package kevin.mygamehelper.data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import kevin.api.base.gameBase.ApiResult;
import kevin.api.base.network.BaseRequest;
import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2MatchHistory;
import kevin.api.dota2.bean.Dota2Url;
import kevin.api.dota2.bean.Dota2User;
import kevin.mygamehelper.data.utils.PreviewRecyAdapter;
import kevin.utils.D2Utils;
import kevin.utils.ImgUtils;
import kevin.utils.Watcher;

import com.google.gson.reflect.TypeToken;
import com.kevin.gamehelper.mygamehelper.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Kevin on 2015/9/2.
 */
public class Dota2PreviewActivity extends FragmentActivity implements  View.OnClickListener{

    @Bind(R.id.preview_content)
    ViewPager mContent;

    @Bind(R.id.img_preview_potrait)
    ImageView imgUserPortrait;

    @Bind(R.id.tv_preview_username)
    TextView tvUsername;

    @Bind(R.id.ll_radar)
    LinearLayout llRadar;

    @Bind(R.id.ll_record)
    LinearLayout llRecord;

    @Bind(R.id.ll_comprehension)
    LinearLayout llComprehension;

    private FragmentPagerAdapter mFrgPageAdapter;
    private List<Fragment> mFragments;

    Dota2User account;
    ArrayList<Dota2GameOutline> matches;
    Dota2MatchDetails[] detials;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dota2_preview);
        init();
        initView();
    }

    private void init(){
        ButterKnife.bind(this);
        Intent intent = getIntent();
        account = (Dota2User) intent.getSerializableExtra(Dota2User.TAG);
        ImgUtils.getInstance().loadImage( account.getAvatarfull(),imgUserPortrait);
        tvUsername.setText(account.getPersonaname());
        matchesHistoryListRequest.getData(Dota2Url.getMatchHistory(D2Utils.getAccountId(account.getSteamid()), 20));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void initView(){

        Fragment comprehensionFrg = new ComprehensionFrg(account);
        Fragment radarFrg = new RadarFrg();
        Fragment recordFrg = new RecordFrg();

        mFragments = new ArrayList<>();

        mFragments.add(comprehensionFrg);
        mFragments.add(radarFrg);
        mFragments.add(recordFrg);

        mFrgPageAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        mContent.setOffscreenPageLimit(3);

        mContent.setAdapter(mFrgPageAdapter);

        mContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setSelected(position);
//                Log.e("page",position+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setSelected(0);
        llRecord.setOnClickListener(this);
        llRadar.setOnClickListener(this);
        llComprehension.setOnClickListener(this);
    }

    private void resetView() {
        llComprehension.setSelected(false);
        llRadar.setSelected(false);
        llRecord.setSelected(false);
    }

    private void setSelected(int i) {

        resetView();
        switch (i) {
            case 0:
                llComprehension.setSelected(true);
                break;
            case 1:
                llRadar.setSelected(true);
                break;
            case 2:
                llRecord.setSelected(true);
                break;
        }
        mContent.setCurrentItem(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_comprehension:
                setSelected(0);
                break;
            case R.id.ll_radar:
                setSelected(1);
                break;
            case R.id.ll_record:
                setSelected(2);
                break;
        }
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
                detailsRequest.getData(Dota2Url.getMatchDetials(matches.get(i).getMatch_id()));
            }
        }

        @Override
        protected void afterFail() {
        }
    };

    class DetailsRequest extends BaseRequest {

        int index;

        public DetailsRequest(int index) {
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
            detials[index] = oneDetails;
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

            }
        }
    };
}
