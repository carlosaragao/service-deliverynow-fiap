package com.deliverynow.product.application.usecase.impl;

import com.deliverynow.product.adapters.controller.request.ItemProductRequest;
import com.deliverynow.product.adapters.controller.request.ItemRequest;
import com.deliverynow.product.application.usecase.SelectItemUseCase;
import com.deliverynow.product.domain.entity.Item;
import com.deliverynow.product.domain.entity.Product;
import com.deliverynow.product.domain.gateway.ItemGateway;
import com.deliverynow.product.domain.gateway.ProductGateway;

public class SelectItemUseCaseImpl implements SelectItemUseCase {

    private final ItemGateway itemGateway;
    private final ProductGateway productGateway;

    public SelectItemUseCaseImpl(ItemGateway itemGateway, ProductGateway productGateway) {
        this.itemGateway = itemGateway;
        this.productGateway = productGateway;
    }

    @Override
    public void selectItem(ItemRequest itemRequest) {

        itemRequest.getProducts().forEach(item -> {
            var productById = productGateway.getProductById(item.getProductId());
            productById.ifPresent(product -> {
                var newItem = buildItem(itemRequest, item, product);
                itemGateway.selectItem(newItem);
            });
        });
    }

    private Item buildItem(ItemRequest itemRequest, ItemProductRequest item, Product product) {
        var newItem = Item.builder()
                .name(product.getName())
                .quantity(item.getQuantity())
                .unitPrice(product.getPrice())
                .productId(product.getId())
                .description(product.getDescription())
                .customerId(itemRequest.getSessionId())
                .category(product.getCategory().getDescription())
                .build();
        newItem.setTotalPrice();
        return newItem;
    }
}
