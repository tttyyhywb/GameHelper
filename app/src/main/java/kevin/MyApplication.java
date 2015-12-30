package kevin;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import kevin.database.DataBase.DBHelperDota2;
import kevin.utils.D2Utils;
import kevin.utils.SPUtils;
import kevin.utils.Utils;

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
        initImageLoader();
        initOthers();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);

        ImageLoader.getInstance().init(configuration);
    }

    private void initService(){

    }

    private void initCommonUtils(Context context){
        Utils.init(this);
        SPUtils.init(this);
        D2Utils.init();
    }

    private void initDatabase(Context context){
        DBHelperDota2.init(context);
    }

    private void initOthers(){}
}
