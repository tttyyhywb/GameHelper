package kevin.mygamehelper.data;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.kevin.gamehelper.mygamehelper.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kevin on 2016/3/31.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class NotFoundActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.not_found_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.img_back)
    public void onClick() {
        NotFoundActivity.this.finish();
    }
}
