package com.test.tuma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.tuma.entity.Student;
import com.test.tuma.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents(){
		return studentRepository.findAll();
		
	}

	public void addNewStudent(Student student) {
		Student std=new Student();
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getUsername());
		
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("Email Is Present");
		}
		std.setUsername(student.getUsername());
		std.setDob(student.getDob());
		std.setFirstname(student.getFirstname());
		std.setLastname(student.getLastname());
		std.setRole(student.getRole());
		std.setPassword(encoder.encode(student.getPassword()));
		studentRepository.save(std);
		System.out.println("Student added successfully" + student);	
		
	}

	public void deleteStudents(Long id) {
		boolean exists = studentRepository.existsById(id);
		
		if(!exists) {
			throw new IllegalStateException("The student with id : " +id+ " does not exist in the system");
		}
		studentRepository.deleteById(id);
	}

	@Transactional
	public String updateStudents(Long id, Student studentUpdate) {
	    Optional<Student> optionalStudent = studentRepository.findById(id);
	    
	    if (optionalStudent.isPresent()) {
	    	Student student = optionalStudent.get();
	        
	        // Update the student's name if provided
	        if (studentUpdate.getFirstname() != null && !studentUpdate.getFirstname().isEmpty()) {
	            student.setFirstname(studentUpdate.getFirstname());
	        }
	        
	        // Update the student's email if provided
	        if (studentUpdate.getUsername() != null && !studentUpdate.getUsername().isEmpty()) {
	            student.setUsername(studentUpdate.getUsername());
	        }
	        
	        // Save the updated student back to the repository
	        studentRepository.save(student);
	        
	        return "Student updated successfully!";
	        
	    } else {
	        throw new IllegalStateException("Student with id " + id + " does not exist.");
	    }
	}

}
