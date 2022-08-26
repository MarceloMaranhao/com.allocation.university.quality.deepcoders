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
	
	public List<Professor> findByNameContaining(String name){
		List<Professor> professors = professorRepository.findByNameContaining(name);
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

	public Professor create(Professor professor) {
		professor.setId(null);
		return professorRepository.save(professor);
	}

	public Professor update(Professor professor) {
		Long id = professor.getId();
		if(id!=null && professorRepository.existsById(id))
			return professorRepository.save(professor);
		else
			return null;
	}

	public void deleteById(Long id) {
		if(professorRepository.existsById(id))
			professorRepository.deleteById(id);
	}

	public void deleteAllInBatch() {
		professorRepository.deleteAllInBatch();
	}

}
