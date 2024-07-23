package com.deliverynow.order.application.usecase.impl;

import com.deliverynow.order.adapters.controller.response.OrderResponse;
import com.deliverynow.order.application.exception.OrderException;
import com.deliverynow.order.application.mapper.ItemOrderMapper;
import com.deliverynow.order.application.mapper.OrderMapper;
import com.deliverynow.order.application.usecase.ResumeOrderUseCase;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.product.domain.gateway.ItemGateway;
import com.deliverynow.user.domain.gateway.CustomerGateway;

public class ResumeOrderUseCaseImpl implements ResumeOrderUseCase {

    ItemGateway itemGateway;
    CustomerGateway customerGateway;
    ItemOrderMapper itemOrderMapper;
    OrderMapper orderMapper;

    public ResumeOrderUseCaseImpl(ItemGateway itemGateway, CustomerGateway customerGateway, ItemOrderMapper itemOrderMapper, OrderMapper orderMapper) {
        this.itemGateway = itemGateway;
        this.customerGateway = customerGateway;
        this.itemOrderMapper = itemOrderMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponse getResumeOrder(String customerId) {

        var itemsByCustomer = itemGateway.getItemsByCustomer(customerId);
        if (!itemsByCustomer.isEmpty()) {
            var customer = customerGateway.getCustomerById(customerId);
            var order = Order.builder()
                    .items(itemOrderMapper.itemsToItemsOrder(itemsByCustomer))
                    .customer(orderMapper.customerToCustomerOrder(customer))
                    .build();
            order.calculatedTotal();
            return orderMapper.domainToResponse(order);
        } else {
            throw new OrderException("Item not found for the id provided");
        }
    }
}
