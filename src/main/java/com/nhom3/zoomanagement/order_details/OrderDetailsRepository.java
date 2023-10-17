package com.nhom3.zoomanagement.order_details;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer> {
}
