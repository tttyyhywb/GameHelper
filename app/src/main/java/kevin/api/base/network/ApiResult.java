package kevin.api.base.network;

import kevin.api.base.ApiBase;
import kevin.api.base.BaseJson;

/**
 * Created by Kevin on 2015/8/10.
 */
public class ApiResult<T extends BaseJson>  extends ApiBase {

    T result;

    int status;

    public ApiResult(){

    }

    public ApiResult(T result) {
      this.result=result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "result=" + result +
                ", status=" + status +
                '}';
    }
}
