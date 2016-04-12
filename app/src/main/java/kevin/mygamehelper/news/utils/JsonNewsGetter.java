package kevin.mygamehelper.news.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import kevin.api.base.network.ApiResult;
import kevin.api.base.network.BaseRequest;
import kevin.api.dota2.bean.Dota2Url;

/**
 * Created by Kevin on 2016/3/2.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class JsonNewsGetter implements INewsGetter {

    News news;
    ApiResult<News> resultNews= new ApiResult(news);

    @Override
    public List<News> getData(int startIndex, int endIndex) {
        ArrayList<News> news = new ArrayList<>();
        news.add(new News("content1","","title1"));
        news.add(new News("content2","","title2"));
        news.add(new News("content3","","title3"));
        news.add(new News("content4","","title4"));
        EventBus.getDefault().post(news);
        return news;
    }

    private void analizeData(String response){

        Gson gson = new Gson();
        resultNews = gson.fromJson(Dota2Url.NewsUrl ,new TypeToken<ApiResult<News>>(){}.getType());
//        if(resultNews.getStatus() == 0 ){
//
//        }
        EventBus.getDefault().post(resultNews.getResult());
    }

    //请求Json格式数据url详见Dota2Url.NewsUrl
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
