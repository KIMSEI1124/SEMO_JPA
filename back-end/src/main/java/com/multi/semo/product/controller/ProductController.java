package com.multi.semo.product.controller;

import com.multi.semo.product.dto.request.ProductSaveRequest;
import com.multi.semo.product.dto.request.ProductUpdateRequest;
import com.multi.semo.product.dto.response.ProductsResponse;
import com.multi.semo.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product")
@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping("/admin")
    public ResponseEntity<Void> save(@RequestBody ProductSaveRequest request) {
        productService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ProductsResponse> findProducts(@Param("search") String search) {
        ProductsResponse response = productService.findProducts(search);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/admin")
    public ResponseEntity<Void> update(@RequestBody ProductUpdateRequest request) {
        productService.update(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin")
    public ResponseEntity<Void> delete(@Param("productId") Long productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }
}
