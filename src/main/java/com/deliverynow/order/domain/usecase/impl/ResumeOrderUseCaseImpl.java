package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.application.exception.OrderException;
import com.deliverynow.order.application.presenter.ItemOrderPresenter;
import com.deliverynow.order.application.presenter.OrderPresenter;
import com.deliverynow.order.domain.usecase.ResumeOrderUseCase;
import com.deliverynow.order.domain.entity.CustomerOrder;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.product.domain.gateway.ItemGateway;
import com.deliverynow.user.domain.gateway.CustomerGateway;

public class ResumeOrderUseCaseImpl implements ResumeOrderUseCase {

    ItemGateway itemGateway;
    CustomerGateway customerGateway;
    ItemOrderPresenter itemOrderPresenter;
    OrderPresenter orderPresenter;

    public ResumeOrderUseCaseImpl(ItemGateway itemGateway, CustomerGateway customerGateway, ItemOrderPresenter itemOrderPresenter, OrderPresenter orderPresenter) {
        this.itemGateway = itemGateway;
        this.customerGateway = customerGateway;
        this.itemOrderPresenter = itemOrderPresenter;
        this.orderPresenter = orderPresenter;
    }

    @Override
    public Order getResumeOrder(String sessionId) {

        var itemsByCustomer = itemGateway.getItemsBySession(sessionId);
        if (!itemsByCustomer.isEmpty()) {
            var order = Order.builder()
                    .items(itemOrderPresenter.itemsToItemsOrder(itemsByCustomer))
                    .customer(getCustomer(sessionId))
                    .build();
            order.calculatedTotal();
            return order;
        } else {
            throw new OrderException("Item not found for the id provided");
        }
    }

    private CustomerOrder getCustomer(String sessionId) {
        var customerById = customerGateway.getCustomerById(sessionId);
        return customerById.map(customer -> orderPresenter.customerToCustomerOrder(customer)).orElse(null);
    }
}
