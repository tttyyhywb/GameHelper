package kevin.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Kevin on 2016/1/20.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class ImgUtils {

    private static ImgUtils instance;

    DisplayImageOptions options;

    DisplayImageOptions defaultOptions;

    public ImgUtils(){
        defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public static ImgUtils getInstance() {
        if(instance ==null){
            throw new IllegalArgumentException("instance is null ,call init() first");
        }
        return instance;
    }

    public static void  init(){
        if(instance ==null){
            synchronized (ImgUtils.class){
                if(instance == null){
                    instance = new ImgUtils();
                }
            }
        }
    }

    public void loadImage(String url,ImageView view,DisplayImageOptions options){
        if(options != null ){
            ImageLoader.getInstance().displayImage(url,view,options);
        }else {
            ImageLoader.getInstance().displayImage(url, view);
        }
    }

    public void loadImage(String url, ImageView view){
        loadImage(url,view,defaultOptions);
    }

    public void setOptions(DisplayImageOptions options){
        this.options = options;
    }

    public void switchToDefaultOptions(){
        this.options = null;
    }
}
