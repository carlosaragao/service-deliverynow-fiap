package com.deliverynow.product.domain.entity;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String productId;
    private String customerId;
    private String name;
    private String description;
    private String category;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;

    public void setTotalPrice() {
        this.totalPrice = this.quantity * this.unitPrice;
    }
}
