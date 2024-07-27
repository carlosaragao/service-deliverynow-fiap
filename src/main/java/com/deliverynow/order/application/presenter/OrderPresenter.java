package com.deliverynow.order.application.presenter;

import com.deliverynow.order.adapters.api.response.OrderResponse;
import com.deliverynow.order.domain.entity.CustomerOrder;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.infrastructure.repository.entity.OrderEntity;
import com.deliverynow.user.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "jakarta", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderPresenter {

    OrderResponse domainToResponse(Order order);
    CustomerOrder customerToCustomerOrder(Customer customer);
    OrderEntity domainToEntity(Order order);
    Order entityToDomain(OrderEntity order);
}
