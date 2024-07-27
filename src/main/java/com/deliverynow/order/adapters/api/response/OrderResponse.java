package com.deliverynow.order.adapters.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String orderId;
    private String createDate;
    private String statusOrder;
    private CustomerResponse customer;
    private List<ItemResponse> items;
    private TotalResponse total;
}
