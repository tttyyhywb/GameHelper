package kevin.mygamehelper;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import kevin.utils.Utils;
import com.kevin.gamehelper.mygamehelper.R;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    @ViewInject(R.id.id_viewpager_cont)
    ViewPager mViewPagerCont;
    private FragmentPagerAdapter mFrgPageAdapter;
    private List<Fragment> mFragments;

    @ViewInject(R.id.ll_data)
    private LinearLayout mTabData;
    @ViewInject(R.id.ll_me)
    private LinearLayout mTabMe;
    @ViewInject(R.id.ll_discovery)
    private LinearLayout mTabDiscover;

    @ViewInject(R.id.img_tab_data)
    ImageButton imgData;
    @ViewInject(R.id.img_tab_me)
    ImageButton imgMe;
    @ViewInject(R.id.img_tab_discover)
    ImageButton imgDiscover;
    static Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        utils = new Utils(this);
        initView();
        resetView();
        setSelected(0);
    }

    public void initView() {
        ViewUtils.inject(this);

        Fragment meFrg = new MeFrg();
        Fragment dataFrg = new DataFrg();
        Fragment discoverFrg = new DiscoverFrg();
        mFragments = new ArrayList<Fragment>();

        mFragments.add(dataFrg);
        mFragments.add(discoverFrg);
        mFragments.add(meFrg);

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

        mViewPagerCont.setAdapter(mFrgPageAdapter);

        mViewPagerCont.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        mTabData.setOnClickListener(this);
        mTabMe.setOnClickListener(this);
        mTabDiscover.setOnClickListener(this);
    }

    private void resetView() {
        imgData.setImageResource(R.mipmap.ic_data);
        imgDiscover.setImageResource(R.mipmap.ic_discovery);
        imgMe.setImageResource(R.mipmap.ic_me);
    }

    private void setSelected(int i) {

        resetView();
        switch (i) {
            case 0:
                imgData.setImageResource(R.mipmap.ic_data_click);
                break;
            case 1:
                imgDiscover.setImageResource(R.mipmap.ic_discovery_click);
                break;
            case 2:
                imgMe.setImageResource(R.mipmap.ic_me_click);
                break;
        }
        mViewPagerCont.setCurrentItem(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_data:
                setSelected(0);
                break;
            case R.id.ll_discovery:
                setSelected(1);
                break;
            case R.id.ll_me:
                setSelected(2);
                break;
        }
    }


}
