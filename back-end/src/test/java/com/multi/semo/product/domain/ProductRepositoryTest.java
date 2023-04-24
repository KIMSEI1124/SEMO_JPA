package com.multi.semo.product.domain;

import com.multi.semo.product.domain.embedded.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    private Product saveProduct;

    @BeforeEach
    void setUp() {
        Product product = Product.builder()
                .category(Category.TOP)
                .image("base64")
                .info(Info.of("상의 정보 입니다."))
                .name("상의")
                .price(20000)
                .stock(10)
                .target(Target.MEN)
                .build();

        saveProduct = productRepository.save(product);
    }

    @DisplayName("제품을 저장한다.")
    @Test
    void saveTest() {
        // given
        Product product = Product.builder()
                .category(Category.HAT)
                .image("base64")
                .info(Info.of("모자 정보 입니다."))
                .name("모자")
                .price(10000)
                .stock(10)
                .target(Target.MEN)
                .build();

        // when
        Product actual = productRepository.save(product);

        // then
        assertThat(actual).isNotNull();
    }

    @DisplayName("제품이 존재하는지 검사한다.")
    @Test
    void existTest() {
        // given
        // when
        boolean actual = productRepository.existsByName(saveProduct.getName());

        // then
        assertThat(actual).isTrue();
    }
}