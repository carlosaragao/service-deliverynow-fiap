package com.deliverynow.order.application.usecase.impl;

import com.deliverynow.order.adapters.controller.response.QrCodePaymentResponse;
import com.deliverynow.order.application.exception.OrderException;
import com.deliverynow.order.application.usecase.GenerateQrCodeUseCase;
import com.deliverynow.order.domain.gateway.OrderGateway;
import com.deliverynow.order.domain.gateway.QrCodePaymentGateway;
import com.deliverynow.order.infrastructure.rest.response.QrCodeResponse;

import java.time.LocalDateTime;

public class GenerateQrCodeUseCaseImpl implements GenerateQrCodeUseCase {

    OrderGateway orderGateway;
    QrCodePaymentGateway qrCodePaymentGateway;

    public GenerateQrCodeUseCaseImpl(OrderGateway orderGateway, QrCodePaymentGateway qrCodePaymentGateway) {
        this.orderGateway = orderGateway;
        this.qrCodePaymentGateway = qrCodePaymentGateway;
    }

    @Override
    public QrCodePaymentResponse getQrCode(String orderId) {
        var order = orderGateway.getOrderById(orderId);
        return order.map(o -> {
                    var qrCodeResponse = qrCodePaymentGateway.processPayment(o);
                    return QrCodePaymentResponse.builder()
                            .dateExpiration(LocalDateTime.now().plusMinutes(10L).toString())
                            .qrCode(qrCodeResponse.getQrData())
                            .inStoreOrderId(qrCodeResponse.getInStoreOrderId())
                            .build();
                }
        ).orElseThrow(() -> new OrderException("Order not found to generate qrcode"));
    }
}
