package com.deliverynow.order.application.controller;

import com.deliverynow.order.adapters.api.response.PaymentResponse;
import com.deliverynow.order.adapters.api.response.QrCodePaymentResponse;

public interface PaymentController {

    PaymentResponse getPayment(String id);
    QrCodePaymentResponse getQrCodeOrder(String id);
    void process(String payload);
}
