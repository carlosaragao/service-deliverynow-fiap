package com.deliverynow.order.adapters.gateway;

import com.deliverynow.order.domain.gateway.ProcessPaymentGateway;
import com.deliverynow.order.infrastructure.rest.MerchantOrderRest;
import com.deliverynow.order.infrastructure.rest.response.MerchantOrderResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ProcessPaymentMpGateway implements ProcessPaymentGateway {

    @Inject
    @RestClient
    MerchantOrderRest merchantOrderRest;

    @Override
    public MerchantOrderResponse processPayment(String orderId) {

        return merchantOrderRest.generatedQrCode("Bearer APP_USR-7053979908591335-070813-c58054b9accbf72de9d6762f8aeabb67-1893050778", orderId);
    }
}
