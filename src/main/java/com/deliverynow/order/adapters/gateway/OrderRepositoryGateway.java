package com.deliverynow.order.adapters.gateway;


import com.deliverynow.order.application.mapper.OrderMapperV2;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.domain.gateway.OrderGateway;
import com.deliverynow.order.infrastructure.repository.OrderRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class OrderRepositoryGateway implements OrderGateway {

    OrderRepository orderRepository;
    OrderMapperV2 orderMapper;

    public OrderRepositoryGateway(OrderRepository orderRepository, OrderMapperV2 orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public void insertOrder(Order order) {
        var newOrder = orderMapper.domainToEntity(order);
        orderRepository.persist(newOrder);
    }

    @Override
    public List<Order> listOrders() {
        var orderEntities = orderRepository.listAll();
        return orderEntities.stream()
                .map(order -> orderMapper.entityToDomain(order)).toList();
    }
}
