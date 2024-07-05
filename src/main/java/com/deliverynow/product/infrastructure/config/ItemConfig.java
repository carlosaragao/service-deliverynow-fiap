package com.deliverynow.product.infrastructure.config;

import com.deliverynow.product.application.usecase.impl.RemoveItemUseCaseImpl;
import com.deliverynow.product.application.usecase.impl.SelectItemUseCaseImpl;
import com.deliverynow.product.domain.gateway.ItemGateway;
import com.deliverynow.product.domain.gateway.ProductGateway;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
public class ItemConfig {

    @Default
    public SelectItemUseCaseImpl selectItemUseCase(ItemGateway itemGateway, ProductGateway productGateway) {
        return new SelectItemUseCaseImpl(itemGateway, productGateway);
    }

    @Default
    public RemoveItemUseCaseImpl removeItemUseCase(ItemGateway itemGateway) {
        return new RemoveItemUseCaseImpl(itemGateway);
    }
}
