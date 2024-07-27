package com.deliverynow.product.adapters.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {

    private String sessionId;
    private List<ItemProductRequest> products;
}
