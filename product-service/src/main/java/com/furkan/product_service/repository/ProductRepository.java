package com.furkan.product_service.repository;

import com.furkan.product_service.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
