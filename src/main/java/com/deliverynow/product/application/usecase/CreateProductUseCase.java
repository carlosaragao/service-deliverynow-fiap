package com.deliverynow.product.application.usecase;

import com.deliverynow.product.adapters.controller.request.ProductRequest;

public interface CreateProductUseCase {

    void insertProduct(ProductRequest productRequest);
}
