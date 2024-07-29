package com.deliverynow.product.infrastructure.config;

import com.deliverynow.product.application.presenter.ProductPresenter;
import com.deliverynow.product.application.usecase.impl.CreateProductUseCaseImpl;
import com.deliverynow.product.application.usecase.impl.DeleteProductUseCaseImpl;
import com.deliverynow.product.application.usecase.impl.GetProductByCategoryUseCaseImpl;
import com.deliverynow.product.application.usecase.impl.UpdateProductUseCaseImpl;
import com.deliverynow.product.domain.gateway.ProductGateway;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
public class ProductConfig {

    @Default
    public CreateProductUseCaseImpl createProductUseCase(ProductGateway productGateway, ProductPresenter productPresenter) {
        return new CreateProductUseCaseImpl(productGateway, productPresenter);
    }

    @Default
    public DeleteProductUseCaseImpl deleteProductUseCase(ProductGateway productGateway) {
        return new DeleteProductUseCaseImpl(productGateway);
    }

    @Default
    public GetProductByCategoryUseCaseImpl getProductByCategoryUseCase(ProductGateway productGateway, ProductPresenter productPresenter) {
        return new GetProductByCategoryUseCaseImpl(productGateway, productPresenter);
    }

    @Default
    public UpdateProductUseCaseImpl updateProductUseCase(ProductGateway productGateway, ProductPresenter productPresenter) {
        return new UpdateProductUseCaseImpl(productGateway, productPresenter);
    }

}
