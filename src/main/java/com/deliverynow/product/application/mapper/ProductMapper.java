package com.deliverynow.product.application.mapper;

import com.deliverynow.product.adapters.controller.request.ProductRequest;
import com.deliverynow.product.adapters.controller.response.ProductResponse;
import com.deliverynow.product.domain.entity.Product;
import com.deliverynow.product.infrastructure.repository.entity.ProductEntity;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "jakarta", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {


    @Mapping(target = "id", expression = "java(stringToObjectId(product.getId()))")
    ProductEntity productToProductEntity(Product product);
    Product requestToDomain(ProductRequest productRequest);

    @Mapping(target = "id", expression = "java(objectIdToString(productEntity.getId()))")
    Product entityToDomain(ProductEntity productEntity);
    ProductResponse domainToResponse(Product product);


    default String objectIdToString(ObjectId objectId) {
        return objectId == null ? null : objectId.toHexString();
    }

    default ObjectId stringToObjectId(String id) {
        return id == null ? null : new ObjectId(id);
    }

}
