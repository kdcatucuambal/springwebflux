package com.kd.springboot.webflux.app;

import java.math.BigDecimal;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.kd.springboot.webflux.app.models.dao.ProductDao;
import com.kd.springboot.webflux.models.app.documents.Product;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringbootwebfluxApplication implements CommandLineRunner {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	private static final Logger log = LoggerFactory.getLogger(SpringbootwebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootwebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("products").subscribe();
		Flux.just(new Product("TV Panasonic LCD", new BigDecimal("435.22")),
				new Product("Apple Ipod", new BigDecimal("989.99")),
				new Product("Nokia 4 Phone", new BigDecimal("122.45")),
				new Product("Lenovo Laptop 400X", new BigDecimal("600.15")),
				new Product("Bazuka Headphone", new BigDecimal("89.50")),
				new Product("Keyboard TKA3", new BigDecimal("45.00")),
				new Product("Touch Rix 1.9", new BigDecimal("11.10"))).flatMap(product -> productDao.save(product))
				.subscribe(product -> log.info(product.toString()));

	}

}
