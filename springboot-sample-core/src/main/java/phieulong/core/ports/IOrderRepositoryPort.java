package phieulong.core.ports;

import phieulong.core.entities.OrderEntity;
import phieulong.core.entities.PageEntity;

public interface IOrderRepositoryPort {
    PageEntity<OrderEntity> getList(String userId, Integer page, Integer pageSize);
}
