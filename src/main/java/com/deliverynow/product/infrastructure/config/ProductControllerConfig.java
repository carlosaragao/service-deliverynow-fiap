package com.deliverynow.product.infrastructure.config;

import com.deliverynow.product.application.controller.impl.ItemControllerImpl;
import com.deliverynow.product.application.controller.impl.ProductControllerImpl;
import com.deliverynow.product.application.presenter.ProductPresenter;
import com.deliverynow.product.application.usecase.*;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
public class ProductControllerConfig {

    @Default
    public ProductControllerImpl productController(CreateProductUseCase createProductUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase, GetProductByCategoryUseCase getProductByCategoryUseCase, ProductPresenter productPresenter) {
        return new ProductControllerImpl(createProductUseCase, updateProductUseCase, deleteProductUseCase, getProductByCategoryUseCase, productPresenter);
    }

    @Default
    public ItemControllerImpl itemController(SelectItemUseCase selectItemUseCase, RemoveItemUseCase removeItemUseCase) {
        return new ItemControllerImpl(selectItemUseCase, removeItemUseCase);
    }
}
