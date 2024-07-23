package com.deliverynow.order.infrastructure.config;


import com.deliverynow.order.adapters.gateway.PaymentRepositoryGateway;
import com.deliverynow.order.application.mapper.ItemOrderMapper;
import com.deliverynow.order.application.mapper.OrderMapper;
import com.deliverynow.order.application.mapper.PaymentMapper;
import com.deliverynow.order.application.usecase.UpdateStatusOrderUseCase;
import com.deliverynow.order.application.usecase.impl.*;
import com.deliverynow.order.domain.gateway.OrderGateway;
import com.deliverynow.order.domain.gateway.PaymentGateway;
import com.deliverynow.order.domain.gateway.ProcessPaymentGateway;
import com.deliverynow.order.domain.gateway.QrCodePaymentGateway;
import com.deliverynow.product.domain.gateway.ItemGateway;
import com.deliverynow.user.domain.gateway.CustomerGateway;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
public class Config {

    @Default
    public ResumeOrderUseCaseImpl resumeOrderUseCase(ItemGateway itemGateway, CustomerGateway customerGateway, ItemOrderMapper itemOrderMapper, OrderMapper orderMapper) {
        return new ResumeOrderUseCaseImpl(itemGateway, customerGateway, itemOrderMapper, orderMapper);
    }

    @Default
    public CreateOrderUseCaseImpl createOrderUseCase(OrderGateway orderGateway, OrderMapper orderMapper) {
        return new CreateOrderUseCaseImpl(orderGateway, orderMapper);
    }

    @Default
    public GetOrderByStatusUseCaseImpl getOrderByStatusUseCase(OrderGateway orderGateway, OrderMapper orderMapper) {
        return new GetOrderByStatusUseCaseImpl(orderGateway, orderMapper);
    }

    @Default
    public GenerateQrCodeUseCaseImpl generateQrCodeUseCase(OrderGateway orderGateway, QrCodePaymentGateway qrCodePaymentGateway) {
        return new GenerateQrCodeUseCaseImpl(orderGateway, qrCodePaymentGateway);
    }

    @Default
    public ProcessPaymentUseCaseImpl processPaymentUseCase(PaymentMapper paymentMapper, ProcessPaymentGateway processPaymentGateway, UpdateStatusOrderUseCase updateStatusOrderUseCase, PaymentRepositoryGateway paymentRepositoryGateway) {
        return new ProcessPaymentUseCaseImpl(paymentMapper, processPaymentGateway, updateStatusOrderUseCase, paymentRepositoryGateway);
    }

    @Default
    public UpdateStatusOrderUseCaseImpl updateStatusOrderUseCase(OrderGateway orderGateway) {
        return new UpdateStatusOrderUseCaseImpl(orderGateway);
    }

    @Default
    public PaymentStatusUseCaseImpl paymentStatusUseCase(PaymentGateway paymentGateway, PaymentMapper paymentMapper) {
        return new PaymentStatusUseCaseImpl(paymentGateway, paymentMapper);
    }
}
