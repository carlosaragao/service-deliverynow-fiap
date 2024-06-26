package com.deliverynow.order.application.usecase;


import com.deliverynow.order.adapters.controller.request.OrderRequest;
import com.deliverynow.order.domain.entity.Order;

import java.util.List;

public interface OrderUseCase {

    String checkoutOrder(OrderRequest order);

    List<Order> listOrder();
}
