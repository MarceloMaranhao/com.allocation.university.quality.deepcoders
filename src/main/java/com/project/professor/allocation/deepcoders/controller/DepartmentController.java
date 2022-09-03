package com.project.professor.allocation.deepcoders.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.deepcoders.entity.Department;
import com.project.professor.allocation.deepcoders.service.DepartmentService;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
	private final DepartmentService departmentService;
	
	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Department>> findAll(@RequestParam(name = "name", required = false) String name){
		List<Department> departments;
		if (name==null) {
			departments = departmentService.findAll();
		} else {
			departments = departmentService.findByNameContaining(name);
		}
		return new ResponseEntity<>(departments, HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> findById(@PathVariable(name = "department_id") Long id){
		Department department = departmentService.findById(id);
		if (department == null)
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Department>(department, HttpStatus.OK);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> create(@RequestBody Department department){
		try {
			Department depart = departmentService.create(department);
			return new ResponseEntity<Department>(depart, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path = "/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> update(@RequestBody Department department, 
											 @PathVariable(name = "department_id") Long departmentId){
		try {
			department.setId(departmentId);
			Department depart = departmentService.update(department);
			if(depart == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else { 
				return new ResponseEntity<Department>(depart, HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "/{department_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "department_id") Long id){
		departmentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		departmentService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
