package com.deliverynow.order.application.usecase;

import com.deliverynow.order.adapters.controller.response.OrderResponse;

import java.util.List;

public interface GetOrderByStatusUseCase {

    List<OrderResponse> getOrderByStatus();
}
