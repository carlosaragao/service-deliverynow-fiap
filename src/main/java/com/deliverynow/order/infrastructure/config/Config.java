package com.deliverynow.order.infrastructure.config;


import com.deliverynow.order.application.mapper.ItemOrderMapper;
import com.deliverynow.order.application.mapper.OrderMapperV2;
import com.deliverynow.order.application.usecase.impl.*;
import com.deliverynow.order.domain.gateway.OrderGateway;
import com.deliverynow.order.domain.gateway.PaymentGateway;
import com.deliverynow.order.domain.gateway.QrCodePaymentGateway;
import com.deliverynow.product.domain.gateway.ItemGateway;
import com.deliverynow.user.domain.gateway.CustomerGateway;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
public class Config {

    @Default
    public ResumeOrderUseCaseImpl resumeOrderUseCase(ItemGateway itemGateway, CustomerGateway customerGateway, ItemOrderMapper itemOrderMapper, OrderMapperV2 orderMapperV2) {
        return new ResumeOrderUseCaseImpl(itemGateway, customerGateway, itemOrderMapper, orderMapperV2);
    }

    @Default
    public CreateOrderUseCaseImpl createOrderUseCase(OrderGateway orderGateway, OrderMapperV2 orderMapperV2) {
        return new CreateOrderUseCaseImpl(orderGateway, orderMapperV2);
    }

    @Default
    public GetOrderByStatusUseCaseImpl getOrderByStatusUseCase(OrderGateway orderGateway, OrderMapperV2 orderMapperV2) {
        return new GetOrderByStatusUseCaseImpl(orderGateway, orderMapperV2);
    }

    @Default
    public GenerateQrCodeUseCaseImpl generateQrCodeUseCase(OrderGateway orderGateway, QrCodePaymentGateway qrCodePaymentGateway) {
        return new GenerateQrCodeUseCaseImpl(orderGateway, qrCodePaymentGateway);
    }

    @Default
    public ProcessPaymentUseCaseImpl processPaymentUseCase(PaymentGateway paymentGateway) {
        return new ProcessPaymentUseCaseImpl(paymentGateway);
    }

    @Default
    public UpdateStatusOrderUseCaseImpl updateStatusOrderUseCase(OrderGateway orderGateway) {
        return new UpdateStatusOrderUseCaseImpl(orderGateway);
    }
}
