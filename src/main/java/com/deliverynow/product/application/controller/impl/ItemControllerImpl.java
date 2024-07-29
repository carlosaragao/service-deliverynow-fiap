package com.deliverynow.product.application.controller.impl;

import com.deliverynow.product.adapters.controller.request.ItemRequest;
import com.deliverynow.product.application.controller.ItemController;
import com.deliverynow.product.application.usecase.RemoveItemUseCase;
import com.deliverynow.product.application.usecase.SelectItemUseCase;

public class ItemControllerImpl implements ItemController {

    SelectItemUseCase selectItemUseCase;
    RemoveItemUseCase removeItemUseCase;

    public ItemControllerImpl(SelectItemUseCase selectItemUseCase, RemoveItemUseCase removeItemUseCase) {
        this.selectItemUseCase = selectItemUseCase;
        this.removeItemUseCase = removeItemUseCase;
    }

    @Override
    public void selectProduct(ItemRequest itemRequest) {
        selectItemUseCase.selectItem(itemRequest);
    }

    @Override
    public void removeItem(String id) {
        removeItemUseCase.removeItem(id);
    }
}
