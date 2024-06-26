package com.deliverynow.order.application.mapper;


import com.deliverynow.order.domain.entity.*;
import com.deliverynow.order.infrastructure.repository.entity.*;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;

@ApplicationScoped
public class OrderMapper {

    public OrderEntity orderToOrderEntity(Order order) {

        var listItems = new ArrayList<ItemEntity>();
        order.getItems().forEach(product -> {
            var items = ItemEntity.builder()
                    .produtoId(product.getProductId())
                    .nome(product.getName())
                    .descricao(product.getDescription())
                    .quantidade(product.getQuantity())
                    .precoUnitario(product.getUnitPrice())
                    .totalItem(product.getTotalItem())
                    .build();
            listItems.add(items);
        });

        return OrderEntity.builder()
                .client(ClientEntity.builder()
                        .name(order.getClient().getName())
                        .phone(order.getClient().getPhone())
                        .email(order.getClient().getEmail())
                        .document(order.getClient().getDocument())
                        .address(AddressEntity.builder()
                                .postalCode(order.getClient().getAddress().getPostalCode())
                                .street(order.getClient().getAddress().getStreet())
                                .state(order.getClient().getAddress().getState())
                                .city(order.getClient().getAddress().getCity())
                                .number(order.getClient().getAddress().getNumber())
                                .build())
                        .build())
                .items(listItems)
                .orderDetails(OrderDetailEntity.builder()
                        .notes(order.getOrderDetail().getNotes())
                        .dateTime(order.getOrderDetail().getDateTime())
                        .deliveryMethod(order.getOrderDetail().getDeliveryMethod())
                        .build())
                .total(TotalEntity.builder()
                        .subtotal(order.getTotal().getSubtotal())
                        .taxes(order.getTotal().getTaxes())
                        .discount(order.getTotal().getDiscounts())
                        .finalTotal(order.getTotal().getFinalTotal())
                        .build())
                .payment(PaymentEntity.builder()
                        .method(order.getPayment().getMethod())
                        .status(order.getPayment().getStatus().name())
                        .details(order.getPayment().getDetails())
                        .build())
                .build();
    }

    public Order orderEntityToOrder(OrderEntity order) {

        var listItems = new ArrayList<Item>();
        order.getItems().forEach(product -> {
            var items = new Item(product.getProdutoId(), product.getNome(), product.getDescricao(),
                    product.getQuantidade(), product.getPrecoUnitario(), product.getTotalItem());
            listItems.add(items);
        });
        var client = new Client(order.getClient().getName(), order.getClient().getPhone(), order.getClient().getEmail(), order.getClient().getDocument(),
                new Address(order.getClient().getAddress().getPostalCode(), order.getClient().getAddress().getStreet(), order.getClient().getAddress().getState(),
                        order.getClient().getAddress().getCity(), order.getClient().getAddress().getNumber()));
        var orderDetail = new OrderDetail(order.getOrderDetails().getDateTime(), order.getOrderDetails().getNotes(), order.getOrderDetails().getDeliveryMethod());
        var total = new Total(order.getTotal().getSubtotal(), order.getTotal().getTaxes(), order.getTotal().getDiscount(), order.getTotal().getFinalTotal());
        var payment = new Payment(order.getPayment().getMethod(), PaymentEnum.getValue(order.getPayment().getStatus()), order.getPayment().getDetails());

        return new Order(client, listItems, orderDetail, total, payment);
    }
}
