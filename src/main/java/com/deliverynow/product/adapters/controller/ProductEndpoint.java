package com.deliverynow.product.adapters.controller;


import com.deliverynow.product.adapters.controller.request.ProductRequest;
import com.deliverynow.product.adapters.controller.response.BaseResponse;
import com.deliverynow.product.adapters.controller.response.ProductResponse;
import com.deliverynow.product.application.usecase.*;
import com.deliverynow.product.domain.entity.Product;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Product controller", description = "Produto operations")
public class ProductEndpoint {

    CreateProductUseCase createProductUseCase;
    UpdateProductUseCase updateProductUseCase;
    DeleteProductUseCase deleteProductUseCase;
    GetProductByCategoryUseCase getProductByCategoryUseCase;

    public ProductEndpoint(CreateProductUseCase createProductUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase, GetProductByCategoryUseCase getProductByCategoryUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.getProductByCategoryUseCase = getProductByCategoryUseCase;
    }

    @POST
    @Operation(summary = "Criar um novo produto")
    public RestResponse<Void> insertProduct(@Valid ProductRequest product) {
        createProductUseCase.insertProduct(product);
        return RestResponse.ok();
    }

    @GET
    @Operation(summary = "Buscar produto por categoria")
    public RestResponse<BaseResponse<List<ProductResponse>>> getClient(@QueryParam("category") String category) {

        var productByCategory = getProductByCategoryUseCase.getProductByCategory(category);
        return RestResponse.ok(new BaseResponse<>(productByCategory));
    }

    @PUT
    @Path("{productId}")
    @Operation(summary = "Editar produto por id")
    public RestResponse<BaseResponse<ProductResponse>> updateProduto(@PathParam("productId") String id, ProductRequest product) {

        var productByCategory = updateProductUseCase.updateProduct(id, product);
        return RestResponse.ok(new BaseResponse<>(productByCategory));
    }

    @DELETE
    @Path("{productId}")
    @Operation(summary = "Deletar produto por id")
    public RestResponse<Void> removeProduto(@PathParam("productId") String id) {
        deleteProductUseCase.deleteProduct(id);
        return RestResponse.accepted();
    }
}
