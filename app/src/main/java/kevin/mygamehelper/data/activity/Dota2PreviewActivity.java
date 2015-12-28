package kevin.mygamehelper.data.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kevin.api.base.network.BaseRequest;
import kevin.mygamehelper.data.utils.PreviewRecyAdapter;
import kevin.utils.D2Utils;
import kevin.mygamehelper.data.utils.PreviewListAdapter;
import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2MatchHistory;
import kevin.api.dota2.bean.Dota2Url;
import kevin.api.dota2.bean.Dota2User;
import kevin.api.base.gameBase.ApiResult;
import com.kevin.gamehelper.mygamehelper.R;
import kevin.utils.Watcher;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Kevin on 2015/9/2.
 */
public class Dota2PreviewActivity extends Activity {

    BitmapUtils bitmapUtils;
    Dota2User account;
    ArrayList<Dota2GameOutline> matches;
    Dota2Url url;

    @ViewInject(R.id.img_preview_potrait)
    ImageButton imgUserPortrait;

    @ViewInject(R.id.tv_preview_username)
    TextView tvUsername;

    @ViewInject(R.id.dota2_preview_listview)
    ListView listView;

    @ViewInject(R.id.dota2_preview_recyclerview)
    RecyclerView myRecyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dota2_preview);

        init();
    }

    void init() {
        com.lidroid.xutils.ViewUtils.inject(this);
        bitmapUtils = new BitmapUtils(this);
        url = new Dota2Url();
        Intent intent = getIntent();
        account = (Dota2User) intent.getSerializableExtra(Dota2User.TAG);
        bitmapUtils.display(imgUserPortrait,account.getAvatarfull());
        tvUsername.setText(account.getPersonaname());
        matchesHistoryListRequest.getData(url.getMatchHistory(D2Utils.getAccountId(account.getSteamid()),10));
    }


    BaseRequest matchesHistoryListRequest = new BaseRequest() {

        @Override
        protected void afterSuccess(String responseResult) {

            Gson gson = new Gson();

            ApiResult<Dota2MatchHistory> result;
            result = gson.fromJson(responseResult, new TypeToken<ApiResult<Dota2MatchHistory>>() {
            }.getType());

            matches = result.getResult().getMatches();

            Log.e("result", result.toString());
            Log.e("matches", matches.toString());

            MatchBaseRequest matchBaseRequest;
            for(int i=0;i<=6;i++){
                matchBaseRequest = new MatchBaseRequest(matches.get(i),i);
                matchBaseRequest.getData(url.getMatchDetials(matches.get(i).getMatch_id()));
            }
        }

        @Override
        protected void afterFail() {
        }
    };

    class MatchBaseRequest extends BaseRequest{

        Dota2GameOutline detials;

        int index;
        MatchBaseRequest(Dota2GameOutline detials,int index){
            this.detials = detials;
            this.index = index;
            this.detials.addObserver(watcher);
        }
        @Override
        protected void afterSuccess(String responseResult) {
            Gson gson = new Gson();
            ApiResult<Dota2MatchDetails> result;

            result = gson.fromJson(responseResult, new TypeToken<ApiResult<Dota2MatchDetails>>() {
            }.getType());
            //Log.e("detial", result.getResult().toString());
            setDetial(this.detials, result.getResult());

        }

        @Override
        protected void afterFail() {

        }

        private void setDetial(Dota2GameOutline gameDetials, Dota2MatchDetails matchDetails) {
            gameDetials.setDetails(matchDetails);
            gameDetials.notifyObservers();
        }
    }

    Watcher watcher = new Watcher(){
        int count = 0;
        @Override
        public void update(Observable observable, Object data) {
            count++;
            Log.e("count", count+"");
            if(count == 6){
                Log.e("aaaaaaa", matches.toString());
//                PreviewListAdapter previewListAdapter = new PreviewListAdapter(Dota2PreviewActivity.this ,matches,account);
//                listView.setAdapter(previewListAdapter);
                myRecyView.setLayoutManager(new LinearLayoutManager(Dota2PreviewActivity.this));
                myRecyView.setAdapter(new PreviewRecyAdapter(Dota2PreviewActivity.this ,matches,account));

            }
        }
    };
}
