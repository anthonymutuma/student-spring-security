package com.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.test.tuma.entity.Role;
import com.test.tuma.entity.Student;
import com.test.tuma.exception.StudentAddedSuccessfullyException;
import com.test.tuma.repository.StudentRepository;
import com.test.tuma.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
	
	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Mock
	StudentRepository studentRepository;
	
	@InjectMocks
	StudentService studentService;

	@Test
	public void addStudentShouldAddStudentSuccessfully() {
		
		 // Arrange: Set up the student and mock behavior
	    Student student = new Student();
	  //  student.setId(1);
	    student.setFirstname("TestjUnit");
	    student.setLastname("jUnitTest");
	    student.setUsername("Test1234@gmail.com");
	    student.setDob(LocalDate.of(2003, Month.MAY, 19));
	    student.setRole(Role.STUDENT_MAKER);
	    student.setPassword("123456");

	    when(bCryptPasswordEncoder.encode("123456")).thenReturn("encodedPassword");

	    // Act: Call the service method
	    assertThrows(StudentAddedSuccessfullyException.class, () -> {
	        studentService.addNewStudent(student);
	    });

	    // Capture the Student object passed to save
	    ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);
	    verify(studentRepository).save(studentCaptor.capture());
	    Student savedStudent = studentCaptor.getValue();

	    // Assert: Verify specific fields in the captured student
	  //  assertEquals(1, savedStudent.getId());
	    assertEquals("TestjUnit", savedStudent.getFirstname());
	    assertEquals("jUnitTest", savedStudent.getLastname());
	    assertEquals("Test1234@gmail.com", savedStudent.getUsername());
	    assertEquals("encodedPassword", savedStudent.getPassword()); // Verify the encoded password
	    assertEquals(LocalDate.of(2003, Month.MAY, 19), savedStudent.getDob());
	    assertEquals(Role.STUDENT_MAKER, savedStudent.getRole());

	    System.out.println("This is my first Unit test");
	}
	
}
