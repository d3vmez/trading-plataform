package com.tradingplataform.clientservice.controller;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tradingplataform.clientservice.feignproduct.Product;
import com.tradingplataform.clientservice.feignproduct.ProductFeign;

import feign.FeignException;

@Controller
public class ProductsController {

	private Logger log = Logger.getLogger(ProductsController.class);
	@Autowired
	private ProductFeign productFeign;

	@GetMapping("/products")
	public String showProducts(@RequestHeader Map<String, String> header, Model model) {

		try {

			String[] splits = header.get("cookie").split("=");
			String token = "Bearer " + splits[1];
			
			// Obtener productos del product-service
			List<Product> products = productFeign.getAll(token);
			model.addAttribute("products", products);
			
			// Enviarlos a la vista
			
		} catch (NullPointerException e) {

			log.error("Not found token in /products: " + e.getMessage());
			return "/auth/login";

		} catch (FeignException e) {

			log.error("Invalid token in /products: " + e.getMessage());
			return "/auth/login";

		}

		return "/user/products";

	}
	
	

}
