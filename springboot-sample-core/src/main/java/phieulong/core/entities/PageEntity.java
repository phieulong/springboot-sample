package phieulong.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageEntity<T> {

    private List<T> data;
    private Integer currentPage;
    private Integer pageSize;
    private Long totalItems;
    private Integer totalPages;
}