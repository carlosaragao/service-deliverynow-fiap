package com.deliverynow.order.infrastructure.rest;

import com.deliverynow.order.infrastructure.rest.request.QrCodeRequest;
import com.deliverynow.order.infrastructure.rest.response.QrCodeResponse;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/instore/orders/qr/seller/collectors")
@RegisterRestClient(baseUri = "https://api.mercadopago.com")
public interface QrCodeRest {

    @POST
    @Path("/{user_id}/pos/{external_pos_id}/qrs")
    QrCodeResponse generatedQrCode(@HeaderParam("Authorization") String authorization,
                                   @PathParam("user_id") String userId,
                                   @PathParam("external_pos_id") String externalPos_id,
                                   QrCodeRequest paymentRequest);
}
