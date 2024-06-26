package com.deliverynow.order.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

    private String produtoId;
    private String nome;
    private String descricao;
    private Integer quantidade;
    private Double precoUnitario;
    private Double totalItem;
}
