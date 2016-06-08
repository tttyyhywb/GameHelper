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
        news.add(new News("content1","assets://news/news_id_0001.png","震中杯现场：清新萌妹，性感coser"));
        news.add(new News("content2","assets://news/news_id_0002.png","关于NewBee 的BP思路的一点分析"));
        news.add(new News("content3","assets://news/news_id_0003.png","Max+战报，A队淘汰密码，凌晨迎战NewBee"));
        news.add(new News("content4","assets://news/news_id_0004.png","Esl one法兰克福中国区预算赛，二十进一的争夺战"));
        news.add(new News("content5","assets://news/news_id_0005.png","巨神归来！ --从震中杯看上古巨神"));
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
