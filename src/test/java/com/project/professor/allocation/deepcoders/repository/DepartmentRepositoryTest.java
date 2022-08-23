package com.project.professor.allocation.deepcoders.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.deepcoders.entity.Department;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {
	@Autowired
    DepartmentRepository departmentRepository;
	
	@Test
	public void findAll() {
		List<Department> AllDepartments = departmentRepository.findAll();
		System.out.println(AllDepartments);
	}
	
	@Test
	public void create() {
		Department newDepartment = new Department();
		newDepartment.setName("Math Department");
		
		System.out.println(departmentRepository.save(newDepartment));
	}
	
	@Test
	public void update() {
		Department newDepartment = new Department();
		newDepartment.setName("English Department");
		newDepartment.setId(1L);
		
		System.out.println(departmentRepository.save(newDepartment));
	}
	
	@Test
	public void delete() {
		departmentRepository.deleteById(1L);
	}
	
	@Test
	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}
}
