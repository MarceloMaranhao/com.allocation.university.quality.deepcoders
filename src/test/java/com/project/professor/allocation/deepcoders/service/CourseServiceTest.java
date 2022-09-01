package com.project.professor.allocation.deepcoders.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.deepcoders.entity.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

	@Autowired
	CourseService courseService;
	
	@Test
	public void findById() {
		System.out.println(courseService.findById(1L));
	}
	
	@Test
	public void findAll() {
		System.out.println(courseService.findAll());
	}
	
	@Test
	public void findByNameContaining(){
		List<Course> courses = courseService.findByNameContaining("Logic");
		System.out.println(courses);
	}
	
	@Test
	public void create() {
		Course newCourse = new Course();
		newCourse.setId(null);
		newCourse.setName("Science Logic");
		
		System.out.println(courseService.create(newCourse));
	}
	
	@Test
	public void update() {
		Course newCourse = new Course();
		newCourse.setId(2L);
		newCourse.setName("Science Fiction Logic");
		
		System.out.println(courseService.create(newCourse));
	}
	
	@Test
	public void deleteById() {
		courseService.deleteById(2L);
	}

	@Test
	public void deleteAll() {
		courseService.deleteAll();
	}
}
