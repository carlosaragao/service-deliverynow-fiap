package com.deliverynow.order.adapters.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private CustomerRequest customer;
    @Valid
    @Size(min = 1,message = "Informe algum item")
    private List<ItemRequest> items;
    private OrderDetailRequest orderDetail;
    @Valid
    private TotalRequest total;
    @Valid
    private PaymentRequest payment;
}
