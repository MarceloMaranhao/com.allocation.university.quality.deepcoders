package com.project.professor.allocation.deepcoders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.deepcoders.entity.Professor;
import com.project.professor.allocation.deepcoders.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;

	public ProfessorService(ProfessorRepository professorRepository) {
		super();
		this.professorRepository = professorRepository;
	}
	
	public List<Professor> findByDepartmentId(Long id){
		List<Professor> professors = professorRepository.findByDepartmentId(id);
		return professors;
	}
	
	public Professor findByCpf(String cpf) {
		Professor searched = professorRepository.findByCpf(cpf);
		return searched;
	}
	
	public List<Professor> findByNameLike(String name){
		List<Professor> professors = professorRepository.findByNameLike(name);
		return professors;
	}

	public Professor findById(Long id) {
		Professor professor = professorRepository.findById(id).orElse(null);
		return professor;
	}

	public List<Professor> findAll() {
		List<Professor> professors = professorRepository.findAll();
		return professors;
	}

	public void create(String name, String cpf, Long departmentId) {
		Professor newProfessor = new Professor();
		newProfessor.setId(null);
		newProfessor.setName(name);
		newProfessor.setCpf(cpf);
		newProfessor.setDepartmentId(departmentId);
		professorRepository.save(newProfessor);
	}

	public void update(Professor professor, String name, String cpf) {
		Long id = professor.getId();
		Long departmentId = professor.getDepartmentId();
		professor.setId(id);
		professor.setName(name);
		professor.setCpf(cpf);
		professor.setDepartmentId(departmentId);
		professorRepository.save(professor);
	}

	public void deleteById(Long id) {
		professorRepository.deleteById(id);
	}

	public void deleteAllInBatch() {
		professorRepository.deleteAllInBatch();
	}

}
