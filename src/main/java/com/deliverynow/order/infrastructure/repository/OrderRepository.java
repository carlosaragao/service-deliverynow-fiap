package com.deliverynow.order.infrastructure.repository;


import com.deliverynow.order.infrastructure.repository.entity.OrderEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<OrderEntity> {

//    public List<OrderEntity> findAllSorted() {
//        return find(
//                "ORDER BY FIELD(statusOrder, 'PRONTO', 'EM_PREPARACAO', 'RECEBIDO'), createDate ASC"
//        )..list();
//    }
}
