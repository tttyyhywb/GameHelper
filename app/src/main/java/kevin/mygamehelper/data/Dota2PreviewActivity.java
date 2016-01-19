package kevin.mygamehelper.data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2Url;
import kevin.api.dota2.bean.Dota2User;

import com.kevin.gamehelper.mygamehelper.R;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

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

    @ViewInject(R.id.ll_radar)
    LinearLayout llRadar;

    @ViewInject(R.id.ll_record)
    LinearLayout llRecord;

    @ViewInject(R.id.ll_comprehension)
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
