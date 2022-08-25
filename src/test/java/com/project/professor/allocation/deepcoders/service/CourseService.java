package com.project.professor.allocation.deepcoders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.deepcoders.entity.Course;
import com.project.professor.allocation.deepcoders.repository.CourseRepository;

@Service
public class CourseService {
	
	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}
	
	public List<Course> findByNameLike(String name){
		List<Course> courses = courseRepository.findByNameLike(name);
		return courses;
	}
	
	public Course findById(Long id) {
		Course Course = courseRepository.findById(id).orElse(null);
		return Course;
	}

	public List<Course> findAll() {
		List<Course> Courses = courseRepository.findAll();
		return Courses;
	}

	public void create(String name) {
		Course newCourse = new Course();
		newCourse.setId(null);
		newCourse.setName(name);
		courseRepository.save(newCourse);
	}

	public void update(Course Course, String name, String cpf) {
		Long id = Course.getId();
		Course.setId(id);
		Course.setName(name);
		courseRepository.save(Course);
	}

	public void delete(Course Course) {
		courseRepository.delete(Course);
	}

	public void deleteAllInBatch() {
		courseRepository.deleteAllInBatch();
	}

}
