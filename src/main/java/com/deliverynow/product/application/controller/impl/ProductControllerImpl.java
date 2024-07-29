package com.deliverynow.product.application.controller.impl;


import com.deliverynow.product.adapters.controller.request.ProductRequest;
import com.deliverynow.product.adapters.controller.response.ProductResponse;
import com.deliverynow.product.application.controller.ProductController;
import com.deliverynow.product.application.presenter.ProductPresenter;
import com.deliverynow.product.application.usecase.CreateProductUseCase;
import com.deliverynow.product.application.usecase.DeleteProductUseCase;
import com.deliverynow.product.application.usecase.GetProductByCategoryUseCase;
import com.deliverynow.product.application.usecase.UpdateProductUseCase;

import java.util.List;

public class ProductControllerImpl implements ProductController {

    CreateProductUseCase createProductUseCase;
    UpdateProductUseCase updateProductUseCase;
    DeleteProductUseCase deleteProductUseCase;
    GetProductByCategoryUseCase getProductByCategoryUseCase;
    ProductPresenter productPresenter;

    public ProductControllerImpl(CreateProductUseCase createProductUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase, GetProductByCategoryUseCase getProductByCategoryUseCase, ProductPresenter productPresenter) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.getProductByCategoryUseCase = getProductByCategoryUseCase;
        this.productPresenter = productPresenter;
    }

    public void insertProduct(ProductRequest product) {
        createProductUseCase.insertProduct(product);
    }

    public List<ProductResponse> getProductByCategory(String category) {
        var productByCategory = getProductByCategoryUseCase.getProductByCategory(category);
        return productByCategory.stream()
                .map(productPresenter::domainToResponse).toList();
    }

    public ProductResponse updateProduct(String id, ProductRequest product) {
        var productByCategory = updateProductUseCase.updateProduct(id, product);
        return productPresenter.domainToResponse(productByCategory);
    }

    public void removeProduct(String id) {
        deleteProductUseCase.deleteProduct(id);
    }
}
