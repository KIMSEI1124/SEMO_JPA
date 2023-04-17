package com.multi.semo.product.domain;

import com.multi.semo.product.domain.embedded.Info;
import com.multi.semo.product.dto.request.ProductUpdateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Lob
    @Column(nullable = false)
    private String image;

    private Info info;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Target target;

    @Builder
    public Product(Long id, Category category, String image, Info info, String name, int price, int stock, Target target) {
        this.id = id;
        this.category = category;
        this.image = image;
        this.info = info;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.target = target;
    }

    /* Getter */
    public String getInfo() {
        return info.getValue();
    }

    /* 비즈니스 로직 */
    public void update(ProductUpdateRequest request) {
        this.category = request.getCategory();
        this.image = request.getImage();
        this.info = Info.of(request.getInfo());
        this.name = request.getName();
        this.price = request.getPrice();
        this.stock = request.getStock();
        this.target = request.getTarget();
    }
}
