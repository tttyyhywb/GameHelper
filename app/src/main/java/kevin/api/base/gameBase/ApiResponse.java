package kevin.api.base.gameBase;

/**
 * Created by Kevin on 2015/7/23.
 */
public class ApiResponse<T extends BaseJson> {

    T response;

    public ApiResponse(T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "response=" + response +
                '}';
    }
}