package com.deliverynow.product.application.usecase;

import com.deliverynow.product.adapters.controller.request.ItemRequest;

public interface SelectItemUseCase {

    void selectItem(ItemRequest itemRequest);
}
