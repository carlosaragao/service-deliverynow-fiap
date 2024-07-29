package com.deliverynow.product.adapters.gateway;

import com.deliverynow.product.application.presenter.ItemPresenter;
import com.deliverynow.product.domain.entity.Item;
import com.deliverynow.product.domain.gateway.ItemGateway;
import com.deliverynow.product.infrastructure.repository.ItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ItemRepositoryGateway implements ItemGateway {

    ItemRepository itemRepository;
    ItemPresenter itemPresenter;

    public ItemRepositoryGateway(ItemRepository itemRepository, ItemPresenter itemPresenter) {
        this.itemRepository = itemRepository;
        this.itemPresenter = itemPresenter;
    }

    @Override
    public void selectItem(Item item) {
        itemRepository.persist(itemPresenter.domainToEntity(item));
    }

    @Override
    public Optional<Item> getItemById(String id) {
        return itemRepository
                .findByIdOptional(new ObjectId(id))
                .map(itemEntity -> itemPresenter.entityToDomain(itemEntity));
    }

    @Override
    public void deletedItem(String itemId) {
        itemRepository.deleteById(new ObjectId(itemId));
    }

    @Override
    public List<Item> getItemsBySession(String customerId) {
        var itemEntityList = itemRepository.getItemByCustomer(customerId);
        return itemEntityList.stream()
                .map(itemEntity -> itemPresenter.entityToDomain(itemEntity))
                .toList();
    }
}
