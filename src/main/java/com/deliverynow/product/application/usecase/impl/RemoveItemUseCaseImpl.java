package com.deliverynow.product.application.usecase.impl;

import com.deliverynow.product.application.usecase.RemoveItemUseCase;
import com.deliverynow.product.domain.gateway.ItemGateway;

public class RemoveItemUseCaseImpl implements RemoveItemUseCase {

    ItemGateway itemGateway;

    public RemoveItemUseCaseImpl(ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }

    @Override
    public void removeItem(String itemId) {
        itemGateway.deletedItem(itemId);
    }
}
