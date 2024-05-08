package phieulong.core.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import phieulong.core.entities.OrderEntity;
import phieulong.core.ports.IOrderRepositoryPort;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final IOrderRepositoryPort orderRepositoryPort;

    public OrderEntity createOrder(OrderEntity orderEntity) {
        return orderRepositoryPort.save(orderEntity);
    }
}
