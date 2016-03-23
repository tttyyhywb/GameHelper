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
import kevin.utils.AccountManager;
import kevin.utils.Utils;

/**
 * Created by Kevin on 2016/3/23.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class PasswordModifyActivity extends Activity {

    @Bind(R.id.et_old_password)
    EditText etOldPassword;
    @Bind(R.id.et_new_password)
    EditText etNewPassword;
    @Bind(R.id.et_confirm_new_password)
    EditText etConfirmNewPassword;

    Dao<Account,String> accountDao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.modify_password);
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
                PasswordModifyActivity.this.finish();
                break;
            case R.id.comfirm:
                try {
                    String oldPassword = etOldPassword.getText().toString();
                    String newPassword = etNewPassword.getText().toString();
                    String comfirmPassword = etConfirmNewPassword.getText().toString();
                    Account account = accountDao.queryForId(AccountManager.getAccount().getUsername());
                    if(!oldPassword.equals(account.getPassword())){
                        Toast.makeText(this, Utils.getResource().getString(R.string.wrong_password),Toast.LENGTH_SHORT).show();
                    }else{
                        if(newPassword.equals(comfirmPassword)){
                            AccountManager.modifyPassword(comfirmPassword);
                        }else{
                            Toast.makeText(this,Utils.getResource().getString(R.string.password_not_equal),Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
