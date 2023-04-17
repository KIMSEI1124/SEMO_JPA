package com.multi.semo.product.controller;

import com.multi.semo.product.dto.request.ProductSaveRequest;
import com.multi.semo.product.dto.request.ProductUpdateRequest;
import com.multi.semo.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin/product")
@RequiredArgsConstructor
@RestController
public class ProductModifyController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProductSaveRequest request) {
        productService.save(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Void> update(@RequestBody ProductUpdateRequest request) {
        productService.update(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@Param("productId") Long productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }
}
