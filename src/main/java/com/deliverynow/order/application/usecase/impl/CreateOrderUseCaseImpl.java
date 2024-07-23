package com.deliverynow.order.application.usecase.impl;

import com.deliverynow.order.adapters.controller.request.OrderRequest;
import com.deliverynow.order.application.mapper.OrderMapper;
import com.deliverynow.order.application.usecase.CreateOrderUseCase;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.domain.gateway.OrderGateway;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {


    OrderGateway orderGateway;
    OrderMapper orderMapper;

    public CreateOrderUseCaseImpl(OrderGateway orderGateway, OrderMapper orderMapper) {
        this.orderGateway = orderGateway;
        this.orderMapper = orderMapper;
    }

    @Override
    public String createdOrder(OrderRequest orderRequest) {

        var newOrder = orderMapper.requestToDomain(orderRequest);
        newOrder.setOrderId();
        newOrder.setCreateDate();
        newOrder.setStatusOrder(OrderStatusEnum.AGUARDANDO_PAGAMENTO);
        orderGateway.insertOrder(newOrder);

        return newOrder.getOrderId();
    }
}
