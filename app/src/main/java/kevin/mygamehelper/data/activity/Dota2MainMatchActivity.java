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


    }
}
