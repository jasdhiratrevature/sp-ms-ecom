package com.revature.ms.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.ms.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}