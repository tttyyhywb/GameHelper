package kevin.mygamehelper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import kevin.utils.D2Utils;

import com.kevin.gamehelper.mygamehelper.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    @Bind(R.id.id_viewpager_cont)
    ViewPager mViewPagerCont;
    private FragmentPagerAdapter mFrgPageAdapter;
    private List<Fragment> mFragments;

    @Bind(R.id.ll_data)
    LinearLayout mTabData;
    @Bind(R.id.ll_me)
    LinearLayout mTabMe;
    @Bind(R.id.ll_discovery)
    LinearLayout mTabDiscover;

    @Bind(R.id.img_tab_data)
    ImageButton imgData;
    @Bind(R.id.img_tab_me)
    ImageButton imgMe;
    @Bind(R.id.img_tab_discover)
    ImageButton imgDiscover;
    static D2Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        utils =  D2Utils.getInstantce();
        initView();
        resetView();
        setSelected(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void initView() {
        ButterKnife.bind(this);

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
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {            }

            @Override
            public void onPageSelected(int position) {
                setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
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
