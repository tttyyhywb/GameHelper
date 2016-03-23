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
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.kevin.gamehelper.mygamehelper.R;

import java.sql.SQLException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kevin.database.DataBase.DBHelperDota2;
import kevin.utils.Account;
import kevin.utils.AccountManager;

/**
 * Created by Kevin on 2016/3/21.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class SignInActivity extends Activity {

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;

    Dao<Account, String> accountDao;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sign_in_activity);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.img_back, R.id.sign_in, R.id.forget_hint})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                intent = new Intent(SignInActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.sign_in:
                signIn();
                break;
            case R.id.forget_hint:
                break;
        }
    }

    private void signIn(){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        try {
            accountDao = DBHelperDota2.getInstance().getDao(Account.class);
            Account account = accountDao.queryForId(username);
            if (account == null) {
                Toast.makeText(this, "账户不存在!", Toast.LENGTH_SHORT).show();
            } else {
                if (account.getPassword().equals(password)) {
                    intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                    AccountManager.setAccount(account);
                    SignInActivity.this.finish();
                } else {
                    Toast.makeText(this, "密码错误!", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
