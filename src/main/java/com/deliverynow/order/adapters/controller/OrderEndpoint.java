package com.deliverynow.order.adapters.controller;


import com.deliverynow.order.adapters.controller.request.OrderRequest;
import com.deliverynow.order.adapters.controller.request.OrderUpdateStatusRequest;
import com.deliverynow.order.adapters.controller.response.BaseResponse;
import com.deliverynow.order.adapters.controller.response.OrderResponse;
import com.deliverynow.order.application.usecase.CreateOrderUseCase;
import com.deliverynow.order.application.usecase.GetOrderByStatusUseCase;
import com.deliverynow.order.application.usecase.ResumeOrderUseCase;
import com.deliverynow.order.application.usecase.UpdateStatusOrderUseCase;
import com.deliverynow.order.domain.entity.Order;
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

    CreateOrderUseCase createOrderUseCase;
    ResumeOrderUseCase resumeOrderUseCase;
    GetOrderByStatusUseCase getOrderByStatusUseCase;
    UpdateStatusOrderUseCase updateStatusOrderUseCase;

    public OrderEndpoint(CreateOrderUseCase createOrderUseCase, ResumeOrderUseCase resumeOrderUseCase, GetOrderByStatusUseCase getOrderByStatusUseCase, UpdateStatusOrderUseCase updateStatusOrderUseCase) {

        this.createOrderUseCase = createOrderUseCase;
        this.resumeOrderUseCase = resumeOrderUseCase;
        this.getOrderByStatusUseCase = getOrderByStatusUseCase;
        this.updateStatusOrderUseCase = updateStatusOrderUseCase;
    }

    @POST
    @Path("/checkout")
    @Operation(summary = "Fazer checkout do pedido")
    public RestResponse<BaseResponse<String>> orderCheckout(@Valid OrderRequest orderRequest) {
        String checkoutOrder = createOrderUseCase.createdOrder(orderRequest);
        return RestResponse.ok(new BaseResponse<>(checkoutOrder));
    }

    @GET
    @Operation(summary = "Lista todos os pedidos")
    public RestResponse<BaseResponse<List<OrderResponse>>> getAllOrders() {
        var orders = getOrderByStatusUseCase.getOrderByStatus();
        return RestResponse.ok(new BaseResponse<>(orders));
    }

    @GET
    @Path("resume/{customerId}")
    @Operation(summary = "Resumir pedido")
    public RestResponse<BaseResponse<OrderResponse>> getResumeOrder(@PathParam("customerId") String id) {
        var resumeOrder = resumeOrderUseCase.getResumeOrder(id);
        return RestResponse.ok(new BaseResponse<>(resumeOrder));
    }

    @PUT
    @Path("/update/status")
    @Operation(summary = "Atualizar status")
    public RestResponse<Void> updateOrder(@Valid OrderUpdateStatusRequest orderRequest) {
        updateStatusOrderUseCase.updateStatus(orderRequest);
        return RestResponse.ok();
    }
}

