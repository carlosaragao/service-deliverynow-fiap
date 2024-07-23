package com.deliverynow.order.application.usecase;

import com.deliverynow.order.adapters.controller.response.PaymentResponse;

public interface PaymentStatusUseCase {

    PaymentResponse getPaymentByStatus(String orderId);
}
