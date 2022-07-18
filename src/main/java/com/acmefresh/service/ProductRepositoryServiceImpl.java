package com.acmefresh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acmefresh.model.ProductRepository;
import com.acmefresh.repository.ProductRepositoryDao;

@Service
public class ProductRepositoryServiceImpl implements ProductRepositoryService {

	@Autowired
	private ProductRepositoryDao productshub;
	@Override
	public ProductRepository  saveProduct(ProductRepository product) {
		return productshub.save(product);
	}

}
