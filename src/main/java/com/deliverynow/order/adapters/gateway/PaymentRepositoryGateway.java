package com.deliverynow.order.adapters.gateway;

import com.deliverynow.order.application.mapper.PaymentMapper;
import com.deliverynow.order.domain.entity.Payment;
import com.deliverynow.order.domain.gateway.PaymentGateway;
import com.deliverynow.order.infrastructure.repository.PaymentRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PaymentRepositoryGateway implements PaymentGateway {

    PaymentRepository paymentRepository;
    PaymentMapper paymentMapper;

    public PaymentRepositoryGateway(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public void insertPayment(Payment payment) {
        paymentRepository.persist(paymentMapper.domainToEntity(payment));
    }

    @Override
    public Optional<Payment> getByOrderId(String orderId) {

        return paymentRepository.findByOrderId(orderId)
                .map(paymentEntity -> paymentMapper.entityToDomain(paymentEntity));
    }
}
