package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {
	private static final SimpleDateFormat formatHHmm = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private TaskService taskService;
  //view all
    @RequestMapping(value = "/task/", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> listAllTask() {
        List<Task> tasks = taskService.viewAll();
        if (tasks.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }
    //view 1 entity
    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTask(@PathVariable("id") int id) {
    	Task tasks = taskService.viewId(id);
        return new ResponseEntity<Task>(tasks, HttpStatus.OK);
    }
    //creat
    @RequestMapping(value = "/task/",method = RequestMethod.POST)
    public ResponseEntity<?> creatTask(@RequestBody Task task, UriComponentsBuilder ucBuilder) throws ParseException {
        Task tasks=taskService.add(task.getDetail(), task.getEndDate(), task.getNameTasks(), task.getStartDate());
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/task/{id}").buildAndExpand(tasks.getTasksId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //update
    @RequestMapping(value = "/task/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTask(@RequestBody Task task) throws ParseException{
        Task currentTask=taskService.update(task.getTasksId(), task.getDetail(),task.getEndDate(), task.getNameTasks(),task.getStartDate());
        return new ResponseEntity<Task>(currentTask, HttpStatus.OK);
    }
    //delete 1 entity
    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTask(@PathVariable("id") int taskId) {
        if(taskService.delete(taskId)) {
        	
        	return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
        }
        else
        	return new ResponseEntity("no delete personal with id {} khong co.",HttpStatus.NOT_FOUND);
    }
}
