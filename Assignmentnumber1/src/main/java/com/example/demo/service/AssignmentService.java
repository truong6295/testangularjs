package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.AssignmentRepository;
import com.example.demo.dao.PersonalRepository;
import com.example.demo.dao.TaskRepository;
import com.example.demo.entity.Assignment;

@Service
public class AssignmentService {
	@Autowired
	private AssignmentRepository assignmentRepositor;
	@Autowired
	private PersonalRepository personalRepository;
	@Autowired
	private TaskRepository taskRepository;
	//add student
	public Assignment add(String testResult,int personalId,int taskId) {
		Assignment assignments=new Assignment(testResult,personalRepository.getOne(personalId),taskRepository.getOne(taskId)); 
		if(assignmentRepositor.save(assignments) != null)
			return assignments;
		else
			return null;
	}
	//update student
	public Assignment update(int id,String testResult,int personalId,int taskId) {
		Assignment assignments=new Assignment(testResult,personalRepository.getOne(personalId),taskRepository.getOne(taskId)); 
		assignments.setAssignmentId(id);
		if(assignmentRepositor.save(assignments)!=null)
			return assignments;
		else
			return null;
	}
	//delete
	public boolean delete(int id) {
		if(assignmentRepositor.exists(id)) {
			assignmentRepositor.delete(id);
				return true;	
		}
		return false;
	}
	//view 
	public List<Assignment> viewAll(){
		List<Assignment> assignments= assignmentRepositor.findAll();
			return assignments;
	}
	//view 1 entity
	public Assignment viewId(int id) {
		Assignment assignments=assignmentRepositor.findOne(id);
			return assignments;
	}
}
