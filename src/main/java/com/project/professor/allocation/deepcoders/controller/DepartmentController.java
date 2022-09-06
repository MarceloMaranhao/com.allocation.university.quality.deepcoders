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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.deepcoders.entity.Department;
import com.project.professor.allocation.deepcoders.service.DepartmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
	private final DepartmentService departmentService;
	
	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}
	
	@ApiOperation(value="Find all departments")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Department>> findAll(@RequestParam(name = "name", required = false) String name){
		List<Department> departments;
		if (name==null) {
			departments = departmentService.findAll();
		} else {
			departments = departmentService.findByNameContaining(name);
		}
		return new ResponseEntity<>(departments, HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Find a department by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "BAD REQUEST"),
		@ApiResponse(code = 404, message = "NOT FOUND")
	})
	@GetMapping(path = "/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Department> findById(@PathVariable(name = "department_id") Long id){
		Department department = departmentService.findById(id);
		if (department == null)
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Department>(department, HttpStatus.OK);
	}
	
	@ApiOperation(value="Create a department")
	@ApiResponses({
		@ApiResponse(code = 201, message = "CREATED"),
		@ApiResponse(code = 400, message = "BAD REQUEST")
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Department> create(@RequestBody Department department){
		try {
			Department newDepartment = departmentService.create(department);
			return new ResponseEntity<Department>(newDepartment, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="Update a department by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "BAD REQUEST"),
		@ApiResponse(code = 404, message = "NOT FOUND")
	})
	@PutMapping(path = "/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Department> update(@RequestBody Department department, 
											 @PathVariable(name = "department_id") Long departmentId){
		try {
			department.setId(departmentId);
			Department existingDepartment = departmentService.update(department);
			if(existingDepartment == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else { 
				return new ResponseEntity<Department>(existingDepartment, HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="Delete a department by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "NO CONTENT"),
		@ApiResponse(code = 404, message = "NOT FOUND")
	})
	@DeleteMapping(path = "/{department_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "department_id") Long id){
		departmentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="Delete all departments")
	@ApiResponses({
		@ApiResponse(code = 204, message = "NO CONTENT"),
		@ApiResponse(code = 404, message = "NOT FOUND")
	})
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll(){
		departmentService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
