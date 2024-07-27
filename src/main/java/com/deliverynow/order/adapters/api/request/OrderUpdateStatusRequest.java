package com.deliverynow.order.adapters.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateStatusRequest {

    private String orderId;
    private String status;
}
