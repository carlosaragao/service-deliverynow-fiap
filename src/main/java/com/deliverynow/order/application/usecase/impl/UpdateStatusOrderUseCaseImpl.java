package com.deliverynow.order.application.usecase.impl;

import com.deliverynow.order.application.exception.OrderException;
import com.deliverynow.order.application.usecase.UpdateStatusOrderUseCase;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.domain.gateway.OrderGateway;

public class UpdateStatusOrderUseCaseImpl implements UpdateStatusOrderUseCase {

    OrderGateway orderGateway;

    public UpdateStatusOrderUseCaseImpl(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    @Override
    public void updateStatus(String orderId, String status) {
        var orderOption = orderGateway.getOrderById(orderId);
        orderOption.ifPresentOrElse(order -> {
            order.setStatusOrder(OrderStatusEnum.getStatus(status));
            orderGateway.updateOrder(order);
                },
                () -> {
                    throw new OrderException("Order not found for update");
                });
    }
}
