package com.multi.semo.product.domain.query;

import com.multi.semo.product.domain.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findProducts(String search);
}
