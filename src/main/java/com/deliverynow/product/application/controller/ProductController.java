package com.deliverynow.product.application.controller;

import com.deliverynow.product.adapters.controller.request.ProductRequest;
import com.deliverynow.product.adapters.controller.response.ProductResponse;

import java.util.List;

public interface ProductController {

    void insertProduct(ProductRequest product);

    List<ProductResponse> getProductByCategory(String category);

    ProductResponse updateProduct(String id, ProductRequest product);

    void removeProduct(String id);
}
