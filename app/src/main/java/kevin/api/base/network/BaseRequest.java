package kevin.api.base.network;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import kevin.utils.Utils;

/**
 * Created by Kevin on 2015/8/13.
 */
public abstract class BaseRequest {

    public void getData(String url) {

        RequestQueue mQueue = Volley.newRequestQueue(Utils.getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        afterSuccess(jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        afterFail();
                    }
                });
        mQueue.add(jsonObjectRequest);
    }

    protected abstract void afterSuccess(String result);

    protected abstract void afterFail();
}
