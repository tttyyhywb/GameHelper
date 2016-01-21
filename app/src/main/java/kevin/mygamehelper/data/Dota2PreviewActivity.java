package kevin.mygamehelper.data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import kevin.api.dota2.bean.Dota2Url;
import kevin.api.dota2.bean.Dota2User;
import kevin.utils.ImgUtils;

import com.kevin.gamehelper.mygamehelper.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2015/9/2.
 */
public class Dota2PreviewActivity extends FragmentActivity implements  View.OnClickListener{

    Dota2User account;

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

}
