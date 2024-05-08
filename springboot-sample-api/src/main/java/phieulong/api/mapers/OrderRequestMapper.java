package phieulong.api.mapers;

import org.mapstruct.Mapper;
import phieulong.api.requests.CreateOrderRequest;
import phieulong.api.requests.UpdateOrderRequest;
import phieulong.core.entities.OrderEntity;

@Mapper(componentModel = "spring")
public abstract class OrderRequestMapper {

    public abstract OrderEntity mapCreateOrderRequestToOrderEntity(CreateOrderRequest request);
    public abstract OrderEntity mapUpdateOrderRequestToOrderEntity(UpdateOrderRequest request);
}
