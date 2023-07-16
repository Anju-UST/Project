package com.ust.cart.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.cart.Model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {}
