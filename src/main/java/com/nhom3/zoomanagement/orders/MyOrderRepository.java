package com.nhom3.zoomanagement.orders;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface MyOrderRepository extends JpaRepository<MyOrder, Integer> {
    
}
