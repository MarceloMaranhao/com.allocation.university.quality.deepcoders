package com.project.professor.allocation.deepcoders.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
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

	public void create(Long courseId, Long professorId, DayOfWeek day, String start, String end) throws ParseException {
		SimpleDateFormat newDate = new SimpleDateFormat("HH:mmZ");
		Allocation newAllocation = new Allocation();
		newAllocation.setId(null);
		newAllocation.setCourseId(courseId);
		newAllocation.setProfessorId(professorId);
		newAllocation.setDay(day);
		newAllocation.setStart(newDate.parse(start));
		newAllocation.setEnd(newDate.parse(end));
		
		allocationRepository.save(newAllocation);
	}

	public void update(Allocation allocation, Long courseId, Long professorId, DayOfWeek day, String start, String end) throws ParseException {
		SimpleDateFormat newDate = new SimpleDateFormat("HH:mmZ");
		Long id = allocation.getId();
		
		allocation.setId(id);
		allocation.setCourseId(courseId);
		allocation.setProfessorId(professorId);
		allocation.setDay(day);
		allocation.setStart(newDate.parse(start));
		allocation.setEnd(newDate.parse(end));
		
		allocationRepository.save(allocation);
	}

	public void delete(Allocation allocation) {
		allocationRepository.delete(allocation);
	}

	public void deleteAllInBatch() {
		allocationRepository.deleteAllInBatch();
	}
}
