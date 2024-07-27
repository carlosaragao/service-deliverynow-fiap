package com.deliverynow.order.domain.usecase;

import com.deliverynow.order.adapters.api.response.QrCodePaymentResponse;

public interface GenerateQrCodeUseCase {

    QrCodePaymentResponse getQrCode(String orderId);

}
