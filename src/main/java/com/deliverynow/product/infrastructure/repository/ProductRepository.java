package com.deliverynow.product.infrastructure.repository;

import com.deliverynow.product.infrastructure.repository.entity.ProductEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<ProductEntity> {

    public List<ProductEntity> getProductByCategory(String category) {
        return find("category", category).list();
    }

    public Optional<ProductEntity> getProductByName(String name) {
        return find("name", name).firstResultOptional();
    }

    public ProductEntity getProductByProductId(String productId) {
        return find("productId", productId).firstResult();
    }
}
