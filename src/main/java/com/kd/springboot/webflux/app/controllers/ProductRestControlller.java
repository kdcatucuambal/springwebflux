package com.kd.springboot.webflux.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kd.springboot.webflux.app.models.dao.ProductDao;
import com.kd.springboot.webflux.models.app.documents.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductRestControlller {
	@Autowired
	private ProductDao productDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductRestControlller.class);
	
	@RequestMapping
	public Flux<Product> list(){
		Flux<Product> products = this.productDao.findAll();
		return products;
	}
	
	@RequestMapping("/{id}")
	public Mono<Product> getById(@PathVariable String id){
		Mono<Product> product = this.productDao.findById(id);
		return product;
	}
}
