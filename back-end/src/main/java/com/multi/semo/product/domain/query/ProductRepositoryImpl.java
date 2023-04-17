package com.multi.semo.product.domain.query;

import com.multi.semo.product.domain.Product;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.multi.semo.product.domain.QProduct.product;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public ProductRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Product> findProducts(String search) {
        return jpaQueryFactory
                .selectFrom(product)
                .where(hasSearch(search))
                .fetch();
    }

    private BooleanExpression hasSearch(String search) {
        return StringUtils.hasText(search) ? product.name.contains(search) : null;
    }
}
