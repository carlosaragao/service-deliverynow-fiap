package com.deliverynow.order.domain.usecase;

import com.deliverynow.order.adapters.api.request.OrderRequest;

public interface CreateOrderUseCase {

    String createdOrder(String sessionId);
}
