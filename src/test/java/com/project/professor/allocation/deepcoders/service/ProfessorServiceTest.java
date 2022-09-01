package com.project.professor.allocation.deepcoders.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.deepcoders.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTest {
	
	@Autowired
	ProfessorService professorService;
	
	@Test
	public void findById() {
		System.out.println(professorService.findById(5L));
	}
	
	@Test
	public void findByDepartmentId() {
		System.out.println(professorService.findByDepartmentId(1L));
	}
	
	@Test
	public void findByCpf() {
		System.out.println(professorService.findByCpf("12345678900"));
	}
	
	@Test
	public void findByNameContaining() {
		System.out.println(professorService.findByNameContaining("Lopes"));
	}
	
	@Test
	public void findAll() {
		System.out.println(professorService.findAll());
	}
	
	@Test
	public void create() {
		Professor newProfessor = new Professor();
		newProfessor.setId(null);
		newProfessor.setName("Rafael Portugal");
		newProfessor.setCpf("23456789001");
		newProfessor.setDepartmentId(1L);
		
		System.out.println(professorService.create(newProfessor));
	}
	
	@Test
	public void update() {
		Professor newProfessor = new Professor();
		newProfessor.setId(5L);
		newProfessor.setName("Mario Covas");
		newProfessor.setCpf("23456789001");
		newProfessor.setDepartmentId(1L);
		
		System.out.println(professorService.update(newProfessor));
	}
	
	@Test
	public void deleteById() {
		professorService.deleteById(5L);
	}
	
	@Test
	public void deleteAll() {
		professorService.deleteAll();
	}

}
