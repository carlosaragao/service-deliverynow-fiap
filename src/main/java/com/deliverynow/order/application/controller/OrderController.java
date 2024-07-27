package com.deliverynow.order.application.controller;

import com.deliverynow.order.adapters.api.request.OrderRequest;
import com.deliverynow.order.adapters.api.request.OrderUpdateStatusRequest;
import com.deliverynow.order.adapters.api.response.CheckoutResponse;
import com.deliverynow.order.adapters.api.response.OrderResponse;

import java.util.List;

public interface OrderController {
    CheckoutResponse orderCheckout(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
    OrderResponse getResumeOrder(String id);
    void updateStatusOrder(OrderUpdateStatusRequest orderRequest);
}
