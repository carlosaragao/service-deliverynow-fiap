package com.deliverynow.product.application.usecase.impl;

import com.deliverynow.product.adapters.controller.response.ProductResponse;
import com.deliverynow.product.application.mapper.ProductMapper;
import com.deliverynow.product.application.usecase.GetProductByCategoryUseCase;
import com.deliverynow.product.domain.entity.CategoryEnum;
import com.deliverynow.product.domain.gateway.ProductGateway;

import java.util.List;
import java.util.stream.Collectors;

public class GetProductByCategoryUseCaseImpl implements GetProductByCategoryUseCase {

    private final ProductGateway productGateway;
    private final ProductMapper productMapper;

    public GetProductByCategoryUseCaseImpl(ProductGateway productGateway, ProductMapper productMapper) {
        this.productGateway = productGateway;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponse> getProductByCategory(String category) {
        CategoryEnum.validateCategory(category.toUpperCase());
        var productByCategory = productGateway.getProductByCategory(category.toUpperCase());
        return productByCategory.stream()
                .map(productMapper::domainToResponse).toList();
    }
}
