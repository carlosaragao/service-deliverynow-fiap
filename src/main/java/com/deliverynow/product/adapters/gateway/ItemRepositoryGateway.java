package com.deliverynow.product.adapters.gateway;

import com.deliverynow.product.domain.gateway.ItemGateway;
import com.deliverynow.product.infrastructure.repository.ItemRepository;
import com.deliverynow.product.infrastructure.repository.ProductRepository;
import com.deliverynow.product.infrastructure.repository.entity.ItemEntity;
import com.deliverynow.product.infrastructure.repository.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ItemRepositoryGateway implements ItemGateway {

    ItemRepository itemRepository;
    ProductRepository productRepository;

    public ItemRepositoryGateway(ItemRepository itemRepository, ProductRepository productRepository) {
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void selectItem(List<String> productIds, String itemId) {

        var productEntityList = productIds.stream()
                .map(id -> productRepository.getProductByProductId(id))
                .collect(Collectors.toList());

        var itemEntity = ItemEntity.builder()
                .itemId(itemId)
                .listProducts(productEntityList)
                .build();

        itemRepository.persist(itemEntity);
    }
}
