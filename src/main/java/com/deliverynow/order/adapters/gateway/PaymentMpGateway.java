package com.deliverynow.order.adapters.gateway;

import com.deliverynow.order.domain.gateway.PaymentGateway;
import com.deliverynow.order.infrastructure.rest.PaymentMpRest;
import com.deliverynow.order.infrastructure.rest.response.PaymentResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class PaymentMpGateway implements PaymentGateway {

    @Inject
    @RestClient
    PaymentMpRest paymentMpRest;

    @Override
    public PaymentResponse processPayment(String orderId) {

        return paymentMpRest.generatedQrCode("Bearer APP_USR-7053979908591335-070813-c58054b9accbf72de9d6762f8aeabb67-1893050778", orderId);
    }
}
