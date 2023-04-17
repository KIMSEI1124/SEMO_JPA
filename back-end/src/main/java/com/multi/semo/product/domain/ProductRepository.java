package com.multi.semo.product.domain;

import com.multi.semo.product.domain.query.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    boolean existsByName(String name);
}
