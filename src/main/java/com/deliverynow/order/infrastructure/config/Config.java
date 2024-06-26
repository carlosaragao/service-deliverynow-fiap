package com.deliverynow.order.infrastructure.config;


import com.deliverynow.order.application.usecase.impl.OrderUseCaseImpl;
import com.deliverynow.order.domain.gateway.OrderGateway;
import com.deliverynow.order.domain.gateway.ProcessPaymentGateway;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
public class Config {

    @Default
    public OrderUseCaseImpl orderUseCaseImpl(OrderGateway orderGateway, ProcessPaymentGateway processPaymentGateway){
        return new OrderUseCaseImpl(orderGateway, processPaymentGateway);
    }
}
