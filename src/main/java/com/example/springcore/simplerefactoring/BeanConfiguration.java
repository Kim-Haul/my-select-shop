package com.example.springcore.simplerefactoring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 해당 어노테이션을 적어주면 스프링이 처음 기동될 때 이 부분을 읽음
public class BeanConfiguration {

    @Bean // 아래에서 return 되는 값을 bean 에 등록하겠다 !
    public ProductRepository productRepository() {
        String dbUrl = "jdbc:h2:mem:springcoredb";
        String dbId = "sa";
        String dbPassword = "";

        // 객체를 생성해서, 리턴
        return new ProductRepository(dbUrl, dbId, dbPassword);
    }
}