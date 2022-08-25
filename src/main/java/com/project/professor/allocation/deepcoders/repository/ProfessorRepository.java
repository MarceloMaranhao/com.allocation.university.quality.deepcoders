package com.project.professor.allocation.deepcoders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.deepcoders.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	
	List<Professor> findByDepartmentId(Long departmentId);
	
	Professor findByCpf(String cpf);
	
	List<Professor> findByNameLike(String name);
}
