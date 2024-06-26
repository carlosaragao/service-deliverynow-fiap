package com.deliverynow.product.adapters.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(

        @Schema(description = "Nome do produto", example = "Cheeseburger")
        @NotBlank(message = "O nome do produto não pode estar em branco")
        String name,

        @Schema(description = "Descrição do produto", example = "Um delicioso cheeseburger com todos os acompanhamentos.")
        @NotBlank(message = "A descrição do produto não pode estar em branco")
        String description,

        @Schema(description = "Categoria do produto", example = "LANCHE")
        @NotBlank(message = "A categoria do produto não pode estar em branco")
        String category,

        @Schema(description = "Preço do produto", example = "9.99")
        @NotNull(message = "O preço do produto não pode ser nulo")
        Double price,

        @Schema(description = "URL da imagem do produto", example = "http://example.com/cheeseburger.jpg")
        @NotBlank(message = "A URL da imagem do produto não pode estar em branco")
        String image,

        @Schema(description = "Disponibilidade do produto", example = "true")
        @NotNull(message = "A disponibilidade do produto não pode ser nula")
        Boolean availability,

        @Schema(description = "Lista de ingredientes do produto", example = "[\"Hambúrguer\", \"Queijo\", \"Alface\"]")
        @NotEmpty(message = "A lista de ingredientes do produto não pode estar vazia")
        List<String> ingredients,

        @Schema(description = "Tipo de pão usado para o produto", example = "Pão de hambúrguer")
        String breadType,

        @Schema(description = "Tipo de sobremesa, se aplicável", example = "Sorvete de Chocolate")
        String dessertType,

        @Schema(description = "Tipo de bebida, se aplicável", example = "Coca Cola")
        String beverageType,

        @Schema(description = "Tamanho da porção do produto", example = "Média")
        String portionSize,

        @Schema(description = "Peso do produto em gramas", example = "250.0")
        Double weight,

        @Schema(description = "Número de calorias no produto", example = "700")
        Integer calories,

        @Schema(description = "Volume do produto em mililitros", example = "500")
        Integer volume
) { }
