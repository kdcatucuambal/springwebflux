package com.kd.springboot.webflux.app.models.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.kd.springboot.webflux.models.app.documents.Product;

public interface ProductDao extends ReactiveMongoRepository<Product, String> {

}
