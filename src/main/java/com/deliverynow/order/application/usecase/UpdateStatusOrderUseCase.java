package com.deliverynow.order.application.usecase;

public interface UpdateStatusOrderUseCase {

    void updateStatus(String orderId, String status);
}
