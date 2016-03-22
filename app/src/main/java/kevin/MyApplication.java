package kevin;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import kevin.database.DataBase.DBHelperDota2;
import kevin.utils.AccountManager;
import kevin.utils.D2Utils;
import kevin.utils.ImgUtils;
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

        ImgUtils.init();
    }

    private void initService() {

    }

    private void initCommonUtils(Context context) {
        //Do not initial D2Utils before DataBaseHelper
        Utils.init(this);
        SPUtils.init(this);
        D2Utils.init();
        AccountManager.init();
    }

    private void initDatabase(Context context) {
        DBHelperDota2.init(context);
    }

    private void initOthers() {
    }
}
