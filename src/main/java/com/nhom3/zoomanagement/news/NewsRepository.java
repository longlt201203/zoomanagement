package com.nhom3.zoomanagement.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NewsRepository extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {
}
