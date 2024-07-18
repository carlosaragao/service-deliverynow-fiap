package com.deliverynow.order.application.usecase.impl;

import com.deliverynow.order.adapters.controller.response.OrderResponse;
import com.deliverynow.order.application.mapper.OrderMapperV2;
import com.deliverynow.order.application.usecase.GetOrderByStatusUseCase;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.domain.gateway.OrderGateway;

import java.util.List;

public class GetOrderByStatusUseCaseImpl implements GetOrderByStatusUseCase {

    OrderGateway orderGateway;
    OrderMapperV2 orderMapperV2;

    public GetOrderByStatusUseCaseImpl(OrderGateway orderGateway, OrderMapperV2 orderMapperV2) {
        this.orderGateway = orderGateway;
        this.orderMapperV2 = orderMapperV2;
    }

    @Override
    public List<OrderResponse> getOrderByStatus() {
        var orders = orderGateway.listOrders();
        return orders.stream()
                .filter(order -> order.getStatusOrder() != OrderStatusEnum.FINALIZADO)
                .sorted(Order.COMPARATOR)
                .map(order -> orderMapperV2.domainToResponse(order))
                .toList();
    }
}
