package com.project.professor.allocation.deepcoders.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	public ResponseEntity<List<Professor>> findAll(){
		List<Professor> professors = professorService.findAll();
		return new ResponseEntity<>(professors, HttpStatus.OK);
	} 
	
}
