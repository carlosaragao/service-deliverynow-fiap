package com.deliverynow.product.application.usecase;

import com.deliverynow.product.adapters.controller.response.ProductResponse;
import com.deliverynow.product.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface GetProductByCategoryUseCase {

    List<ProductResponse> getProductByCategory(String category);
}
