package kevin.mygamehelper.data.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kevin.api.base.network.BaseRequest;
import kevin.utils.Utils;
import kevin.mygamehelper.data.utils.PreviewListAdapter;
import kevin.api.dota2.jsonResponse.Dota2GameOutline;
import kevin.api.dota2.jsonResponse.Dota2MatchDetails;
import kevin.api.dota2.jsonResponse.Dota2MatchHistory;
import kevin.api.dota2.jsonResponse.Dota2Url;
import kevin.api.dota2.jsonResponse.Dota2User;
import kevin.api.base.gameBase.Result;
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
    Dota2User.Players account;
    ArrayList<Dota2GameOutline> matches;
    Dota2Url url;

    @ViewInject(R.id.img_preview_potrait)
    ImageButton imgUserPortrait;
    @ViewInject(R.id.tv_preview_username)
    TextView tvUsername;
    @ViewInject(R.id.dota2_preview_listview)
    ListView listView;

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
        account = (Dota2User.Players) intent.getSerializableExtra(Dota2User.DOTA2USER);
        bitmapUtils.display(imgUserPortrait,account.getAvatarfull());
        tvUsername.setText(account.getPersonaname());
        matchesHistoryListRequest.getData(url.getMatchHistory(Utils.getAccountId(account.getSteamid()),10));
    }


    BaseRequest matchesHistoryListRequest = new BaseRequest() {

        @Override
        protected void afterSuccess(String responseResult) {

            Gson gson = new Gson();

            Result<Dota2MatchHistory> result;
            result = gson.fromJson(responseResult, new TypeToken<Result<Dota2MatchHistory>>() {
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
            Result<Dota2MatchDetails> result;

            result = gson.fromJson(responseResult, new TypeToken<Result<Dota2MatchDetails>>() {
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
                PreviewListAdapter previewListAdapter = new PreviewListAdapter(Dota2PreviewActivity.this ,matches,account);
                listView.setAdapter(previewListAdapter);
            }
        }
    };
}
