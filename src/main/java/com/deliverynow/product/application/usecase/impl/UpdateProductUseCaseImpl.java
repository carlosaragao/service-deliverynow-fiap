package com.deliverynow.product.application.usecase.impl;

import com.deliverynow.product.adapters.controller.request.ProductRequest;
import com.deliverynow.product.adapters.controller.response.ProductResponse;
import com.deliverynow.product.application.exception.ProductException;
import com.deliverynow.product.application.mapper.ProductMapper;
import com.deliverynow.product.application.usecase.UpdateProductUseCase;
import com.deliverynow.product.domain.entity.CategoryEnum;
import com.deliverynow.product.domain.gateway.ProductGateway;

public class UpdateProductUseCaseImpl implements UpdateProductUseCase {


    private final ProductGateway productGateway;
    private final ProductMapper productMapper;

    public UpdateProductUseCaseImpl(ProductGateway productGateway, ProductMapper productMapper) {
        this.productGateway = productGateway;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse updateProduct(String productId, ProductRequest productRequest) {
        CategoryEnum.validateCategory(productRequest.category());
        var productById = productGateway.getProductById(productId);
        return productById.map(pr -> {
            if (!pr.getName().equalsIgnoreCase(productRequest.name())) {
                var product = productMapper.requestToDomain(productRequest);
                var updateProduct = productGateway.updateProduct(productId, product);
                return productMapper.domainToResponse(updateProduct);
            } else {
                throw new ProductException("Product already exists with the name provided");
            }
        }).orElseThrow(() -> new ProductException("Product not found for update"));

    }
}
