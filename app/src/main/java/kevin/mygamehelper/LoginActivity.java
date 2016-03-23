package kevin.mygamehelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import kevin.utils.AccountManager;

/**
 * Created by Kevin on 2015/12/22.
 * DESCRIPTION:preview + login
 * 预览页 + 登录页
 * email:493243390@qq.com
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.tv_skip_login)
    TextView tvSkipLogin;

    @Bind(R.id.btn_sign_in)
    Button btnLoginIn;

    @Bind(R.id.btn_sign_up)
    Button btnSignUp;

    @Bind(R.id.rl_login_preview)
    RelativeLayout rlLoginPreview;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dota2_login_preview_activity);
        ButterKnife.bind(this);
        initView();
        startLaunchView();
    }

    private void startLaunchView() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (AccountManager.getInstance().getAccount() == null) {
                    rlLoginPreview.setVisibility(View.VISIBLE);
                } else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
            }
        }, 2900);
    }

    private void initView() {
        ButterKnife.bind(this);
        btnLoginIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        tvSkipLogin.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (AccountManager.getAccount() != null) {
            finish();
            System.exit(0);
        }else{
            rlLoginPreview.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up: {
                intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_sign_in: {
                intent = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.tv_skip_login: {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
                break;
            }
        }
    }
}
