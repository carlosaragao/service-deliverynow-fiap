package com.deliverynow.product.application.usecase;

import com.deliverynow.product.adapters.controller.request.ProductRequest;
import com.deliverynow.product.adapters.controller.response.ProductResponse;
import com.deliverynow.product.domain.entity.Product;

public interface UpdateProductUseCase {

    Product updateProduct(String productId, ProductRequest productRequest);

}
