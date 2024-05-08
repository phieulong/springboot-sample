package phieulong.adapter.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import phieulong.core.entities.OrderEntity;
import phieulong.core.entities.PageEntity;
import phieulong.core.filters.PageFilter;
import phieulong.core.usecases.GetOrderUseCase;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final GetOrderUseCase getOrderUseCase;

    public PageEntity<OrderEntity> getListOrder(String userId, PageFilter filter) {
        return getOrderUseCase.getListOrder(userId, filter);
    }
}
