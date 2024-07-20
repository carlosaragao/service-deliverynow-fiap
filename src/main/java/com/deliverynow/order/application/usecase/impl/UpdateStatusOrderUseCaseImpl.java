package com.deliverynow.order.application.usecase.impl;

import com.deliverynow.order.adapters.controller.request.OrderUpdateStatusRequest;
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
    public void updateStatus(OrderUpdateStatusRequest orderUpdateStatusRequest) {
        var orderOption = orderGateway.getOrderById(orderUpdateStatusRequest.getOrderId());
        orderOption.ifPresentOrElse(order -> {
            order.setStatusOrder(OrderStatusEnum.getStatus(orderUpdateStatusRequest.getStatus()));
            orderGateway.updateOrder(order);
                },
                () -> {
                    throw new OrderException("Order not found for update");
                });
    }
}
