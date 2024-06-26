package com.deliverynow.order.domain.gateway;

import com.deliverynow.order.domain.entity.Order;

import java.util.List;

public interface OrderGateway {

    void insertOrder(Order order);
    List<Order> listOrders();
}
