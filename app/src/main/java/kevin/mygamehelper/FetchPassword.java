package kevin.mygamehelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.kevin.gamehelper.mygamehelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kevin on 2016/3/25.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class FetchPassword extends Activity {

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_forget_hint)
    EditText etForgetHint;
    @Bind(R.id.et_forget_answer)
    EditText etForgetAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fetch_password_activity);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.comfirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                FetchPassword.this.finish();
                break;
            case R.id.comfirm:
                break;
        }
    }
}
