package com.multi.semo.product.controller;

import com.multi.semo.product.dto.response.ProductsResponse;
import com.multi.semo.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/product")
@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductsResponse> findProducts(@Param("search") String search) {
        ProductsResponse response = productService.findProducts(search);
        return ResponseEntity.ok(response);
    }
}
