package com.deliverynow.order.application.usecase;

import com.deliverynow.order.adapters.controller.request.OrderRequest;

public interface CreateOrderUseCase {

    String createdOrder(OrderRequest orderRequest);
}
