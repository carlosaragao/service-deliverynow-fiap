package com.deliverynow.product.application.mapper;

import com.deliverynow.product.domain.entity.Item;
import com.deliverynow.product.infrastructure.repository.entity.ItemEntity;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "jakarta", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ItemMapper {

    ItemEntity domainToEntity(Item item);

    @Mapping(target = "itemId", expression = "java(objectIdToString(itemEntity.getId()))")
    Item entityToDomain(ItemEntity itemEntity);

    default String objectIdToString(ObjectId objectId) {
        return objectId == null ? null : objectId.toHexString();
    }
}
