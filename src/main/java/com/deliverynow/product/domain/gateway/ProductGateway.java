package com.deliverynow.product.domain.gateway;


import com.deliverynow.product.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

   void insertProduct(Product product);
   Product updateProduct(String productId, Product product);
   void deleteProduct(String productId);
   List<Product> getProductByCategory(String category);
   Optional<Product> getProductByName(String name);
   Optional<Product> getProductById(String productId);
}
