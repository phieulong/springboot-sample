package phieulong.api.mapers;

import org.mapstruct.Mapper;
import phieulong.api.responses.OrderResponse;
import phieulong.core.entities.OrderEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderResponseMapper {

    public abstract OrderResponse mapOrderEntityToOrderResponse(OrderEntity entity);
    public abstract List<OrderResponse> mapOrderEntitiesToOrderResponses(List<OrderEntity> entities);
}
