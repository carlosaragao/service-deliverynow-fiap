package com.deliverynow.product.domain.entity;

import com.deliverynow.product.application.exception.ProductException;

public enum CategoryEnum {

    LANCHE("1", "Lanche"),
    ACOMPANHAMENTO("2", "Acompanhamento"),
    SOBREMESA("3", "Sobremesa"),
    BEBIDA("4", "Bebida");

    private String code;
    private String description;

    CategoryEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static boolean validateCategory(String description) {
        for (CategoryEnum category : CategoryEnum.values()) {
            if (category.getDescription().equalsIgnoreCase(description)) {
                return true;
            }
        }
        throw new ProductException("Category does not exist, inform the 'LANCHE', 'ACOMPANHAMENTO', 'SOBREMESA' and 'BEBIDA' type categories");
    }
}
