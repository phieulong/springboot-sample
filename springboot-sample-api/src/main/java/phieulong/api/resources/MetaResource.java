package phieulong.api.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MetaResource {

    private int code;
    private String message;
    private PaginationResource page;

    public MetaResource(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class PaginationResource {
        private Integer currentPage;
        private Integer pageSize;
        private Long totalItems;
        private Integer totalPages;
    }
}
