package kevin.mygamehelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kevin.gamehelper.mygamehelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kevin.api.Status;
import kevin.api.base.network.ApiResult;
import kevin.api.bean.Account;
import kevin.mygamehelper.common.view.WaitingDialog;
import kevin.mygamehelper.utils.AccountGetter;
import kevin.mygamehelper.utils.SqlGetter;
import kevin.utils.AccountManager;

/**
 * Created by Kevin on 2016/3/21.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class SignUpActivity extends Activity {

    @Bind(R.id.tv_wrong_info)
    TextView tvWrongInfo;
    @Bind(R.id.username)
    EditText etUsername;
    @Bind(R.id.password)
    EditText etPassword;
    @Bind(R.id.confirm_password)
    EditText etConfirmPassword;

    Account account;
    Gson gson;
    AccountGetter getter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sign_up_activity);
        ButterKnife.bind(this);
        gson = new Gson();
        getter = new SqlGetter();
    }

    @OnClick({R.id.img_back, R.id.sign_up})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.sign_up:
                tvWrongInfo.setVisibility(View.INVISIBLE);
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String comfirmPassword = etConfirmPassword.getText().toString();
                if (!password.equals(comfirmPassword)) {
                    tvWrongInfo.setVisibility(View.VISIBLE);
                } else {
                    account = new Account();
                    account.setUsername(username);
                    account.setPassword(password);
                    gson.toJson(account);
                    signUpAccount();
                }
                break;
        }
    }

    private void signUpAccount() {
        showWaitingDlg();
        ApiResult<Account> accountApi = new ApiResult<>(account);
        accountApi.setStatus(Status.SUCCESS);
        String result =  getter.getAccount(gson.toJson(accountApi));
        analyzeResult(result);
    }

    private void analyzeResult(String result) {
        ApiResult<Account> apiResult = gson.fromJson(result, new TypeToken<ApiResult<Account>>(){}.getType());
        switch ( apiResult.getStatus() ){
            case Status.SUCCESS:{
                AccountManager.getInstance().setAccount(apiResult.getResult());
                intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    private void showWaitingDlg() {
        WaitingDialog waitingDialog = new WaitingDialog(this, R.drawable.frame);
        waitingDialog.show();
    }

}
