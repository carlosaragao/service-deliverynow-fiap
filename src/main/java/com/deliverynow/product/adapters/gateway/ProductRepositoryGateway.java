package com.deliverynow.product.adapters.gateway;


import com.deliverynow.product.application.exception.ProductException;
import com.deliverynow.product.application.mapper.ProductMapper;
import com.deliverynow.product.domain.entity.Product;
import com.deliverynow.product.domain.gateway.ProductGateway;
import com.deliverynow.product.infrastructure.repository.ProductRepository;
import com.deliverynow.product.infrastructure.repository.entity.ProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductRepositoryGateway implements ProductGateway {


    ProductRepository productRepository;
    ProductMapper productMapper;

    public ProductRepositoryGateway(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void insertProduct(Product product) {

        var productByNome = productRepository.getProductByName(product.getName());
        if (productByNome.isEmpty()) {
            ProductEntity entity = productMapper.productToProductEntity(product);
            productRepository.persist(entity);
        } else {
            throw new ProductException("Product already exists with the name provided");
        }
    }

    @Override
    public Product updateProduct(String productId, Product product) {

        var newProduct = productMapper.productToProductEntity(product);
        newProduct.setId(new ObjectId(productId));
        productRepository.persistOrUpdate(newProduct);
        return productMapper.entityToDomain(newProduct);
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(new ObjectId(productId));
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        var productByCategory = productRepository.getProductByCategory(category);
        return productByCategory.stream()
                .map(product -> productMapper.entityToDomain(product)).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        var productEntity = productRepository.getProductByName(name);
        return productEntity.map(product -> productMapper.entityToDomain(product));
    }

    @Override
    public Optional<Product> getProductById(String productId) {
        var productEntity = productRepository.findByIdOptional(new ObjectId(productId));
        return productEntity.map(product -> productMapper.entityToDomain(product));
    }
}
