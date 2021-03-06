package kevin.mygamehelper.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.dao.Dao;
import com.kevin.gamehelper.mygamehelper.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kevin.Constant.Configure;
import kevin.api.base.network.ApiResponse;
import kevin.api.base.network.BaseRequest;
import kevin.api.dota2.bean.Dota2Url;
import kevin.api.dota2.bean.Dota2User;
import kevin.database.DataBase.DBHelperDota2;
import kevin.mygamehelper.data.adapter.UserIdRecyAdapter;
import kevin.utils.D2Utils;
import kevin.utils.SPUtils;

/**
 * Created by kevin on 2015/8/30.
 */
public class Dota2SearchActivity extends Activity {

    private final int ISCHANGED = 1;

    @Bind(R.id.img_search_back)
    ImageButton imgSearchBack;

    @Bind(R.id.edit_search)
    EditText etSearch;

    @Bind(R.id.img_search_delete)
    ImageButton imgSearchDelete;

    @Bind(R.id.search_recycler)
    RecyclerView searchRecy;

    @Bind(R.id.match_userid_title)
    LinearLayout llMatchUserTitle;

    @Bind(R.id.ll_history)
    LinearLayout llRecentSearch;

    @Bind(R.id.recent_clear_history)
    TextView clearHistory;

    Dota2Url url = new Dota2Url();
    ArrayList<Dota2User> users;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.e("url", url.getPlayerSummaries((String) msg.obj));
                    userListRequest.getData(url.getPlayerSummaries((String) msg.obj));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dota2_search);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        etSearch.addTextChangedListener(watcher);
        Set<String> steamIds = SPUtils.getInstance().getStringSet(Dota2User.TAG, null);
        if (steamIds != null) {
            try {
                Dao<Dota2User, String> dao = DBHelperDota2.getInstance().getDao(Dota2User.class);
                users = new ArrayList<>();
                for (String s : steamIds) {
                    users.add(dao.queryForId(s));
                }
                llRecentSearch.setVisibility(View.VISIBLE);
                llMatchUserTitle.setVisibility(View.GONE);
                setAdapter(searchRecy, new UserIdRecyAdapter(users, Dota2SearchActivity.this));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            mHandler.removeMessages(ISCHANGED);

            if (s.length() >= 7 && D2Utils.allNumber(s.toString())) {
                imgSearchBack.setVisibility(View.VISIBLE);
                Message msg = new Message();
                msg.what = ISCHANGED;
                msg.obj = s.toString();
                mHandler.sendMessageDelayed(msg, 500);
            } else {
            }
        }
    };

    BaseRequest userListRequest = new BaseRequest() {

        @Override
        protected void afterSuccess(String responseResult) {

            Gson gson = new Gson();

            Dota2User dota2User = new Dota2User();
            ApiResponse<Dota2User> response = new ApiResponse(dota2User);
            response = gson.fromJson(responseResult, new TypeToken<ApiResponse<Dota2User>>() {
            }.getType());
            if (response.getResponse().getPlayers().size() > 0) {
                users = response.getResponse().getPlayers();
                llRecentSearch.setVisibility(View.GONE);
                llMatchUserTitle.setVisibility(View.VISIBLE);
                setAdapter(searchRecy, new UserIdRecyAdapter(users, Dota2SearchActivity.this));
            }else {
                llMatchUserTitle.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void afterFail() {
            Toast.makeText(Dota2SearchActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
        }
    };

    private void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setLayoutManager(new LinearLayoutManager(Dota2SearchActivity.this));
        view.setAdapter(adapter);
    }

    @OnClick({R.id.img_search_back, R.id.recent_clear_history, R.id.cant_find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_search_back:
                Dota2SearchActivity.this.finish();
                break;
            case R.id.recent_clear_history:
                SPUtils.getInstance().remove(Dota2User.TAG);
                users = new ArrayList<>();
                setAdapter(searchRecy, new UserIdRecyAdapter(users, Dota2SearchActivity.this));
                llRecentSearch.setVisibility(View.GONE);
                break;
            case R.id.cant_find:{
                Intent intent = new Intent(Dota2SearchActivity.this, NotFoundActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
