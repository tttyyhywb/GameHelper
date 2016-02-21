package kevin.mygamehelper.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.mygamehelper.data.adapter.MainRecyAdapter;
import kevin.utils.Utils;

import com.kevin.gamehelper.mygamehelper.R;
/**
 * Created by kevin on 2015/9/11.
 */
public class Dota2MainMatchActivity extends Activity {

    Dota2GameOutline match;
    Dota2MatchDetails detail;

    @Bind(R.id.main_match_recycler)
    RecyclerView recyclerView;

    @Bind(R.id.tv_competation_type)
    TextView tvCompetationType;

    @Bind(R.id.tv_level)
    TextView tvLevel;

    @Bind(R.id.tv_match_seq)
    TextView tvMatchSeq;

    @Bind(R.id.tv_duration_time)
    TextView tvDurationTime;

    @Bind(R.id.ll_detail_title)
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
        ButterKnife.bind(this);

        Intent intent = getIntent();
        match = (Dota2GameOutline) intent.getSerializableExtra(match.TAG);
        detail = (Dota2MatchDetails) intent.getSerializableExtra(detail.TAG);

        tvDurationTime.setText(detail.getDuration()/60+ Utils.getResource().getString(R.string.minute_ago));
        tvMatchSeq.setText(detail.getMatch_seq_num()+"");
    }
}
