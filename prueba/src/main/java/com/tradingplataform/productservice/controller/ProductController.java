package com.tradingplataform.productservice.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.productservice.model.Product;
import com.tradingplataform.productservice.service.impl.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		List<Product> Products = productService.findAll();
		if (Products.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(Products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") int id) {
		Optional<Product> product = productService.find(id);
		if (product.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(product.get());
	}

	@PostMapping()
	public ResponseEntity<Product> save(@RequestBody Product product, @RequestHeader Map<String, String> header) {
		
		//Obtener el token de la cabecera de la peticion
		String token = header.get("authorization");
		
		// Pasar el token al metodo save para que pueda obtener el id del usuario
		Product productNew = productService.save(product,token);
		return ResponseEntity.ok(productNew);
	}

	@GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Product>> getProducts(@PathVariable("userId") int userId) {
        List<Product> products = productService.findByUserId(userId);

        if(products.isEmpty())
           return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }

}
