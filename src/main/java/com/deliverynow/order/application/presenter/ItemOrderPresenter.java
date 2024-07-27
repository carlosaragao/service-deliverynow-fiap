package com.deliverynow.order.application.presenter;

import com.deliverynow.order.domain.entity.ItemOrder;
import com.deliverynow.product.domain.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "jakarta", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ItemOrderPresenter {

    ItemOrder itemToItemOrder(Item item);
    List<ItemOrder> itemsToItemsOrder(List<Item> items);
}
