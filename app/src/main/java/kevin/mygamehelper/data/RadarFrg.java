package kevin.mygamehelper.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kevin.gamehelper.mygamehelper.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2User;
import kevin.mygamehelper.data.view.RadarView;

/**
 * Created by Kevin on 2016/1/19.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class RadarFrg extends Fragment {

    Dota2MatchDetails[] detials;
    ArrayList<Dota2GameOutline> matches;
    Dota2User account;

    @Bind(R.id.twenty_times)
    Button btnTimes;
    @Bind(R.id.radar)
    RadarView radarView;

    public RadarFrg() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dota2_preview_radar, container, false);
        ButterKnife.bind(this, view);
        radarView = new RadarView(getActivity());
        radarView.prepareEndHex(detials,20,account);

        return view;
    }

    public RadarFrg(Dota2MatchDetails[] detials, ArrayList<Dota2GameOutline> matches, Dota2User account) {
        this.detials = detials;
        this.matches = matches;
        this.account = account;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
