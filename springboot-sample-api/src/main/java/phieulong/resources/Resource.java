package phieulong.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Setter
@Getter
public class Resource<T> {
    private T data;
    private MetaResource meta;

    public Resource(int code, String message) {
        this.meta = new MetaResource(code, message);
    }

    public Resource(T data) {
        this.meta = new MetaResource(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
        this.data = data;
    }

    public Resource(T data, MetaResource.PaginationResource pagination) {
        this.meta = new MetaResource(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), pagination);
        this.data = data;
    }
}
