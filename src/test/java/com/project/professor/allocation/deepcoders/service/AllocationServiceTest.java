package com.project.professor.allocation.deepcoders.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.deepcoders.entity.Allocation;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

	@Autowired
	AllocationService allocationService;
	
	@Test
	public void findById() {
		System.out.println(allocationService.findById(1L));
	}
	
	@Test
	public void findByCourseId() {
		System.out.println(allocationService.findByCourseId(1L));
	}
	
	@Test
	public void findByProfessorId() {
		System.out.println(allocationService.findByProfessorId(2L));
	}
	
	@Test
	public void findAll() {
		System.out.println(allocationService.findAll());
	}
	
	@Test
	public void create() throws ParseException {
		Allocation newAllocation = new Allocation();
		newAllocation.setId(null);
		newAllocation.setCourseId(1L);
		newAllocation.setProfessorId(4L);
		newAllocation.setDay(DayOfWeek.MONDAY);
		SimpleDateFormat newDate = new SimpleDateFormat("HH:mmZ");
		newAllocation.setStart(newDate.parse("15:00-0300"));
		newAllocation.setEnd(newDate.parse("17:00-0300"));
		
		System.out.println(allocationService.create(newAllocation));
	}
	
	@Test
	public void update() throws ParseException {
		Allocation newAllocation = new Allocation();
		newAllocation.setId(2L);
		newAllocation.setCourseId(1L);
		newAllocation.setProfessorId(4L);
		newAllocation.setDay(DayOfWeek.MONDAY);
		SimpleDateFormat newDate = new SimpleDateFormat("HH:mmZ");
		newAllocation.setStart(newDate.parse("15:00-0300"));
		newAllocation.setEnd(newDate.parse("17:00-0300"));
		
		System.out.println(allocationService.create(newAllocation));
	}
	
	@Test
	public void deleteById() {
		allocationService.deleteById(2L);
	}
	
	@Test
	public void deleteAll() {
		allocationService.deleteAll();
	}
	
}
