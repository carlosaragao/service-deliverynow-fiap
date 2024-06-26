package com.deliverynow.order.adapters.controller;


import com.deliverynow.order.adapters.controller.request.OrderRequest;
import com.deliverynow.order.application.usecase.OrderUseCase;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.user.adapters.controller.response.BaseResponse;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Order controller", description = "Order operations")
public class OrderEndpoint {

    OrderUseCase orderUseCase;

    public OrderEndpoint(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @POST
    @Path("/checkout")
    @Operation(summary = "Fazer checkout do pedido")
    public RestResponse<BaseResponse<String>> orderCheckout(@Valid OrderRequest orderRequest) {
        String checkoutOrder = orderUseCase.checkoutOrder(orderRequest);
        return RestResponse.ok(new BaseResponse<>(checkoutOrder));
    }

    @GET
    @Operation(summary = "Lista todos os pedidos")
    public RestResponse<List<Order>> getAllOrders() {
        var orders = orderUseCase.listOrder();
        return RestResponse.ok(orders);
    }
}

