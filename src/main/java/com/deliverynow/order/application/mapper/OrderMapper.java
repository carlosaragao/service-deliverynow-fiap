package com.deliverynow.order.application.mapper;


import com.deliverynow.order.domain.entity.*;
import com.deliverynow.order.infrastructure.repository.entity.*;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;

@ApplicationScoped
public class OrderMapper {

    public OrderEntity orderToOrderEntity(Order order) {

        var listItems = new ArrayList<ItemEntity>();
//        order.getItemOrders().forEach(product -> {
//            var items = ItemEntity.builder()
//                    .produtoId(product.getProductId())
//                    .nome(product.getName())
//                    .descricao(product.getDescription())
//                    .quantidade(product.getQuantity())
//                    .precoUnitario(product.getUnitPrice())
//                    .totalItem(product.getTotalItem())
//                    .build();
//            listItems.add(items);
//        });

        return OrderEntity.builder()
                .customer(ClientEntity.builder()
                        .name(order.getCustomer().getName())
                        .phone(order.getCustomer().getPhone())
                        .email(order.getCustomer().getEmail())
                        .document(order.getCustomer().getDocument())
                        .address(AddressEntity.builder()
                                .postalCode(order.getCustomer().getAddress().getPostalCode())
                                .street(order.getCustomer().getAddress().getStreet())
                                .state(order.getCustomer().getAddress().getState())
                                .city(order.getCustomer().getAddress().getCity())
                                .number(order.getCustomer().getAddress().getNumber())
                                .build())
                        .build())
                .items(listItems)
                .orderDetail(OrderDetailEntity.builder()
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

        var listItems = new ArrayList<ItemOrder>();
        order.getItems().forEach(product -> {
//            var items = new ItemOrder(product.getProdutoId(), product.getNome(), product.getDescricao(),
//                    product.getQuantidade(), product.getPrecoUnitario(), product.getTotalItem());
//            listItems.add(items);
        });
        var client = new CustomerOrder(order.getCustomer().getName(), order.getCustomer().getPhone(), order.getCustomer().getEmail(), order.getCustomer().getDocument(),
                new Address(order.getCustomer().getAddress().getPostalCode(), order.getCustomer().getAddress().getStreet(), order.getCustomer().getAddress().getState(),
                        order.getCustomer().getAddress().getCity(), order.getCustomer().getAddress().getNumber()));
        var orderDetail = new OrderDetail(order.getOrderDetail().getDateTime(), order.getOrderDetail().getNotes(), order.getOrderDetail().getDeliveryMethod());
        var total = new Total(order.getTotal().getSubtotal(), order.getTotal().getTaxes(), order.getTotal().getDiscount(), order.getTotal().getFinalTotal());
        var payment = new Payment(order.getPayment().getMethod(), PaymentEnum.getValue(order.getPayment().getStatus()), order.getPayment().getDetails());

        return null;
    }
}
