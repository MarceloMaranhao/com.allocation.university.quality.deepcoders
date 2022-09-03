package com.project.professor.allocation.deepcoders.repository;

import com.project.professor.allocation.deepcoders.entity.Department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	List<Department> findByNameContaining(String name);
}
