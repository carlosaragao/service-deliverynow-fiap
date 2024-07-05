package com.deliverynow.product.application.mapper;

import com.deliverynow.product.domain.entity.Item;
import com.deliverynow.product.infrastructure.repository.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "jakarta", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ItemMapper {

    ItemEntity domainToEntity(Item item);

    Item entityToDomain(ItemEntity itemEntity);
}
