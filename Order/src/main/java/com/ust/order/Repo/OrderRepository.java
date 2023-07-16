package com.ust.order.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.order.Model.Order;



@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {
    
}
