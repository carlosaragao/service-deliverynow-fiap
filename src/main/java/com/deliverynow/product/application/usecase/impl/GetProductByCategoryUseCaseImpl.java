package com.deliverynow.product.application.usecase.impl;

import com.deliverynow.product.application.presenter.ProductPresenter;
import com.deliverynow.product.application.usecase.GetProductByCategoryUseCase;
import com.deliverynow.product.domain.entity.CategoryEnum;
import com.deliverynow.product.domain.entity.Product;
import com.deliverynow.product.domain.gateway.ProductGateway;

import java.util.List;

public class GetProductByCategoryUseCaseImpl implements GetProductByCategoryUseCase {

    private final ProductGateway productGateway;
    private final ProductPresenter productPresenter;

    public GetProductByCategoryUseCaseImpl(ProductGateway productGateway, ProductPresenter productPresenter) {
        this.productGateway = productGateway;
        this.productPresenter = productPresenter;
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        CategoryEnum.validateCategory(category.toUpperCase());
        return productGateway.getProductByCategory(category.toUpperCase());
    }
}
