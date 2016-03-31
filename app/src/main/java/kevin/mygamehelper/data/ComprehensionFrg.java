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

import com.kevin.gamehelper.mygamehelper.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2User;
import kevin.mygamehelper.data.adapter.ComprehensionRecyAdapter;

/**
 * Created by Kevin on 2016/1/19.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class ComprehensionFrg extends Fragment {

    Dota2User account;
    ArrayList<Dota2GameOutline> matches;
    Dota2MatchDetails[] detials;

    @Bind(R.id.dota2_preview_recyclerview)
    RecyclerView myRecyView;
    @Bind(R.id.ll_preview_recy)
    LinearLayout llRecy;

    public ComprehensionFrg(){

    }

    public ComprehensionFrg(Dota2User account, Dota2MatchDetails[] detials, ArrayList<Dota2GameOutline> matches) {
        this.account = account;
        this.detials = detials;
        this.matches=matches;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dota2_preview_comprehension, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    void init() {
        if (llRecy != null) {
            llRecy.setVisibility(View.VISIBLE);
            myRecyView.setLayoutManager(new LinearLayoutManager(getActivity()));
            myRecyView.setAdapter(new ComprehensionRecyAdapter(getActivity(), matches, account, detials,20));
        }
    }

}
