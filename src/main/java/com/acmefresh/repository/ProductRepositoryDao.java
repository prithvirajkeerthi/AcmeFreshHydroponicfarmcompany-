package com.acmefresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acmefresh.model.ProductRepository;
@Repository
public interface ProductRepositoryDao extends JpaRepository<ProductRepository, Integer> {

}
