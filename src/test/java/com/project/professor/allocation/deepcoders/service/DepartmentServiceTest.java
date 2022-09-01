package com.project.professor.allocation.deepcoders.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.deepcoders.entity.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {

	@Autowired
	DepartmentService departmentService;
	
	@Test
	public void findById() {
		System.out.println(departmentService.findById(1L));
	}
	
	@Test
	public void findAll() {
		System.out.println(departmentService.findAll());
	}
	
	@Test
	public void create() {
		Department newDepartment = new Department();
		newDepartment.setId(null);
		newDepartment.setName("Science Department");
		
		System.out.println(departmentService.create(newDepartment));
	}
	
	@Test
	public void update() {
		Department newDepartment = new Department();
		newDepartment.setId(2L);
		newDepartment.setName("Science Fiction Department");
		
		System.out.println(departmentService.update(newDepartment));
	}
	
	@Test
	public void deleteById() {
		departmentService.deleteById(2L);
	}
	
	@Test
	public void deleteAll() {
		departmentService.deleteAll();
	}
}
