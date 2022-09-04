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

import com.project.professor.allocation.deepcoders.entity.Allocation;
import com.project.professor.allocation.deepcoders.service.AllocationService;

public class AllocationController {
	private final AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAll() {
		List<Allocation> allocations;
		allocations = allocationService.findAll();
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}
	
	@GetMapping(path="/{allocation_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> findById(@PathVariable(name="allocation_id") Long id){
		Allocation allocation = allocationService.findById(id);
		if (allocation == null) {
			return new ResponseEntity<>(allocation, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		}
	}
	
	@GetMapping(path = "/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findByProfessorId(@PathVariable(name="professor_id") Long professorId){
		List<Allocation> searchedAllocation = allocationService.findByProfessorId(professorId);
		if (searchedAllocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(searchedAllocation, HttpStatus.OK);
		}
	}
	
	@GetMapping(path = "/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findByCourseId(@PathVariable(name="course_id") Long courseId){
		List<Allocation> searchedAllocation = allocationService.findByCourseId(courseId);
		if (searchedAllocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(searchedAllocation, HttpStatus.OK);
		}
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> create(@RequestBody Allocation allocation){
		try {
			Allocation newAllocation = allocationService.create(allocation);
			return new ResponseEntity<Allocation>(newAllocation, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<Allocation>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path="/{allocation_id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> update(@RequestBody Allocation allocation,
											@PathVariable(name="allocation_id") Long allocationId){
		try {
			allocation.setId(allocationId);
			Allocation existingAllocation = allocationService.update(allocation);
			if (existingAllocation==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(existingAllocation, HttpStatus.OK);
			}
		}
		catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path="/{allocation_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name="allocation_id") Long id){
		allocationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		allocationService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
