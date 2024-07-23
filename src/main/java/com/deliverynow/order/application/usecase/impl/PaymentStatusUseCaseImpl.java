package com.deliverynow.order.application.usecase.impl;

import com.deliverynow.order.adapters.controller.response.PaymentResponse;
import com.deliverynow.order.application.exception.PaymentException;
import com.deliverynow.order.application.mapper.PaymentMapper;
import com.deliverynow.order.application.usecase.PaymentStatusUseCase;
import com.deliverynow.order.domain.gateway.PaymentGateway;

public class PaymentStatusUseCaseImpl implements PaymentStatusUseCase {

    PaymentGateway paymentGateway;
    PaymentMapper paymentMapper;

    public PaymentStatusUseCaseImpl(PaymentGateway paymentGateway, PaymentMapper paymentMapper) {
        this.paymentGateway = paymentGateway;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentResponse getPaymentByStatus(String orderId) {

        return paymentGateway.getByOrderId(orderId)
                .map(payment -> paymentMapper.domainToResponse(payment))
                .orElseThrow(() -> new PaymentException(String.format("Payment not found for orderId: %s", orderId)));
    }
}
