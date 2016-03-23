package kevin.mygamehelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.kevin.gamehelper.mygamehelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kevin.utils.Account;
import kevin.database.DataBase.DBHelperDota2;

/**
 * Created by Kevin on 2016/3/21.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class SignInActivity extends Activity {
    @Bind(R.id.img_back)
    ImageButton imgBack;
    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.forget_hint)
    TextView forgetHint;
    @Bind(R.id.sign_in)
    Button signIn;
    DBHelperDota2 helper;
    Dao<Account,String> accountDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sign_in_activity);
        ButterKnife.bind(this);
        helper = DBHelperDota2.getInstance();
    }


    @OnClick({R.id.img_back, R.id.sign_in, R.id.forget_hint})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                break;
            case R.id.sign_in:
                break;
            case R.id.forget_hint:
                break;
        }
    }
}
