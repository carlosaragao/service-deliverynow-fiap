package com.deliverynow.order.application.usecase.impl;

import com.deliverynow.order.adapters.controller.response.WebHookPayload;
import com.deliverynow.order.adapters.gateway.PaymentRepositoryGateway;
import com.deliverynow.order.application.exception.PaymentException;
import com.deliverynow.order.application.mapper.PaymentMapper;
import com.deliverynow.order.application.usecase.ProcessPaymentUseCase;
import com.deliverynow.order.application.usecase.UpdateStatusOrderUseCase;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.domain.entity.Payment;
import com.deliverynow.order.domain.gateway.ProcessPaymentGateway;
import com.deliverynow.order.infrastructure.rest.response.MerchantOrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProcessPaymentUseCaseImpl implements ProcessPaymentUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ProcessPaymentUseCaseImpl.class);

    PaymentMapper paymentMapper;
    ProcessPaymentGateway processPaymentGateway;
    UpdateStatusOrderUseCase updateStatusOrderUseCase;
    PaymentRepositoryGateway paymentRepositoryGateway;

    public ProcessPaymentUseCaseImpl(PaymentMapper paymentMapper, ProcessPaymentGateway processPaymentGateway, UpdateStatusOrderUseCase updateStatusOrderUseCase, PaymentRepositoryGateway paymentRepositoryGateway) {
        this.paymentMapper = paymentMapper;
        this.processPaymentGateway = processPaymentGateway;
        this.updateStatusOrderUseCase = updateStatusOrderUseCase;
        this.paymentRepositoryGateway = paymentRepositoryGateway;
    }

    @Override
    public void execute(String payload) {

        var webhookPayload = getWebHookPayload(payload);
        if (isMerchantOrder(webhookPayload)) {

            var orderId = webhookPayload.getResource().substring(webhookPayload.getResource().lastIndexOf('/') + 1);
            logger.info("Starting payment process for id {}", orderId);
            var scheduler = Executors.newSingleThreadScheduledExecutor();
            var future = new CompletableFuture<MerchantOrderResponse>();
            Runnable task = getTask(orderId, future);
            try {
                scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
                var paymentMpResponse = future.get();
                var payment = paymentMapper.paymentMerchantToDomain(paymentMpResponse.getMerchantPayments().getFirst());
                payment.setOrderId(paymentMpResponse.getExternalReference());
                paymentRepositoryGateway.insertPayment(payment);
                updateStatusOrderUseCase.updateStatus(paymentMpResponse.getExternalReference(), OrderStatusEnum.RECEBIDO.name());

            } catch (InterruptedException | ExecutionException e) {
                logger.error("Error processing payment", e);
                Thread.currentThread().interrupt();
                throw new PaymentException("Error processing payment");
            } finally {
                logger.info("Payment for id {} completed", orderId);
                scheduler.shutdown();
            }
        }
    }

    private WebHookPayload getWebHookPayload(String payload) {
        var objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(payload, WebHookPayload.class);
        } catch (JsonProcessingException e) {
            throw new PaymentException(e);
        }
    }

    private Runnable getTask(String orderId, CompletableFuture<MerchantOrderResponse> future) {
        return () -> {
            try {
                var status = processPaymentGateway.processPayment(orderId);
                if ("CLOSED".equalsIgnoreCase(status.getStatus())) {
                    logger.info("Payment processed successfully {}", orderId);
                    future.complete(status);
                }
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        };
    }

    private boolean isMerchantOrder(WebHookPayload webhookPayload) {
        return webhookPayload.getResource() != null && webhookPayload.getResource().startsWith("https://api.mercadolibre.com/merchant_orders/")
                && webhookPayload.getTopic() != null
                && webhookPayload.getTopic().equalsIgnoreCase("merchant_order");
    }
}
