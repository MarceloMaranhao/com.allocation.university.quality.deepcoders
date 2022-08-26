package com.project.professor.allocation.deepcoders.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.deepcoders.entity.Allocation;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {
	
	@Autowired
    AllocationRepository allocationRepository;
	
	@Test
	public void findAll() {
		List<Allocation> AllAllocations = allocationRepository.findAll();
		System.out.println(AllAllocations);
	}
	
	@Test
	public void create() throws ParseException {
		Allocation newAllocation = new Allocation();
		newAllocation.setCourseId(1L);
		newAllocation.setProfessorId(4L);
		newAllocation.setDay(DayOfWeek.MONDAY);
		
		SimpleDateFormat newDate = new SimpleDateFormat("HH:mmZ");
		newAllocation.setStart(newDate.parse("12:00-0300"));
		newAllocation.setEnd(newDate.parse("14:00-0300"));
		
		System.out.println(allocationRepository.save(newAllocation));
	}
	
	@Test
	public void update() throws ParseException {
		Allocation newAllocation = new Allocation();
		newAllocation.setCourseId(1L);
		newAllocation.setProfessorId(4L);
		newAllocation.setId(1L);
		
		newAllocation.setDay(DayOfWeek.MONDAY);
		
		SimpleDateFormat newDate = new SimpleDateFormat("HH:mmZ");
		newAllocation.setStart(newDate.parse("10:00-0300"));
		newAllocation.setEnd(newDate.parse("12:00-0300"));
		
		System.out.println(allocationRepository.save(newAllocation));
	}
	
	@Test
	public void delete() {
		allocationRepository.deleteById(1L);
	}
	
	@Test
	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}

}