package com.deliverynow.order.domain.gateway;

import com.deliverynow.order.domain.entity.Payment;

public interface ProcessPaymentGateway {

    Payment processPayment(Payment payment);
}
