package com.deliverynow.product.application.usecase.impl;

import com.deliverynow.product.adapters.controller.request.ProductRequest;
import com.deliverynow.product.application.exception.ProductException;
import com.deliverynow.product.application.mapper.ProductMapper;
import com.deliverynow.product.application.usecase.CreateProductUseCase;
import com.deliverynow.product.domain.entity.CategoryEnum;
import com.deliverynow.product.domain.gateway.ProductGateway;

public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductGateway productGateway;
    private final ProductMapper productMapper;

    public CreateProductUseCaseImpl(ProductGateway productGateway, ProductMapper productMapper) {
        this.productGateway = productGateway;
        this.productMapper = productMapper;
    }

    @Override
    public void insertProduct(ProductRequest productRequest) {
        CategoryEnum.validateCategory(productRequest.category());
        var productByNome = productGateway.getProductByName(productRequest.name());

        if (productByNome.isEmpty()) {
            var product = productMapper.requestToDomain(productRequest);
            productGateway.insertProduct(product);
        } else {
            throw new ProductException("Product already exists with the name provided");
        }
    }
}
