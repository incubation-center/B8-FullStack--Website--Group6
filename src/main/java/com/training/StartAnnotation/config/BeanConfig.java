package com.training.StartAnnotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.training.StartAnnotation.*;
import com.training.StartAnnotation.repository.StudentRepository;


@Configuration
@ComponentScan("com.training")
public class BeanConfig {

    @Bean
    public TestBean testBean(){
        return new TestBean();
    }
 
}
