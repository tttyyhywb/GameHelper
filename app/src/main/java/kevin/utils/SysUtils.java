package kevin.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Kevin on 2015/12/22.
 * TODO:supply system methon
 * 提供系统函数
 * email:493243390@qq.com
 */
public class SysUtils {

    private Context mContext;

    private static SysUtils instance;

    private SysUtils(Context context){
        this.mContext = context;
    }

    public static SysUtils getInstance(){
        if(instance == null){
            throw new IllegalArgumentException("context is null, please call init(Context)");
        }else{
            return instance;
        }
    }

    public static void init(Context context){
        if(instance == null){
            synchronized (SysUtils.class){
                if(instance == null){
                    instance = new SysUtils(context);
                }
            }
        }
    }

    public Resources getResource(){
        return mContext.getResources();
    }
}
