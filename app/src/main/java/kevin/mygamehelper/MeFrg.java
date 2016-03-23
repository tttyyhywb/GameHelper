package kevin.mygamehelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevin.gamehelper.mygamehelper.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kevin.utils.Account;
import kevin.utils.AccountManager;

/**
 * Created by Administrator on 2015/8/4.
 */
public class MeFrg extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_me, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_modify_account_info, R.id.tv_account_modify_password, R.id.tv_login_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_modify_account_info:
                break;
            case R.id.tv_account_modify_password:
                break;
            case R.id.tv_login_out:
                AccountManager.removeAccount();
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}
