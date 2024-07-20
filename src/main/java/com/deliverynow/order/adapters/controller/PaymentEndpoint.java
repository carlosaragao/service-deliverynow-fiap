package com.deliverynow.order.adapters.controller;

import com.deliverynow.order.adapters.controller.response.BaseResponse;
import com.deliverynow.order.adapters.controller.response.QrCodePaymentResponse;
import com.deliverynow.order.application.usecase.GenerateQrCodeUseCase;
import com.deliverynow.order.application.usecase.ProcessPaymentUseCase;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/payment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Payment controller", description = "Payment operations")
public class PaymentEndpoint {

    GenerateQrCodeUseCase qrCodeUseCase;
    ProcessPaymentUseCase processPaymentUseCase;

    public PaymentEndpoint(GenerateQrCodeUseCase qrCodeUseCase, ProcessPaymentUseCase processPaymentUseCase) {
        this.qrCodeUseCase = qrCodeUseCase;
        this.processPaymentUseCase = processPaymentUseCase;
    }

    @GET
    @Path("qrcode/{orderId}")
    @Operation(summary = "Gerar codigo QrCode")
    public RestResponse<BaseResponse<QrCodePaymentResponse>> getResumeOrder(@PathParam("orderId") String id) {
        var qrCode = qrCodeUseCase.getQrCode(id);
        return RestResponse.ok(new BaseResponse<>(qrCode));
    }

    @POST
    @Path("webhook")
    @Operation(summary = "Gerar codigo QrCode")
    public RestResponse<?> paymentWebhook(String payload) {
        processPaymentUseCase.execute(payload);
        return RestResponse.ok();
    }
}
