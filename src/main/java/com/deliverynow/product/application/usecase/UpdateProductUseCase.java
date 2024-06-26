package com.deliverynow.product.application.usecase;

import com.deliverynow.product.adapters.controller.request.ProductRequest;
import com.deliverynow.product.adapters.controller.response.ProductResponse;

public interface UpdateProductUseCase {

    ProductResponse updateProduct(String productId, ProductRequest productRequest);

}
