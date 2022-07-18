package com.acmefresh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acmefresh.model.ProductRepository;
import com.acmefresh.service.ProductRepositoryService;

@RestController
public class ProductRepositoryController {

	private ProductRepositoryService PrService;
	
	// http://localhost:8082/product
		// create productdetails 
		@PostMapping("/product")
		public ResponseEntity<ProductRepository> saveProductHandler(@RequestBody ProductRepository product){
			
			ProductRepository savedProduct = PrService.saveProduct(product);
		
			
			return new ResponseEntity<ProductRepository>(savedProduct,HttpStatus.CREATED);
			
		}
}
