package kevin.api.base.network;

import kevin.api.dota2.bean.Dota2Url;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by Kevin on 2015/8/13.
 */
public abstract class BaseRequest {


    HttpUtils httpUtils = new HttpUtils();
    Dota2Url dota2Url = new Dota2Url();


    public void getData(String url){

        RequestParams params = new RequestParams();
        params.addHeader("HOST","api.steampowered.com");
        params.addHeader("Connection","keep-alive");
        params.addHeader("Referer","http://123.sogou.com/");

        httpUtils.send(HttpRequest.HttpMethod.GET, url ,params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        afterSuccess(responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        afterFail();
                    }
                });

    }

    protected abstract void afterSuccess(String result);

    protected abstract void afterFail();
}
