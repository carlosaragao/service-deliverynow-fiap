package com.deliverynow.product.infrastructure.config;

import com.deliverynow.product.application.mapper.ProductMapper;
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
    public CreateProductUseCaseImpl createProductUseCase(ProductGateway productGateway, ProductMapper productMapper) {
        return new CreateProductUseCaseImpl(productGateway, productMapper);
    }

    @Default
    public DeleteProductUseCaseImpl deleteProductUseCase(ProductGateway productGateway) {
        return new DeleteProductUseCaseImpl(productGateway);
    }

    @Default
    public GetProductByCategoryUseCaseImpl getProductByCategoryUseCase(ProductGateway productGateway, ProductMapper productMapper) {
        return new GetProductByCategoryUseCaseImpl(productGateway, productMapper);
    }

    @Default
    public UpdateProductUseCaseImpl updateProductUseCase(ProductGateway productGateway, ProductMapper productMapper) {
        return new UpdateProductUseCaseImpl(productGateway, productMapper);
    }

}
