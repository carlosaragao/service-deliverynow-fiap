package com.deliverynow.product.domain.gateway;

import com.deliverynow.product.domain.entity.Item;

import java.util.List;

public interface ItemGateway {

    void selectItem(Item item);
    void deletedItem(String itemId);

    List<Item> getItemsByCustomer(String customerId);
}
