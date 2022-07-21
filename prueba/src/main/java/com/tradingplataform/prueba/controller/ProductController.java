package com.tradingplataform.prueba.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.prueba.model.Product;
import com.tradingplataform.prueba.service.impl.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> Products = productService.findAll();
        if(Products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(Products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id) {
        Optional<Product> product = productService.find(id);
        if(product.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product.get());
    }

    @PostMapping()
    public ResponseEntity<Product> save(@RequestBody Product product) {
        Product productNew = productService.save(product);
        return ResponseEntity.ok(productNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Product>> getByUserId(@PathVariable("userId") int userId) {
        List<Product> products = productService.findByUserId(userId);
        if(products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }
	
	

}
