package kevin.mygamehelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kevin on 2016/3/21.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class SignUpActivity extends Activity {
    @Bind(R.id.img_back)
    ImageButton imgBack;
    @Bind(R.id.username)
    EditText etUername;
    @Bind(R.id.password)
    EditText etPassword;
    @Bind(R.id.confirm_password)
    EditText etConfirmPassword;
    @Bind(R.id.sign_in)
    Button signIn;
    Intent intent;
    @Bind(R.id.tv_wrong_info)
    TextView tvWrongInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sign_up_activity);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.sign_in})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.sign_in:
                String username = etUername.getText().toString();
                String passwprd = etPassword.getText().toString();
                String comfirmPassword = etConfirmPassword.getText().toString();
                
                break;
        }
    }
}
