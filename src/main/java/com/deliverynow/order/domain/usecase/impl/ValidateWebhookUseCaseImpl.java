package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.adapters.api.response.WebHookPayload;
import com.deliverynow.order.domain.usecase.ValidateWebhookUseCase;
import com.deliverynow.order.infrastructure.config.OrderProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class ValidateWebhookUseCaseImpl implements ValidateWebhookUseCase {

    OrderProperties orderProperties;

    public ValidateWebhookUseCaseImpl(OrderProperties orderProperties) {
        this.orderProperties = orderProperties;
    }

    @Override
    public Optional<String> getPaymentIdOrder(String payload) {

        var webhookPayload = getWebHookPayload(payload);
        if (webhookPayload != null && isMerchantOrder(webhookPayload)) {
            var orderId = webhookPayload.getResource().substring(webhookPayload.getResource().lastIndexOf('/') + 1);
            return Optional.of(orderId);
        } else {
            return Optional.empty();
        }
    }

    private boolean isMerchantOrder(WebHookPayload webhookPayload) {
        return webhookPayload.getResource() != null && webhookPayload.getResource()
                .startsWith(orderProperties.url())
                && webhookPayload.getTopic() != null
                && webhookPayload.getTopic().equalsIgnoreCase("merchant_order");
    }

    private WebHookPayload getWebHookPayload(String payload) {
        var objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(payload, WebHookPayload.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
