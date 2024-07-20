package com.deliverynow.order.application.usecase;

import com.deliverynow.order.adapters.controller.response.QrCodePaymentResponse;

public interface GenerateQrCodeUseCase {

    QrCodePaymentResponse getQrCode(String orderId);

}
