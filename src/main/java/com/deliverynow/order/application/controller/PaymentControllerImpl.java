package com.deliverynow.order.application.controller;

import com.deliverynow.order.adapters.api.response.PaymentResponse;
import com.deliverynow.order.adapters.api.response.QrCodePaymentResponse;
import com.deliverynow.order.application.presenter.PaymentPresenter;
import com.deliverynow.order.domain.usecase.GenerateQrCodeUseCase;
import com.deliverynow.order.domain.usecase.PaymentStatusUseCase;
import com.deliverynow.order.domain.usecase.ProcessPaymentUseCase;
import com.deliverynow.order.domain.usecase.ValidateWebhookUseCase;

import java.util.Optional;

public class PaymentControllerImpl implements PaymentController {

    GenerateQrCodeUseCase qrCodeUseCase;
    ProcessPaymentUseCase processPaymentUseCase;
    PaymentStatusUseCase paymentStatusUseCase;
    ValidateWebhookUseCase validateWebhookUseCase;
    PaymentPresenter paymentPresenter;

    public PaymentControllerImpl(GenerateQrCodeUseCase qrCodeUseCase, ProcessPaymentUseCase processPaymentUseCase, PaymentStatusUseCase paymentStatusUseCase,
                                 ValidateWebhookUseCase validateWebhookUseCase, PaymentPresenter paymentPresenter) {
        this.qrCodeUseCase = qrCodeUseCase;
        this.processPaymentUseCase = processPaymentUseCase;
        this.paymentStatusUseCase = paymentStatusUseCase;
        this.validateWebhookUseCase = validateWebhookUseCase;
        this.paymentPresenter = paymentPresenter;
    }

    @Override
    public PaymentResponse getPayment(String id) {
        var paymentByStatus = paymentStatusUseCase.getPaymentByStatus(id);
        return paymentPresenter.domainToResponse(paymentByStatus);
    }

    @Override
    public QrCodePaymentResponse getQrCodeOrder(String id) {
        return qrCodeUseCase.getQrCode(id);
    }

    @Override
    public void process(String payload) {
        var paymentIdOrder = validateWebhookUseCase.getPaymentIdOrder(payload);
        paymentIdOrder.ifPresent(order -> processPaymentUseCase.execute(order));
    }
}
