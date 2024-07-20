package com.deliverynow.order.application.usecase;

import com.deliverynow.order.adapters.controller.request.OrderUpdateStatusRequest;

public interface UpdateStatusOrderUseCase {

    void updateStatus(OrderUpdateStatusRequest orderUpdateStatusRequest);
}
