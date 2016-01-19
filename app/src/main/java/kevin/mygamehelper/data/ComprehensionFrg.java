package kevin.mygamehelper.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kevin.gamehelper.mygamehelper.R;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Observable;

import kevin.api.base.gameBase.ApiResult;
import kevin.api.base.network.BaseRequest;
import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2MatchHistory;
import kevin.api.dota2.bean.Dota2Url;
import kevin.api.dota2.bean.Dota2User;
import kevin.mygamehelper.data.utils.PreviewRecyAdapter;
import kevin.utils.D2Utils;
import kevin.utils.Watcher;

/**
 * Created by Kevin on 2016/1/19.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class ComprehensionFrg extends Fragment {

    Dota2User account;
    ArrayList<Dota2GameOutline> matches;
    Dota2MatchDetails[] detials;
    Dota2Url url;
    Gson gson = new Gson();

    @ViewInject(R.id.dota2_preview_recyclerview)
    RecyclerView myRecyView;

    @ViewInject(R.id.ll_preview_recy)
    LinearLayout llRecy;

    public ComprehensionFrg(Dota2User account) {
        this.account = account;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dota2_preview_comprehension,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        llRecy = (LinearLayout)view.findViewById(R.id.ll_preview_recy);
        myRecyView=(RecyclerView)view.findViewById(R.id.dota2_preview_recyclerview);
        init();
    }

    void init() {
        url = new Dota2Url();
        detials = new Dota2MatchDetails[6];
        matchesHistoryListRequest.getData(url.getMatchHistory(D2Utils.getAccountId(account.getSteamid() ), 10));
    }


    BaseRequest matchesHistoryListRequest = new BaseRequest() {

        @Override
        protected void afterSuccess(String responseResult) {


            ApiResult<Dota2MatchHistory> result;
            result = gson.fromJson(responseResult, new TypeToken<ApiResult<Dota2MatchHistory>>() {
            }.getType());

            matches = result.getResult().getMatches();

            for (int i = 0; i < 6; i++) {
                DetailsRequest detailsRequest = new DetailsRequest(i);
                detailsRequest.getData(url.getMatchDetials(matches.get(i).getMatch_id()));
            }
        }

        @Override
        protected void afterFail() {
        }
    };

    class DetailsRequest extends BaseRequest{

        int index;
        public DetailsRequest(int index){
            this.index = index;
        }

        @Override
        protected void afterSuccess(String responseResult) {
            ApiResult<Dota2MatchDetails> result;
            result = gson.fromJson(responseResult, new TypeToken<ApiResult<Dota2MatchDetails>>() {
            }.getType());
            Dota2MatchDetails oneDetails;
            oneDetails = result.getResult();
            oneDetails.addObserver(watcher);
            detials[index]=oneDetails;
            oneDetails.changed();
            oneDetails.notifyObservers();
        }

        @Override
        protected void afterFail() {

        }
    };

    Watcher watcher = new Watcher() {
        int count = 0;

        @Override
        public void update(Observable observable, Object data) {
            count++;
            if (count == 6) {
                llRecy.setVisibility(View.VISIBLE);
                myRecyView.setLayoutManager(new LinearLayoutManager(getActivity()));
                myRecyView.setAdapter(new PreviewRecyAdapter(getActivity(), matches, account, detials));
            }
        }
    };
}
