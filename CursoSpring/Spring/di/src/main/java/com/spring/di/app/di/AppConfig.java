package com.spring.di.app.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.spring.di.app.di.repo.ProductRepo;
import com.spring.di.app.di.repo.ProductRepoJson;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Bean
    ProductRepo productRepoJson(){
        return new ProductRepoJson();
    }

}
