package com.multi.semo.product.dto.response;

import com.multi.semo.product.dto.ProductDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ProductsResponse {
    List<ProductDto> products;
    int count;

    @Builder
    private ProductsResponse(List<ProductDto> products, int count) {
        this.products = products;
        this.count = count;
    }

    public static ProductsResponse from(List<ProductDto> products) {
        return ProductsResponse.builder()
                .products(products)
                .count(products.size())
                .build();
    }
}
