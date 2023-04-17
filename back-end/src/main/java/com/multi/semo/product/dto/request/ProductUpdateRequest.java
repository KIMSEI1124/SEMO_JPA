package com.multi.semo.product.dto.request;

import com.multi.semo.product.domain.Category;
import com.multi.semo.product.domain.Target;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ProductUpdateRequest {
    private Long id;
    private Category category;
    private String image;
    private String info;
    private String name;
    private int price;
    private int stock;
    private Target target;

    @Builder
    public ProductUpdateRequest(Long id, Category category, String image, String info, String name, int price, int stock, Target target) {
        this.id = id;
        this.category = category;
        this.image = image;
        this.info = info;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.target = target;
    }
}
