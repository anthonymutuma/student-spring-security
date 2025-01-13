package com.test.tuma;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TumaApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(TumaApplication.class, args);
	}
	
	@Value("${starter.property.show}")
	public String applicationProperty;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Application has Started :::"+applicationProperty);
		
	}

}
