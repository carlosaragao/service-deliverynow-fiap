package com.deliverynow.product.domain.gateway;

import java.util.List;

public interface ItemGateway {

    void selectItem(List<String> productIds, String itemId);
}
