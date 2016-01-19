package kevin.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.kevin.gamehelper.mygamehelper.R;

/**
 * Created by Kevin on 2015/12/22.
 * TODO:supply system methon
 * 提供系统函数
 * email:493243390@qq.com
 */
public class Utils {

    private static Context mContext;

    private static Utils instance;

    private Utils(Context context){
        this.mContext = context;
    }

    public static Utils getInstance(){
        if(instance == null){
            throw new IllegalArgumentException("context is null, please call init(Context)");
        }else{
            return instance;
        }
    }

    public static void init(Context context){
        if(instance == null){
            synchronized (Utils.class){
                if(instance == null){
                    instance = new Utils(context);
                }
            }
        }
    }

    public static Resources getResource(){
        return mContext.getResources();
    }

    public static AssetManager getAssetManager(){
        return mContext.getAssets();
    }

    public static int string2int(String s){
        return Integer.parseInt(s);
    }

    public static String int2string(long i){
        return i+"";
    }

    public static Context getContext(){
        return mContext;
    }
}
