package com.deliverynow.order.application.usecase.impl;

import com.deliverynow.order.adapters.controller.response.OrderResponse;
import com.deliverynow.order.application.mapper.OrderMapper;
import com.deliverynow.order.application.usecase.GetOrderByStatusUseCase;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.domain.gateway.OrderGateway;

import java.util.List;

public class GetOrderByStatusUseCaseImpl implements GetOrderByStatusUseCase {

    OrderGateway orderGateway;
    OrderMapper orderMapper;

    public GetOrderByStatusUseCaseImpl(OrderGateway orderGateway, OrderMapper orderMapper) {
        this.orderGateway = orderGateway;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderResponse> getOrderByStatus() {
        var orders = orderGateway.listOrders();
        return orders.stream()
                .filter(order -> order.getStatusOrder() != OrderStatusEnum.FINALIZADO &&
                        order.getStatusOrder() != OrderStatusEnum.AGUARDANDO_PAGAMENTO)
                .sorted(Order.COMPARATOR)
                .map(order -> orderMapper.domainToResponse(order))
                .toList();
    }
}
