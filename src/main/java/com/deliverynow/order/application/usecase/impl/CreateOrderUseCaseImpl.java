package com.deliverynow.order.application.usecase.impl;

import com.deliverynow.order.adapters.controller.request.OrderRequest;
import com.deliverynow.order.application.mapper.OrderMapperV2;
import com.deliverynow.order.application.usecase.CreateOrderUseCase;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.domain.entity.PaymentEnum;
import com.deliverynow.order.domain.gateway.OrderGateway;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {


    OrderGateway orderGateway;
    OrderMapperV2 orderMapperV2;

    public CreateOrderUseCaseImpl(OrderGateway orderGateway, OrderMapperV2 orderMapperV2) {
        this.orderGateway = orderGateway;
        this.orderMapperV2 = orderMapperV2;
    }

    @Override
    public String createdOrder(OrderRequest orderRequest) {

        var newOrder = orderMapperV2.requestToDomain(orderRequest);
        newOrder.setOrderId();
        newOrder.setCreateDate();
        newOrder.setStatusOrder(OrderStatusEnum.AGUARDANDO_PAGAMENTO);
        newOrder.setPayment(orderRequest.getPayment().getMethod(), PaymentEnum.PENDENTE);
        orderGateway.insertOrder(newOrder);

        return newOrder.getOrderId();
    }
}
