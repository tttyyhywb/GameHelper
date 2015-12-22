package kevin;

import android.app.Application;
import android.content.Context;

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
        initCommonUtils(this);
        initDatabase();
        initOthers();
    }

    private void initService(){

    }

    private void initCommonUtils(Context context){
        SysUtils.init(this);
        SPUtils.init(this);
    }

    private void initDatabase(){

    }

    private void initOthers(){}
}
