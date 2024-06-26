package com.deliverynow.order.domain.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Schema(description = "ID do produto", example = "001")
    @NotBlank(message = "O ID do produto não pode estar em branco")
    private String productId;

    @Schema(description = "Nome do produto", example = "Hambúrguer")
    @NotBlank(message = "O nome do produto não pode estar em branco")
    private String name;

    @Schema(description = "Descrição do produto", example = "Delicioso hambúrguer com queijo, alface, tomate e molho especial.")
    private String description;

    @Schema(description = "Quantidade do produto disponível", example = "10")
    @NotNull(message = "A quantidade não pode ser nula")
    private Integer quantity;

    @Schema(description = "Preço unitário do produto", example = "12.50")
    @NotNull(message = "O preço unitário não pode ser nulo")
    private Double unitPrice;

    @Schema(description = "Preço total dos itens", example = "125.00")
    private Double totalItem;
}
