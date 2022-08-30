package com.project.professor.allocation.deepcoders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.deepcoders.entity.Allocation;
import com.project.professor.allocation.deepcoders.entity.Course;
import com.project.professor.allocation.deepcoders.entity.Professor;
import com.project.professor.allocation.deepcoders.repository.AllocationRepository;

@Service
public class AllocationService {
	
	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
    private final CourseService courseService;

	
	
	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
			CourseService courseService) {
		super();
		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
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
	
	private Allocation saveInternal(Allocation allocation) {
		long professorId = allocation.getProfessorId();
		Professor professor = professorService.findById(professorId);
		
		long courseId = allocation.getCourseId();
		Course course = courseService.findById(courseId);
		
		Allocation allocationSaved = allocationRepository.save(allocation);
		allocationSaved.setProfessor(professor);
		allocationSaved.setCourse(course);
		
		return allocationSaved;
	}
	
	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		
		return saveInternal(allocation);
	}
	
	public Allocation update(Allocation allocation) {
		Long id = allocation.getId();	
		if(id!=null && allocationRepository.existsById(id)) {
			return saveInternal(allocation);
		} else
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
