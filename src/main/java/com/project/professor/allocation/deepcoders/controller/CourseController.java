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

import com.project.professor.allocation.deepcoders.entity.Course;
import com.project.professor.allocation.deepcoders.service.CourseService;

@RestController
@RequestMapping(path="/course")
public class CourseController {
	
	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Course>> findAll(@RequestParam(name="name") String name){
		List<Course> courses;
		if (name == null) {
			courses = courseService.findAll();
		} else {
			courses = courseService.findByNameContaining(name);
		}
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	@GetMapping(path="/{course_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> findById(@PathVariable(name="course_id") Long id){
		Course course = courseService.findById(id);
		if (course == null) {
			return new ResponseEntity<>(course, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(course, HttpStatus.OK);
		}
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> create(@RequestBody Course course){
		try {
			Course newCourse = courseService.create(course);
			return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
		}
		catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path="/{course_id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> update(@RequestBody Course course,
										@PathVariable(name="course_id") Long courseId){
		try {
			course.setId(courseId);
			Course existingCourse = courseService.update(course);
			if (existingCourse==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(existingCourse, HttpStatus.OK);
			}
		}
		catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path="/{Course_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name="Course_id") Long id){
		courseService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		courseService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
