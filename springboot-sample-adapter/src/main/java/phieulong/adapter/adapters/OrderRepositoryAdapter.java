package phieulong.adapter.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import phieulong.adapter.mappers.OrderMapper;
import phieulong.adapter.mysql.models.OrderModel;
import phieulong.adapter.mysql.repositories.OrderRepository;
import phieulong.core.entities.OrderEntity;
import phieulong.core.entities.PageEntity;
import phieulong.core.ports.IOrderRepositoryPort;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements IOrderRepositoryPort {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public PageEntity<OrderEntity> getList(String userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(0, size);

        Specification<OrderModel> orderModelSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (userId != null) {
                predicates.add(criteriaBuilder.equal(root.get("userId"), userId));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<OrderModel> orderModelsPage = orderRepository.findAll(orderModelSpecification, pageable);

        return new PageEntity<>(orderMapper.fromModels(orderModelsPage.getContent()),
                page, size,
                orderModelsPage.getTotalElements(), orderModelsPage.getTotalPages());
    }

    @Override
    public OrderEntity save(OrderEntity order) {
        return orderMapper.fromModel(orderRepository.save(orderMapper.fromEntity(order)));
    }
}
