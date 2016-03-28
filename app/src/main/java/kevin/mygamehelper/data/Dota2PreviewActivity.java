package kevin.mygamehelper.data;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kevin.gamehelper.mygamehelper.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.Bind;
import butterknife.ButterKnife;
import kevin.api.base.network.ApiResult;
import kevin.api.base.network.BaseRequest;
import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2MatchHistory;
import kevin.api.dota2.bean.Dota2Url;
import kevin.api.dota2.bean.Dota2User;
import kevin.mygamehelper.LoginActivity;
import kevin.mygamehelper.SignUpActivity;
import kevin.utils.AccountManager;
import kevin.utils.D2Utils;
import kevin.utils.ImgUtils;
import kevin.utils.Watcher;

/**
 * Created by Kevin on 2015/9/2.
 */
public class Dota2PreviewActivity extends FragmentActivity implements View.OnClickListener {

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

    @Bind(R.id.loading)
    ImageView loading;

    @Bind(R.id.content)
    FrameLayout content;

    @Bind(R.id.bind_player)
    TextView bindPlayer;

    private FragmentPagerAdapter mFrgPageAdapter;
    private List<Fragment> mFragments;

    private AnimationDrawable animationDrawable;

    Dota2User player;
    ArrayList<Dota2GameOutline> matches;
    Dota2MatchDetails[] detials;
    Gson gson = new Gson();

    public int MATCH_COUNT = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dota2_preview);
        ButterKnife.bind(this);
        init();
        //initView();  该语句移动至watcher中  当数据完整获取后  进行view初始化
    }

    private void init() {
        ButterKnife.bind(this);

        loading.setImageResource(R.drawable.loading_anim);
        animationDrawable = (AnimationDrawable) loading.getDrawable();
        animationDrawable.start();

        Intent intent = getIntent();
        player = (Dota2User) intent.getSerializableExtra(Dota2User.TAG);
        ImgUtils.getInstance().loadImage(player.getAvatarfull(), imgUserPortrait);
        tvUsername.setText(player.getPersonaname());
        detials = new Dota2MatchDetails[100];
        matchesHistoryListRequest.getData(Dota2Url.getMatchHistory(D2Utils.getAccountId(player.getSteamid()), MATCH_COUNT));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void initView() {

        animationDrawable.stop();
        if (this.isFinishing()) {
            Log.e("isfinishing", "initView: true ");
            return;
        }

        Fragment comprehensionFrg = new ComprehensionFrg(player, detials, matches);
        Fragment radarFrg = new RadarFrg(detials, matches, player);
        Fragment recordFrg = new RecordFrg(detials, matches, player);

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
        bindPlayer.setOnClickListener(this);

        loading.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
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
            case R.id.bind_player:
                if (AccountManager.getAccount() != null) {
                    AccountManager.addBindPlayer(player);
                    Toast.makeText(this,R.string.binded,Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Dota2PreviewActivity.this, SignUpActivity.class);
                    startActivity(intent);
                }
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

            for (int i = 0; i < MATCH_COUNT; i++) {
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
    }

    ;

    Watcher watcher = new Watcher() {
        int count = 0;

        @Override
        public void update(Observable observable, Object data) {
            count++;
            if (count == 20) {
                initView();
            }
        }
    };
}
