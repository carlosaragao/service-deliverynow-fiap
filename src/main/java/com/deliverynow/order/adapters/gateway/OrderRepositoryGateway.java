package com.deliverynow.order.adapters.gateway;


import com.deliverynow.order.application.mapper.OrderMapper;
import com.deliverynow.order.infrastructure.repository.OrderRepository;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.domain.gateway.OrderGateway;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderRepositoryGateway implements OrderGateway {

    OrderRepository orderRepository;
    OrderMapper orderMapper;

    public OrderRepositoryGateway(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public void insertOrder(Order order) {
        var newOrder = orderMapper.orderToOrderEntity(order);
        orderRepository.persist(newOrder);
    }

    @Override
    public List<Order> listOrders() {
        var orderEntities = orderRepository.listAll();
        return orderEntities.stream()
                .map(order -> orderMapper.orderEntityToOrder(order)).collect(Collectors.toList());
    }
}
