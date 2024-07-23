package com.deliverynow.order.adapters.gateway;


import com.deliverynow.order.application.mapper.OrderMapper;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.domain.gateway.OrderGateway;
import com.deliverynow.order.infrastructure.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

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
        var newOrder = orderMapper.domainToEntity(order);
        orderRepository.persist(newOrder);
    }

    @Override
    public List<Order> listOrders() {
        var orderEntities = orderRepository.listAll();
        return orderEntities.stream()
                .map(order -> orderMapper.entityToDomain(order)).toList();
    }

    @Override
    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findByOrderId(orderId)
                .map(orderEntity -> orderMapper.entityToDomain(orderEntity));
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.update("statusOrder", order.getStatusOrder())
                .where("orderId", order.getOrderId());
    }
}
