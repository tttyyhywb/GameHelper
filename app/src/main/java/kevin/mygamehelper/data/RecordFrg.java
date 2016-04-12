package kevin.mygamehelper.data;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevin.gamehelper.mygamehelper.R;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2Players;
import kevin.api.dota2.bean.Dota2User;
import kevin.mygamehelper.data.adapter.RecordRecyAdapter;
import kevin.mygamehelper.data.utils.IFieldGettor;
import kevin.mygamehelper.data.utils.RecordItem;
import kevin.mygamehelper.data.utils.SimpleFieldGettor;

/**
 * Created by Kevin on 2016/1/19.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
@SuppressLint("ValidFragment")
public class RecordFrg extends Fragment implements IFieldGettor {

    Dota2MatchDetails[] detials;
    ArrayList<Dota2GameOutline> matches;
    Dota2User account;
    IFieldGettor fieldGettor;
    int showCount;
    int index;
    @Bind(R.id.record_recy)
    RecyclerView recordRecy;

    private ArrayList<RecordItem> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dota2_preview_record, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecordRecyAdapter adapter = new RecordRecyAdapter(getActivity(), data ,matches.get(index),detials[index]);
        recordRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        recordRecy.setAdapter(adapter);
    }

    public RecordFrg(Dota2MatchDetails[] detials, ArrayList<Dota2GameOutline> matches, Dota2User account) {
        this.detials = detials;
        this.matches = matches;
        this.account = account;
        showCount = matches.size();
        data = new ArrayList<>();
        setFieldGettor(new SimpleFieldGettor());

        initData();
    }

    private void initData() {
        Dota2Players[] accountDetials = new Dota2Players[showCount];

        for (int i = 0; i < showCount; i++) {
            accountDetials[i] = (detials[i].getPlayer(account));
        }

        ArrayList<Float> array = getFieldAsList(accountDetials, "kills", 20);
        prepareData(array, "kills");
        array = getFieldAsList(accountDetials, "deaths", 20);
        prepareData(array, "deaths");
        array = getFieldAsList(accountDetials, "assists", 20);
        prepareData(array, "assists");
        array = getFieldAsList(accountDetials, "last_hits", 20);
        prepareData(array, "last_hits");
        array = getFieldAsList(accountDetials, "denies", 20);
        prepareData(array, "denies");
        array = getFieldAsList(accountDetials, "gold_per_min", 20);
        prepareData(array, "gold_per_min");
        array = getFieldAsList(accountDetials, "xp_per_min", 20);
        prepareData(array, "xp_per_min");
        array = getFieldAsList(accountDetials, "hero_damage", 20);
        prepareData(array, "hero_damage");
        array = getFieldAsList(accountDetials, "tower_damage", 20);
        prepareData(array, "tower_damage");
        array = getFieldAsList(accountDetials, "hero_healing", 20);
        prepareData(array, "hero_healing");
    }

    public void setFieldGettor(IFieldGettor gettor) {
        this.fieldGettor = gettor;
    }

    private void prepareData(ArrayList<Float> array, String name) {
        float max = Collections.max(array);
        Dota2MatchDetails detial = null;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).equals(max)) {
                detial = detials[i];
                index = i;
                break;
            }
        }
        data.add(new RecordItem(account, detial, name, max));
    }

    @Override
    public <T, V> ArrayList<T> getFieldAsList(V[] detials, String fieldName, int count) {
        if (fieldGettor != null) {
            return fieldGettor.getFieldAsList(detials, fieldName, count);
        } else {
            return null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
