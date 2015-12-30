package kevin.mygamehelper.data.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;

import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.mygamehelper.data.utils.MainRecyAdapter;

import com.kevin.gamehelper.mygamehelper.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by kevin on 2015/9/11.
 */
public class Dota2MainMatchActivity extends Activity {

    Dota2GameOutline match;
    Dota2MatchDetails detail;
    @ViewInject(R.id.main_match_recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dota2_main_match);
        init();
        recyclerView.setLayoutManager(new LinearLayoutManager(Dota2MainMatchActivity.this));
        recyclerView.setAdapter(new MainRecyAdapter(this,match,detail));
    }

    private void init() {
        ViewUtils.inject(this);
        Intent intent = getIntent();
        match = (Dota2GameOutline) intent.getSerializableExtra(match.TAG);
        detail = (Dota2MatchDetails) intent.getSerializableExtra(detail.TAG);
    }
}
