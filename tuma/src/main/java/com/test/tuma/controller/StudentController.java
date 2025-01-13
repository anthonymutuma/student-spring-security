package com.test.tuma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.tuma.entity.Student;
import com.test.tuma.service.StudentService;

@RestController
@RequestMapping(path ="/api/v1/student")
public class StudentController {
	
	public final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	@GetMapping("/get")
	public List<Student> getStudents(){
		return studentService.getStudents();
	}
	
	@GetMapping("/get/{id}")
	public Object getStudent(@PathVariable("id") int id) {
		return studentService.getStudentById(id);
	}
	@PostMapping("/register")
	public void postStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable("id") int id) {
		studentService.deleteStudents(id);
	}

	@PutMapping("/put/{id}")
	public ResponseEntity<String> updateStudent(
	        @PathVariable("id") int id, 
	        @RequestBody Student student // Pass the entire Student object
	        ) {
	    //studentService.updateStudents(id, student); // Call the service with the Student object
		
		String responseMessage = studentService.updateStudents(id, student);
	    
	    if (responseMessage.contains("successfully")) {
	        return ResponseEntity.ok(responseMessage); // Return 200 OK with the success message
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage); // Return 404 with the error message
	    }
	}
}
