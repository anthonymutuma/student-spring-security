package com.test.tuma.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.tuma.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	
	@Query(nativeQuery = true,value="select * from student where username=?1")
	Optional<Student> findStudentByEmail(String email);

	Student findByUsername(String username);

}
