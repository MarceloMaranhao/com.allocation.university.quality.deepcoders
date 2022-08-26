package com.project.professor.allocation.deepcoders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.deepcoders.entity.Allocation;
import com.project.professor.allocation.deepcoders.repository.AllocationRepository;

@Service
public class AllocationService {
	
	private final AllocationRepository allocationRepository;

	public AllocationService(AllocationRepository allocationRepository) {
		super();
		this.allocationRepository = allocationRepository;
	}
	
	public List<Allocation> findByCourseId(Long courseId){
		List<Allocation> searched = allocationRepository.findByCourseId(courseId);
		return searched;
	}
	
	public List<Allocation> findByProfessorId(Long professorId){
		List<Allocation> searched = allocationRepository.findByProfessorId(professorId);
		return searched;
	}
	
	public Allocation findById(Long id) {
		Allocation allocation = allocationRepository.findById(id).orElse(null);
		return allocation;
	}

	public List<Allocation> findAll() {
		List<Allocation> allocations = allocationRepository.findAll();
		return allocations;
	}
	
	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		return allocationRepository.save(allocation);
	}
	
	public Allocation update(Allocation allocation) {
		Long id = allocation.getId();	
		if(id!=null && allocationRepository.existsById(id))
			return allocationRepository.save(allocation);
		else
			return null;
	}

	public void deleteById(Long id) {
		if(allocationRepository.existsById(id))
			allocationRepository.deleteById(id);
	}

	public void deleteAllInBatch() {
		allocationRepository.deleteAllInBatch();
	}
	
}
