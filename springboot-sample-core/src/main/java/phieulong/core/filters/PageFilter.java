package phieulong.core.filters;

import lombok.Getter;

@Getter
public class PageFilter {

    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer DEFAULT_FIRST_PAGE = 1;

    private Integer pageSize = DEFAULT_PAGE_SIZE;
    private Integer pageNumber = DEFAULT_FIRST_PAGE;

    private PageFilter() {}

    public static PageFilter of(Integer pageSize, Integer pageNumber) {
        PageFilter pageFilter = new PageFilter();
        if (pageSize != null && pageSize >= 0) {
            pageFilter.pageSize = pageSize;
        }
        if (pageNumber != null && pageNumber > 0) {
            pageFilter.pageNumber = pageNumber;
        }
        return pageFilter;
    }
}
