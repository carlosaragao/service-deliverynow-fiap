package com.deliverynow.order.infrastructure.rest;

import com.deliverynow.order.infrastructure.rest.response.PaymentResponse;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/merchant_orders")
@RegisterRestClient(baseUri = "https://api.mercadolibre.com")
public interface PaymentMpRest {

    @GET
    @Path("/{code_payment}")
    PaymentResponse generatedQrCode(@HeaderParam("Authorization") String authorization,
                                    @PathParam("code_payment") String codePayment);

}
