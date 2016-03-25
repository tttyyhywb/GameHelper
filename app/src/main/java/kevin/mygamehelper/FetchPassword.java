package kevin.mygamehelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.kevin.gamehelper.mygamehelper.R;

import java.sql.SQLException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kevin.database.DataBase.DBHelperDota2;
import kevin.utils.Account;

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

    Dao<Account,String> accountDao;
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fetch_password_activity);
        ButterKnife.bind(this);
        try {
            accountDao = DBHelperDota2.getInstance().getDao(Account.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.img_back, R.id.comfirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                FetchPassword.this.finish();
                break;
            case R.id.comfirm:
                String username = etUsername.getText().toString();
                String forgetHint = etForgetHint.getText().toString();
                String forgetAnswer = etForgetAnswer.getText().toString();
                try {
                    account = accountDao.queryForId(username);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(account==null){
                    Toast.makeText(this,R.string.wrong_username,Toast.LENGTH_SHORT).show();
                }else{
                    if(account.getForgetHint().equals(forgetHint) && account.getForgetAnswer().equals(forgetAnswer)){
                        Toast.makeText(this,"your password is"+account.getPassword(),Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this,R.string.wrong_hint_or_answer,Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
