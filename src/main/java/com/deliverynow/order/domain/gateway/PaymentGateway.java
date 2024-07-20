package com.deliverynow.order.domain.gateway;

import com.deliverynow.order.infrastructure.rest.response.PaymentResponse;

public interface PaymentGateway {

    PaymentResponse processPayment(String orderId);
}
