package kevin.api.base.gameBase;

/**
 * Created by Kevin on 2015/8/10.
 */
public class Result<T extends BaseJson> {

    T result;

    public Result(T result) {
      this.result=result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                '}';
    }
}
