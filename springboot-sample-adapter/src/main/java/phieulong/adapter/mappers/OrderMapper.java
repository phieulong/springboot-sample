package phieulong.adapter.mappers;

import org.mapstruct.Mapper;
import phieulong.adapter.mysql.models.OrderModel;
import phieulong.core.entities.OrderEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    public abstract OrderEntity fromModel(OrderModel order);
    public abstract List<OrderEntity> fromModels(List<OrderModel> orders);
    public abstract OrderModel fromEntity(OrderEntity order);
}
