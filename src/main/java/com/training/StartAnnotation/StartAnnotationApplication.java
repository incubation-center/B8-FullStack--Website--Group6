package com.training.StartAnnotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
public class StartAnnotationApplication {



	public static void main(String[] args) {
		SpringApplication.run(StartAnnotationApplication.class, args);
	}

}
