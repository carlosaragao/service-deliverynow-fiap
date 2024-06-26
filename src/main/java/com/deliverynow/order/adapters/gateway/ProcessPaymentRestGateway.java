package com.deliverynow.order.adapters.gateway;


import com.deliverynow.order.infrastructure.rest.PaymentRest;
import com.deliverynow.order.domain.entity.Payment;
import com.deliverynow.order.domain.entity.PaymentEnum;
import com.deliverynow.order.domain.gateway.ProcessPaymentGateway;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessPaymentRestGateway implements ProcessPaymentGateway {

    PaymentRest paymentRest;

    public ProcessPaymentRestGateway(PaymentRest paymentRest) {
        this.paymentRest = paymentRest;
    }

    @Override
    public Payment processPayment(Payment payment) {

        if (paymentRest.paymentStratus()) {
            return new Payment(payment.getMethod(), PaymentEnum.APROVADO, payment.getDetails());
        } else {
            return new Payment(payment.getMethod(), PaymentEnum.REPROVADO, payment.getDetails());
        }
    }
}
