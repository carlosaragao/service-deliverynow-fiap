package com.deliverynow.product.adapters.gateway;

import com.deliverynow.product.application.mapper.ItemMapper;
import com.deliverynow.product.domain.entity.Item;
import com.deliverynow.product.domain.gateway.ItemGateway;
import com.deliverynow.product.infrastructure.repository.ItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class ItemRepositoryGateway implements ItemGateway {

    ItemRepository itemRepository;
    ItemMapper itemMapper;

    public ItemRepositoryGateway(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public void selectItem(Item item) {

        itemRepository.persist(itemMapper.domainToEntity(item));
    }

    @Override
    public void deletedItem(String itemId) {
        itemRepository.deleteById(new ObjectId(itemId));
    }

    @Override
    public List<Item> getItemsByCustomer(String customerId) {
        var itemEntityList = itemRepository.getItemByCustomer(customerId);
        return itemEntityList.stream()
                .map(itemEntity -> itemMapper.entityToDomain(itemEntity))
                .toList();
    }
}
