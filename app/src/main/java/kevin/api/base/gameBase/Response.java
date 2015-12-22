package kevin.api.base.gameBase;

/**
 * Created by Kevin on 2015/7/23.
 */
public class Response<T extends BaseJson> {

    T response;

    public Response(T response) {
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