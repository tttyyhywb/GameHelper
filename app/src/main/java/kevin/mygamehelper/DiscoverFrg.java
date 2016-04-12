package kevin.mygamehelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevin.gamehelper.mygamehelper.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import kevin.mygamehelper.news.adapter.DiscoverRecyAdapter;
import kevin.mygamehelper.news.utils.JsonNewsGetter;
import kevin.mygamehelper.news.utils.News;
import kevin.mygamehelper.news.utils.INewsGetter;

/**
 *  Created by Kevin on  2015/8/4.
 */
public class DiscoverFrg extends Fragment {

    @Bind(R.id.discover_recy)
    RecyclerView discoverRecy;

    DiscoverRecyAdapter discoverRecyAdapter;

    INewsGetter INewsGetter = new JsonNewsGetter();
    int count = 0;
    static int INTERVAL = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_discover, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        INewsGetter.getData(count,count+INTERVAL);
        count = count + INTERVAL + 1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    public void onEventMainThread(List<News> event){
        discoverRecyAdapter = new DiscoverRecyAdapter(getActivity(), event);
        discoverRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        discoverRecy.setAdapter(discoverRecyAdapter);
    }
}
