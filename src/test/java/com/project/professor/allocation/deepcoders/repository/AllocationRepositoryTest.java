package com.project.professor.allocation.deepcoders.repository;

import java.time.DayOfWeek;
import java.util.Date;
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
	public void create() {
		Allocation newAllocation = new Allocation();
		newAllocation.setCourseId(1L);
		newAllocation.setProfessorId(1L);
		newAllocation.setDay(DayOfWeek.MONDAY);
		Date start = new Date();
		start.setTime(1000999);
		newAllocation.setStart(start);
		Date end = new Date();
		end.setTime(1200999);
		newAllocation.setEnd(end);
		
		System.out.println(allocationRepository.save(newAllocation));
	}
	
	@Test
	public void update() {
		Allocation newAllocation = new Allocation();
		newAllocation.setProfessorId(2L);
		newAllocation.setId(1L);
		
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
