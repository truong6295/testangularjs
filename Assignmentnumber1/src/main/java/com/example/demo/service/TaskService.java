package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TaskRepository;
import com.example.demo.entity.Task;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	//add student
	public Task add(String detail,Date endDate,String nameTasks,Date startDate) {
		Task tasks=new Task(detail, endDate, nameTasks, startDate);
		if(taskRepository.save(tasks) != null)
			return tasks;
		else
			return null;
	}
	//update student
	public Task update(int id,String detail,Date endDate,String nameTasks,Date startDate) {
		Task tasks=new Task(detail, endDate, nameTasks, startDate);
		tasks.setTasksId(id);
		if(taskRepository.save(tasks)!=null)
			return tasks;
		else
			return null;
	}
	//delete
	public boolean delete(int id) {
		if(taskRepository.exists(id)) {
			taskRepository.delete(id);
			return true;	
		}
		return false;
	}
	//view 
	public List<Task> viewAll(){
		List<Task> tasks= taskRepository.findAll();
		return tasks;
	}
	//view 1 entity
	public Task viewId(int id) {
		Task tasks=taskRepository.findOne(id);
		return tasks;
	}
}
