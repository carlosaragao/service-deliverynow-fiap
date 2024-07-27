package com.deliverynow.product.application.usecase.impl;

import com.deliverynow.product.application.exception.ProductException;
import com.deliverynow.product.application.usecase.RemoveItemUseCase;
import com.deliverynow.product.domain.entity.Item;
import com.deliverynow.product.domain.gateway.ItemGateway;

import java.util.Optional;

public class RemoveItemUseCaseImpl implements RemoveItemUseCase {

    ItemGateway itemGateway;

    public RemoveItemUseCaseImpl(ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }

    @Override
    public void removeItem(String itemId) {
        Optional<Item> itemById = itemGateway.getItemById(itemId);
        if (itemById.isPresent()) {
            itemGateway.deletedItem(itemId);
        } else {
            throw new ProductException("Item not found or removed");
        }
    }
}
