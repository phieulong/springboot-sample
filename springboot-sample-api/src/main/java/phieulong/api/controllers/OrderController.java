package phieulong.api.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import phieulong.adapter.services.OrderService;
import phieulong.api.mapers.OrderResponseMapper;
import phieulong.api.resources.MetaResource;
import phieulong.api.resources.Resource;
import phieulong.api.responses.OrderResponse;
import phieulong.core.entities.OrderEntity;
import phieulong.core.entities.PageEntity;
import phieulong.core.filters.PageFilter;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderResponseMapper orderResponseMapper;

    @GetMapping("/v1/orders")
    public Resource<List<OrderResponse>> listOrders(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetail,
            @RequestParam(name = "user_id", required = false) String userIdFilter,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNumber
    ) {
        PageFilter pageFilter = PageFilter.of(pageSize, pageNumber);

        if (userDetail.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))
                && !userDetail.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            userIdFilter = userDetail.getUsername();
        }
        PageEntity<OrderEntity> entitiesPage = orderService.getListOrder(userIdFilter, pageFilter);
        MetaResource.PaginationResource paginationResource = new MetaResource.PaginationResource(entitiesPage.getCurrentPage(),
                entitiesPage.getPageSize(), entitiesPage.getTotalItems(), entitiesPage.getTotalPages());

        return new Resource<>(orderResponseMapper.mapOrderEntitiesToOrderResponses(entitiesPage.getData()), paginationResource);
    }
}
