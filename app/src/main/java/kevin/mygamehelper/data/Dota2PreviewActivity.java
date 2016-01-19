package kevin.mygamehelper.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kevin.api.base.network.BaseRequest;
import kevin.mygamehelper.data.utils.PreviewRecyAdapter;
import kevin.utils.D2Utils;
import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2MatchHistory;
import kevin.api.dota2.bean.Dota2Url;
import kevin.api.dota2.bean.Dota2User;
import kevin.api.base.gameBase.ApiResult;

import com.kevin.gamehelper.mygamehelper.R;

import kevin.utils.SPUtils;
import kevin.utils.Watcher;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Kevin on 2015/9/2.
 */
public class Dota2PreviewActivity extends FragmentActivity implements  View.OnClickListener{

    BitmapUtils bitmapUtils;
    Dota2User account;
    ArrayList<Dota2GameOutline> matches;
    Dota2MatchDetails[] detials;
    Dota2Url url;
    Gson gson = new Gson();

    @ViewInject(R.id.preview_content)
    private ViewPager mContent;

    @ViewInject(R.id.img_preview_potrait)
    ImageView imgUserPortrait;

    @ViewInject(R.id.tv_preview_username)
    TextView tvUsername;

    private FragmentPagerAdapter mFrgPageAdapter;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dota2_preview_pro);
        init();
        initView();
    }

    private void init(){
        com.lidroid.xutils.ViewUtils.inject(this);
        bitmapUtils = new BitmapUtils(this);
        url = new Dota2Url();
        Intent intent = getIntent();
        account = (Dota2User) intent.getSerializableExtra(Dota2User.TAG);
        bitmapUtils.display(imgUserPortrait, account.getAvatarfull());
        tvUsername.setText(account.getPersonaname());
    }

    private void initView(){
        ViewUtils.inject(this);

        Fragment comprehensionFrg = new ComprehensionFrg(account);
        Fragment radarFrg = new RadarFrg();

        mFragments = new ArrayList<>();
        mFragments.add(comprehensionFrg);
        mFragments.add(radarFrg);

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

        mContent.setAdapter(mFrgPageAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
