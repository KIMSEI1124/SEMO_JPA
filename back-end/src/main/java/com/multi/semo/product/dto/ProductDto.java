package com.multi.semo.product.dto;

import com.multi.semo.product.domain.Category;
import com.multi.semo.product.domain.Product;
import com.multi.semo.product.domain.Target;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ProductDto {
    private Long id;
    private String image;
    private String name;
    private int price;
    private Category category;
    private Target target;

    @Builder
    private ProductDto(Long id, String image, String name, int price, Category category, Target target) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.category = category;
        this.target = target;
    }

    public static ProductDto from(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .image(product.getImage())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .target(product.getTarget())
                .build();
    }
}
