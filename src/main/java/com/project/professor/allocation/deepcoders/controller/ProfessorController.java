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

import com.project.professor.allocation.deepcoders.service.ProfessorService;
import com.project.professor.allocation.deepcoders.entity.Professor;

@RestController
@RequestMapping(path="/professor")
public class ProfessorController {
	private final ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		super();
		this.professorService = professorService;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Professor>> findAll(@RequestParam(name="name") String name){
		List<Professor> professors;
		if (name == null) {
			professors = professorService.findAll();
		} else {
			professors = professorService.findByNameContaining(name);
		}
		return new ResponseEntity<>(professors, HttpStatus.OK);
	}
	
	@GetMapping(path="/{professor_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> findById(@PathVariable(name="professor_id") Long id){
		Professor professor = professorService.findById(id);
		if (professor == null) {
			return new ResponseEntity<>(professor, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(professor, HttpStatus.OK);
		}
	}
	
	@GetMapping(path="/{cpf}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> findByCpf(@RequestParam(name="cpf") String cpf){
		Professor professor;
		if (cpf == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			professor = professorService.findByCpf(cpf);
			if (professor==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else {
				return new ResponseEntity<>(professor, HttpStatus.OK);
			}
		}
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> create(@RequestBody Professor professor){
		try {
			Professor newProfessor = professorService.create(professor);
			return new ResponseEntity<>(newProfessor, HttpStatus.CREATED);
		}
		catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path="/{professor_id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> update(@RequestBody Professor professor,
											@PathVariable(name="professor_id") Long professorId){
		try {
			professor.setId(professorId);
			Professor existingProfessor = professorService.update(professor);
			if (existingProfessor==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(existingProfessor, HttpStatus.OK);
			}
		}
		catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path="/{professor_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name="professor_id") Long id){
		professorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		professorService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
