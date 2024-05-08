package phieulong.core.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import phieulong.core.entities.OrderEntity;
import phieulong.core.entities.PageEntity;
import phieulong.core.filters.PageFilter;
import phieulong.core.ports.IOrderRepositoryPort;

@Service
@RequiredArgsConstructor
public class GetOrderUseCase {

    private final IOrderRepositoryPort orderRepositoryPort;

    public PageEntity<OrderEntity> getListOrder(String userIdFilter, PageFilter pageFilter) {
        return orderRepositoryPort
                .getList(userIdFilter, pageFilter.getPageSize(),pageFilter.getPageNumber());
    }
}
