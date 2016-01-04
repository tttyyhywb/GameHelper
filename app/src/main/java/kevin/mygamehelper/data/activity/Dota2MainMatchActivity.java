package kevin.mygamehelper.data.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.mygamehelper.data.utils.MainRecyAdapter;
import kevin.utils.Utils;

import com.kevin.gamehelper.mygamehelper.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by kevin on 2015/9/11.
 */
public class Dota2MainMatchActivity extends Activity {

    Dota2GameOutline match;
    Dota2MatchDetails detail;

    @ViewInject(R.id.main_match_recycler)
    RecyclerView recyclerView;

    @ViewInject(R.id.tv_end_time)
    TextView tvEndTime;

    @ViewInject(R.id.tv_competation_type)
    TextView tvCompetationType;

    @ViewInject(R.id.tv_level)
    TextView tvLevel;

    @ViewInject(R.id.tv_match_seq)
    TextView tvMatchSeq;

    @ViewInject(R.id.tv_duration_time)
    TextView tvDurationTime;

    @ViewInject(R.id.ll_detail_title)
    LinearLayout llDetailTile;

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

        tvDurationTime.setText(detail.getDuration()/60+ Utils.getResource().getString(R.string.minute_ago));
        tvMatchSeq.setText(detail.getMatch_seq_num()+"");
    }
}
