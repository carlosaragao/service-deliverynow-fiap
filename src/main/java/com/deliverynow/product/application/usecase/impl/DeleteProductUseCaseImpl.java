package com.deliverynow.product.application.usecase.impl;

import com.deliverynow.product.application.usecase.DeleteProductUseCase;
import com.deliverynow.product.domain.gateway.ProductGateway;

public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductGateway productGateway;

    public DeleteProductUseCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public void deleteProduct(String productId) {
        productGateway.deleteProduct(productId);
    }
}
