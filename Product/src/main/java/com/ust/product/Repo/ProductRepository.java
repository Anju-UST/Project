package com.ust.product.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.product.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
