package com.example.springcore.simplerefactoring;

import com.example.springcore.models.Product;
import com.example.springcore.models.ProductMypriceRequestDto;
import com.example.springcore.models.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

// @RequiredArgsConstructor // final로  선언된 멤버 변수를 자동으로 생성(생성자)합니다.
@RestController // JSON 으로 데이터를 주고받음을 선언합니다.
public class ProductController {

    private final ProductService productService;

    // 생성자
    //    public ProductController() {
    //        // DI(의존성 주입) 사용하기 -> 강한결합 해결 필요.
    //        ProductService productService = new ProductService();
    //        this.productService = productService;
    //    }

    //  생성자를 이렇게.
    //  DI 란 미리 만들어져있는 객체를 이렇게 가져다가 쓰는 것. (ProductService productService) 이 부분.
        @Autowired // productService 에 대한 DI 가 필요하면 어노테이션 추가 (ProductController 에 대한 생성자가 1개일 때는, 생략 가능)
        public ProductController(ProductService productService) {
            this.productService = productService;
        }

    // 신규 상품 등록
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) throws SQLException {
//        ProductService productService = new ProductService();
        Product product = productService.createProduct(requestDto);

    // 응답 보내기
        return product;
    }

    // 설정 가격 변경
    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) throws SQLException {
//        ProductService productService = new ProductService();
        Product product = productService.updateProduct(id, requestDto);

    // 응답 보내기 (업데이트된 상품 id)
        return product.getId();
    }

    // 등록된 전체 상품 목록 조회
    @GetMapping("/api/products")
    public List<Product> getProducts() throws SQLException {
//        ProductService productService = new ProductService();
        List<Product> products = productService.getProducts();
        
    // 응답 보내기
        return products;
    }
}

