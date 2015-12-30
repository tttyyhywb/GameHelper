package kevin.utils;

import android.content.Context;

import java.util.Set;

/**
 * Kevin by my on 2015/12/22.
 * share preference utils
 */
public class SPUtils {

    private Context mContext;

    private static SPUtils instance;

    private SPUtils(Context context){
        this.mContext = context;
    }

    public static SPUtils getInstance(){
        if(instance ==null)
        {
            throw new IllegalArgumentException("instance is null, please call init(Context)");
        }else {
            return instance;
        }
    }

    private final static String SP_FILE_NAME = "gamehelper";

    public static void init(Context context){
        if(instance ==null) {
            synchronized (SPUtils.class){
                if(instance ==null){
                    instance =new SPUtils(context);
                }
            }
        }
    }

    public boolean getBoolean(String key,boolean defValue){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).getBoolean(key,defValue);
    }

    public boolean putBoolean(String key,boolean value){
        return  mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).edit().putBoolean(key,value).commit();
    }

    public int getInt(String key,int defValue){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).getInt(key,defValue);
    }

    public boolean putInt(String key, int value){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).edit().putInt(key,value).commit();
    }

    public float getFloat(String key,int defValue){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).getFloat(key,defValue);
    }

    public boolean putFloat(String key,int value){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).edit().putFloat(key,value).commit();
    }

    public long getLong(String key,long defValue){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).getLong(key, defValue);
    }

    public boolean putLong(String key, long value){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).edit().putLong(key, value).commit();
    }

    public String getString(String key,String defValue){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).getString(key,defValue);
    }

    public boolean putString(String key,String value){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    public Set<String> getStringSet(String key,Set<String> defValue){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).getStringSet(key,defValue);
    }

    public boolean putStringSet(String key,Set<String> value){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).edit().putStringSet(key, value).commit();
    }

    public boolean clear(){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).edit().clear().commit();
    }

    public boolean remove(String key){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).edit().remove(key).commit();
    }

    public boolean contains(String key){
        return mContext.getSharedPreferences(SP_FILE_NAME,Context.MODE_PRIVATE).contains(key);
    }
}
