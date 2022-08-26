package com.project.professor.allocation.deepcoders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.deepcoders.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	List<Course> findByNameLike(String name);
	
}