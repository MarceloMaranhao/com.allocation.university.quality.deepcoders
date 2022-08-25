package com.project.professor.allocation.deepcoders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.deepcoders.entity.Department;
import com.project.professor.allocation.deepcoders.repository.DepartmentRepository;

@Service
public class DepartmentService {
	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}
	
	public Department findById(Long id) {
		Department department = departmentRepository.findById(id).orElse(null);
		return department;
	}
	
	public List<Department> findAll(){
		List<Department> departments = departmentRepository.findAll();
		return departments;
	}
	
	public void create(String name) {
		Department newDepartment = new Department();
		newDepartment.setId(null);
		newDepartment.setName(name);
		departmentRepository.save(newDepartment);
	}
	
	public void update(Department department, String name) {
		Long id = department.getId();
		department.setId(id);
		department.setName(name);
		departmentRepository.save(department);
	}
	
	public void deleteById(Long id) {
		departmentRepository.deleteById(id);
	}
	
	public void deleteAllInBatch() {
		departmentRepository.deleteAllInBatch();
	}
}
