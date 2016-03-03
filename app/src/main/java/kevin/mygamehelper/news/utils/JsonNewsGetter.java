package kevin.mygamehelper.news.utils;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import kevin.api.base.gameBase.ApiResult;
import kevin.api.base.network.BaseRequest;
import kevin.api.dota2.bean.Dota2Hero;
import kevin.api.dota2.bean.Dota2Url;

/**
 * Created by Kevin on 2016/3/2.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class JsonNewsGetter implements NewsGetter{

    News news = new News();
    ApiResult<News> resultNews= new ApiResult(news);

    @Override
    public List<News> getData(int startIndex, int endIndex) {

        return null;
    }

    private void analizeData(String response){

        Gson gson = new Gson();
        resultNews = gson.fromJson(Dota2Url.NewsUrl ,new TypeToken<ApiResult<News>>(){}.getType());
//        if(resultNews.getStatus() == 0 ){
//
//        }

    }

    private class NewsGetRequest extends BaseRequest{

        @Override
        protected void afterSuccess(String result) {
            analizeData(result);
        }

        @Override
        protected void afterFail() { 

        }
    }
}
