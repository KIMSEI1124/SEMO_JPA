package com.multi.semo.product.service;

import com.multi.semo.product.domain.Product;
import com.multi.semo.product.domain.ProductRepository;
import com.multi.semo.product.domain.embedded.Info;
import com.multi.semo.product.dto.request.ProductSaveRequest;
import com.multi.semo.product.exception.ProductErrorCode;
import com.multi.semo.product.exception.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Product save(ProductSaveRequest request) {
        validateIsExist(request.getName());

        Product saveProduct = Product.builder()
                .category(request.getCategory())
                .image(request.getImage())
                .info(Info.of(request.getInfo()))
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .target(request.getTarget())
                .build();

        return productRepository.save(saveProduct);
    }

    private void validateIsExist(String name) {
        if (isExist(name)) {
            throw new ProductException(ProductErrorCode.PRODUCT_NAME_DUPLICATED);
        }
    }

    private boolean isExist(String name) {
        return productRepository.existsByName(name);
    }
}
