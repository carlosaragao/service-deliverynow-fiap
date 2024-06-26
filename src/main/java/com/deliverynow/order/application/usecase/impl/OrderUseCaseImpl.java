package com.deliverynow.order.application.usecase.impl;

import com.deliverynow.order.adapters.controller.request.OrderRequest;
import com.deliverynow.order.application.usecase.OrderUseCase;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.domain.entity.PaymentEnum;
import com.deliverynow.order.domain.gateway.OrderGateway;
import com.deliverynow.order.domain.gateway.ProcessPaymentGateway;


import java.util.List;

public class OrderUseCaseImpl implements OrderUseCase {

    private OrderGateway orderGateway;
    private ProcessPaymentGateway processPaymentGateway;

    public OrderUseCaseImpl(OrderGateway orderGateway, ProcessPaymentGateway processPaymentGateway) {
        this.orderGateway = orderGateway;
        this.processPaymentGateway = processPaymentGateway;
    }

    @Override
    public String checkoutOrder(OrderRequest request) {

        return null;
    }

    @Override
    public List<Order> listOrder() {
        return orderGateway.listOrders();
    }
}
