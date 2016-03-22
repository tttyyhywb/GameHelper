package kevin.api.base.network;

import kevin.api.base.ApiBase;
import kevin.api.base.BaseJson;

/**
 * Created by Kevin on 2015/7/23.
 */
public class ApiResponse<T extends BaseJson> extends ApiBase {

    T response;

    int status;

    public ApiResponse(T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Response{" +
                "response=" + response +
                '}';
    }
}