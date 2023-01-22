package com.example.springcore.simplerefactoring;

import com.example.springcore.models.Product;
import com.example.springcore.models.ProductMypriceRequestDto;
import com.example.springcore.models.ProductRequestDto;

import java.sql.*;
import java.util.List;

// 아직은 스프링이 제공하는 @Service 사용 하지 않고 !!
public class ProductService {

    private final ProductRepository productRepository;

    // 생성자
    //    public ProductService() {
    //        // DI(의존성 주입) 사용하기 -> 강한결합 해결 필요.
    //        ProductRepository productRepository = new ProductRepository();
    //        this.productRepository = productRepository;
    //    }

    // 생성자를 이렇게.
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product createProduct(ProductRequestDto requestDto) throws SQLException {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);

//        ProductRepository productRepository = new ProductRepository();
        productRepository.createProduct(product);

        return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) throws SQLException {

        if (requestDto.getMyprice() <= 0) {
            throw new RuntimeException("희망 최저가는 0 이상으로 설정해주세요.");
        }

//        ProductRepository productRepository = new ProductRepository();
        Product product = productRepository.getProduct(id);

        if (product == null) {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        productRepository.updateMyprice(id, requestDto.getMyprice());

        return product;
    }

    public List<Product> getProducts() throws SQLException {
//        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.getProducts();

        return products;

    }
}
