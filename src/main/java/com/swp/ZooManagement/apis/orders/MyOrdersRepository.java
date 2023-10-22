package com.swp.ZooManagement.apis.orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyOrdersRepository extends JpaRepository<MyOrder, String> {
}
