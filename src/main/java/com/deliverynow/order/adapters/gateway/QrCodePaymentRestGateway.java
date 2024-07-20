package com.deliverynow.order.adapters.gateway;


import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.domain.gateway.QrCodePaymentGateway;
import com.deliverynow.order.infrastructure.rest.QrCodeRest;
import com.deliverynow.order.infrastructure.rest.request.QrCodeItemRequest;
import com.deliverynow.order.infrastructure.rest.request.QrCodeRequest;
import com.deliverynow.order.infrastructure.rest.response.QrCodeResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
public class QrCodePaymentRestGateway implements QrCodePaymentGateway {

    @Inject
    @RestClient
    QrCodeRest paymentRest;

    @Override
    public QrCodeResponse processPayment(Order oder) {

        var paymentResquest = buildPaymentRequest(oder);
//        try {

        return paymentRest.generatedQrCode("Bearer APP_USR-7053979908591335-070813-c58054b9accbf72de9d6762f8aeabb67-1893050778","1893050778", "SUC001POS001", paymentResquest);
//        }catch (ClientWebApplicationException ex){
//            log.error(ex.getMessage());
//            throw new RuntimeException();
//        }
    }

    private QrCodeRequest buildPaymentRequest(Order oder) {

        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(10L);
        ZoneId zoneId = ZoneId.of("America/New_York"); // Exemplo de um fuso horário com -04:00
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String formattedDateTime = zonedDateTime.format(formatter);

        return QrCodeRequest.builder()
                .description("Delivery Now payment")
                .externalReference(oder.getOrderId())
                .expirationDate(formattedDateTime)
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
