package kevin;

import android.app.Application;
import android.content.Context;

import kevin.database.DataBase.DBHelperDota2;
import kevin.utils.D2Utils;
import kevin.utils.SPUtils;
import kevin.utils.SysUtils;

/**
 * Created by Kevin on 2015/12/21.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initService();
        initDatabase(this);
        initCommonUtils(this);
        initOthers();
    }

    private void initService(){

    }

    private void initCommonUtils(Context context){
        SysUtils.init(this);
        SPUtils.init(this);
        D2Utils.init();
    }

    private void initDatabase(Context context){
        DBHelperDota2.init(context);
    }

    private void initOthers(){}
}
