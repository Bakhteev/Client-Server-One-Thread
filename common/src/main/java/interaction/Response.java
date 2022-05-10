package interaction;

import lombok.Getter;
import java.io.Serializable;

@Getter
public class Response<T> implements Serializable {
    T body;

    public Response(T body){
        this.body = body;
    }
}
