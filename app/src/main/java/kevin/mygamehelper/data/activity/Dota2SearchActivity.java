package kevin.mygamehelper.data.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kevin.api.base.network.BaseRequest;
import kevin.utils.Utils;
import kevin.mygamehelper.data.utils.UserIdListAdapter;
import kevin.api.dota2.bean.Dota2Url;
import kevin.api.dota2.bean.Dota2User;
import kevin.api.base.gameBase.ApiResponse;
import com.kevin.gamehelper.mygamehelper.R;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by kevin on 2015/8/30.
 */
public class Dota2SearchActivity extends Activity {

    private final int ISCHANGED = 1;

    @ViewInject(R.id.img_search_back)
    ImageButton imgSearchBack;
    @ViewInject(R.id.edit_search)
    EditText etSearch;
    @ViewInject(R.id.img_search_delete)
    ImageButton imgSearchDelete;

    Dota2Url url = new Dota2Url();
    ArrayList<Dota2User> users;
    UserIdListAdapter userIdListAdapter;
    ListView userList;

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
        init();

    }

    private void init() {
        com.lidroid.xutils.ViewUtils.inject(this);
        userList = (ListView) findViewById(R.id.user_list);
        etSearch.addTextChangedListener(watcher);
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
            if (s.length() != 0 && Utils.allNumber(s.toString())) {
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
            Log.e("..", response.toString());
            if (response.getResponse().getPlayers().size() > 0) {
                Log.e("-----------", userList.toString());
                users = response.getResponse().getPlayers();
                userIdListAdapter = new UserIdListAdapter(Dota2SearchActivity.this, users);
                userList.setAdapter(userIdListAdapter);
            }
        }

        @Override
        protected void afterFail() {
            Toast.makeText(Dota2SearchActivity.this,"请检查网络",Toast.LENGTH_SHORT).show();
        }
    };
}
