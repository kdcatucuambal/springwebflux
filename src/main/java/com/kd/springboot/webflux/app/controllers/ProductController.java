package com.kd.springboot.webflux.app.controllers;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import com.kd.springboot.webflux.app.models.dao.ProductDao;
import com.kd.springboot.webflux.models.app.documents.Product;

import reactor.core.publisher.Flux;


@Controller
public class ProductController {
	
	@Autowired
	private ProductDao productDao;
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping({"/list", "/"})
	public String list(Model model) {
		Flux<Product> products = this.productDao
				.findAll().map(product->{
					 product.setName(product.getName().toUpperCase());
					 return product;
				});
		products.subscribe(product->{
			logger.info(product.getName());
		});
		model.addAttribute("products", products);
		model.addAttribute("title", "Products List");
		return "list";
	}
	
	@RequestMapping({"/list-datadriver"})
	public String listDataDriver(Model model) {
		Flux<Product> products = this.productDao
				.findAll().map(product->{
					 product.setName(product.getName().toUpperCase());
					 return product;
				}).delayElements(Duration.ofSeconds(1));
		products.subscribe(product->{
			logger.info(product.getName());
		});
		model.addAttribute("products", new ReactiveDataDriverContextVariable(products, 2));
		model.addAttribute("title", "Products List");
		return "list";
	}

}
