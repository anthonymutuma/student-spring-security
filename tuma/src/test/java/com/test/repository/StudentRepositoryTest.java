package com.test.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.test.tuma.entity.Role;
import com.test.tuma.entity.Student;
import com.test.tuma.repository.StudentRepository;

@DataJpaTest
public class StudentRepositoryTest {
    
    @Autowired
    private StudentRepository studentRepository;
    Student student;
    @BeforeEach
    void setUp() {
    	Student student = new Student();
    	student.setId(1);
    	student.setFirstname("Mutuma");
    	student.setLastname("Kathare");
    	student.setUsername("mutumakathare@gamil.com");
    	student.setDob(LocalDate.of(2001, Month.NOVEMBER, 14));
    	student.setRole(Role.ADMIN);
    	
    	studentRepository.save(student);
    }
    
    @AfterEach
    void tearDown() {
    	student=null;
    	studentRepository.deleteAll();
    }
    
    @Test
    void testFindByEmailSuccessCase() {
    
    	Optional<Student> expected	= studentRepository.findStudentByEmail("mutumakathare@gamil.com");
    	
    	assertThat(expected.get().getId()).isEqualTo(student.getId());
    	assertThat(expected.get().getFirstname()).isEqualTo(student.getFirstname());
    	assertThat(expected.get().getLastname()).isEqualTo(student.getLastname());
    	assertThat(expected.get().getUsername()).isEqualTo(student.getUsername());
    	assertThat(expected.get().getRole()).isEqualTo(student.getRole());
    	assertThat(expected.get().getDob()).isEqualTo(student.getDob());
  
    }
    
    
    
	/*
	 * @Test void itShouldCheckIfStudentExistsByEmail() { // given String email =
	 * "testingtester@gmail.com"; Student student = new Student(); //
	 * student.setId(1); student.setFirstname("Testing");
	 * student.setLastname("Tester"); student.setUsername(email);
	 * student.setPassword("12345"); student.setDob(LocalDate.of(2001, Month.AUGUST,
	 * 14)); student.setRole(Role.ADMIN);
	 * 
	 * Student stude = studentRepository.save(student);
	 * 
	 * // when Optional<Student> expected =
	 * studentRepository.findStudentByEmail(email);
	 * 
	 * // then assertThat(expected).isNotNull();
	 * assertThat(expected.get().getUsername()).isEqualTo(stude.getUsername()); }
	 */
}
