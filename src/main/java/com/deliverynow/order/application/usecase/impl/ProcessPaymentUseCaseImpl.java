package com.deliverynow.order.application.usecase.impl;

import com.deliverynow.order.adapters.controller.response.WebHookPayload;
import com.deliverynow.order.application.usecase.ProcessPaymentUseCase;
import com.deliverynow.order.domain.gateway.PaymentGateway;
import com.deliverynow.order.infrastructure.rest.response.PaymentResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;

public class ProcessPaymentUseCaseImpl implements ProcessPaymentUseCase {

    PaymentGateway paymentGateway;

    public ProcessPaymentUseCaseImpl(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public void execute(String payload) {
        var objectMapper = new ObjectMapper();

        try {
            var webhookPayload = objectMapper.readValue(payload, WebHookPayload.class);

            if (webhookPayload.getResource() != null && webhookPayload.getResource().startsWith("https://api.mercadolibre.com/merchant_orders/")
                    && webhookPayload.getTopic() != null
                    && webhookPayload.getTopic().equalsIgnoreCase("merchant_order")) {


                var orderId = webhookPayload.getResource().substring(webhookPayload.getResource().lastIndexOf('/') + 1);

                Instant startTime = Instant.now();

                PaymentResponse paymentResponse;

                while (true) {
                    try {
                        paymentResponse = paymentGateway.processPayment(orderId);
                        System.out.println("Status do pedido: " + paymentResponse.getStatus());
                        if ("closed".equals(paymentResponse.getStatus())) {
                            //Todo - Inserir pagamento no banco
                            // Todo - Atualizar pedido
                            break;
                        }

                        Thread.sleep(5000);

                        if (Duration.between(startTime, Instant.now()).toMinutes() >= 5) {
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;

                    }
                }
            } else {
                System.out.println("Aguardando payload de pagamento: " + webhookPayload);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
