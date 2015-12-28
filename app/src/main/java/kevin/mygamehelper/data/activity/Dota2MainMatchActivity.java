package kevin.mygamehelper.data.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2Players;
import com.kevin.gamehelper.mygamehelper.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by kevin on 2015/9/11.
 */
public class Dota2MainMatchActivity extends Activity {

    Dota2GameOutline match;
    @ViewInject(R.id.list_match)
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dota2_main_match);
        init();
    }

    private void init() {
        ViewUtils.inject(this);
        Intent intent = getIntent();
        match = (Dota2GameOutline) intent.getSerializableExtra(match.TAG);
        /*
        ArrayList<Dota2Players> mSublist = new ArrayList<>(match.getDetails().getPlayers().subList(0, 5));
        MainListAdapter mainListAdapterRadiant = new MainListAdapter(this, mSublist);
        Log.e("radiant", mSublist.toString());
        radiantListView.setAdapter(mainListAdapterRadiant);
        Utils.setListViewHeightBasedOnChildren(radiantListView);

        mSublist = new ArrayList<>(match.getDetails().getPlayers().subList(6, 10));
        Log.e("radiant", mSublist.toString());
        MainListAdapter mainListAdapterDire = new MainListAdapter(this, mSublist);
        direListView.setAdapter(mainListAdapterDire);
        Utils.setListViewHeightBasedOnChildren(direListView);
        */
        ArrayList<Dota2Players> players = match.getDetails().getPlayers();
        Log.e("eeeeerrrroooorrrrrrr",players.size()+"" );
    }
}
