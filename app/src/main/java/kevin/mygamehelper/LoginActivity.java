package kevin.mygamehelper;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnChildClick;

/**
 * Created by Kevin on 2015/12/22.
 * DESCRIPTION:preview + login
 * 预览页 + 登录页
 * email:493243390@qq.com
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    @ViewInject(R.id.tv_skip_login)
    TextView tvSkipLogin;

    @ViewInject(R.id.btn_login_in)
    Button btnLoginIn;

    @ViewInject(R.id.btn_sign_up)
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dota2_login_preview_activity);
        initView();
    }

    private void initView(){
        ViewUtils.inject(this);
        btnLoginIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        tvSkipLogin.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        System.exit(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_up:{
                break;
            }
            case R.id.btn_login_in:{
                break;
            }
            case R.id.tv_skip_login:{
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
