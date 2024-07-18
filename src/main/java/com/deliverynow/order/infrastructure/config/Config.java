package com.deliverynow.order.infrastructure.config;


import com.deliverynow.order.application.mapper.ItemOrderMapper;
import com.deliverynow.order.application.mapper.OrderMapperV2;
import com.deliverynow.order.application.usecase.impl.CreateOrderUseCaseImpl;
import com.deliverynow.order.application.usecase.impl.GetOrderByStatusUseCaseImpl;
import com.deliverynow.order.application.usecase.impl.ResumeOrderUseCaseImpl;
import com.deliverynow.order.domain.gateway.OrderGateway;
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
}
