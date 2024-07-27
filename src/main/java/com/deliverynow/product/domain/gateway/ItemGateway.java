package com.deliverynow.product.domain.gateway;

import com.deliverynow.product.domain.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemGateway {

    void selectItem(Item item);
    void deletedItem(String itemId);

    Optional<Item> getItemById(String id);
    List<Item> getItemsBySession(String customerId);
}
