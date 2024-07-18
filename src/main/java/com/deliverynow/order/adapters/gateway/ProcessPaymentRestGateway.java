package com.deliverynow.order.adapters.gateway;


import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.domain.gateway.ProcessPaymentGateway;
import com.deliverynow.order.infrastructure.rest.QrCodeRest;
import com.deliverynow.order.infrastructure.rest.request.QrCodeItemRequest;
import com.deliverynow.order.infrastructure.rest.request.QrCodeRequest;
import com.deliverynow.order.infrastructure.rest.response.QrCodeResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProcessPaymentRestGateway implements ProcessPaymentGateway {

    @Inject
    @RestClient
    QrCodeRest paymentRest;

    @Override
    public QrCodeResponse processPayment(Order oder) {

        var paymentResquest = buildPaymentRequest(oder);
        //To-do adicionar o token em uma secrets
        return paymentRest.generatedQrCode("Bearer APP_USR-7053979908591335-070813-c58054b9accbf72de9d6762f8aeabb67-1893050778","1893050778", "SUC001POS001", paymentResquest);
    }

    private QrCodeRequest buildPaymentRequest(Order oder) {
        return QrCodeRequest.builder()
                .description("Delivery Now payment")
                .externalReference(oder.getOrderId())
                .expirationDate(LocalDateTime.now().plusMinutes(10L).toString())
                .items(buildItemsPayment(oder))
                .notificationUrl("https://webhook-test.com/11c6fe21038e5a032647badd306cf0e9")
                .title("Product order")
                .totalAmount(oder.getTotal().getFinalTotal())
                .build();
    }

    private List<QrCodeItemRequest> buildItemsPayment(Order oder) {
        return oder.getItems().stream()
                .map(item -> QrCodeItemRequest.builder()
                        .skuNumber(item.getItemId())
                        .category(item.getCategory())
                        .title(item.getName())
                        .description(item.getDescription())
                        .unitPrice(item.getUnitPrice())
                        .quantity(item.getQuantity())
                        .unitMeasure("unit")
                        .totalAmount(item.getTotalPrice())
                        .build()).collect(Collectors.toList());
    }
}
