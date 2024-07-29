package com.deliverynow.product.application.usecase.impl;

import com.deliverynow.product.adapters.controller.request.ProductRequest;
import com.deliverynow.product.application.exception.ProductException;
import com.deliverynow.product.application.presenter.ProductPresenter;
import com.deliverynow.product.application.usecase.UpdateProductUseCase;
import com.deliverynow.product.domain.entity.CategoryEnum;
import com.deliverynow.product.domain.entity.Product;
import com.deliverynow.product.domain.gateway.ProductGateway;

public class UpdateProductUseCaseImpl implements UpdateProductUseCase {


    private final ProductGateway productGateway;
    private final ProductPresenter productPresenter;

    public UpdateProductUseCaseImpl(ProductGateway productGateway, ProductPresenter productPresenter) {
        this.productGateway = productGateway;
        this.productPresenter = productPresenter;
    }

    @Override
    public Product updateProduct(String productId, ProductRequest productRequest) {
        CategoryEnum.validateCategory(productRequest.category());
        var productById = productGateway.getProductById(productId);
        return productById.map(pr -> {
            var product = productPresenter.requestToDomain(productRequest);
            return productGateway.updateProduct(productId, product);
        }).orElseThrow(() -> new ProductException("Product not found for update"));
    }
}
