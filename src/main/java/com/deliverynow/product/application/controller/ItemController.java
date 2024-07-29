package com.deliverynow.product.application.controller;

import com.deliverynow.product.adapters.controller.request.ItemRequest;

public interface ItemController {

    void selectProduct(ItemRequest itemRequest);

    void removeItem(String id);
}
