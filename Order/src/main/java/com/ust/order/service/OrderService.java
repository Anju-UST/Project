package com.ust.order.service;

import org.springframework.stereotype.Service;

import com.ust.order.Model.Order;



@Service
public interface OrderService {
    long placeOrder(Order order);

    Order getOrderDetails(long orderId);
    Order updateOrder(Order order);
    void generateBill(Order order);
}
