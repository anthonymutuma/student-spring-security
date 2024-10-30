package com.test.tuma.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.tuma.entity.Student;
import com.test.tuma.repository.StudentRepository;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
//			User mutuma = new User(
//					"Mutuma",
//					"Tuma",
//					"mutuma@gmail.com",
//					"1111",
//					LocalDate.of(2000, Month.AUGUST, 5)
//					);
//			
//			User anthony = new User(
//					"Anthony",
//					"Tonny",
//					"anthony@gmail.com",
//					"2222",
//					LocalDate.of(2001, Month.DECEMBER, 10)
//					);
//			
//			User kathare = new User(
//					"Kathare",
//					"Tush",
//					"kathare@gmail.com",
//					"2222",
//					LocalDate.of(2002, Month.JUNE, 17)
//					);
//			repository.saveAll(List.of(mutuma, anthony, kathare));
		};
		
	}

}
