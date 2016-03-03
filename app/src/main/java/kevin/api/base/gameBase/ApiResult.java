package kevin.api.base.gameBase;

/**
 * Created by Kevin on 2015/8/10.
 */
public class ApiResult<T extends BaseJson> {

    T result;

    int status;

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
        return "Result{" +
                "result=" + result +
                '}';
    }
}
