package com.example.demo.controller;

import java.beans.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entity.Assignment;
import com.example.demo.service.AssignmentService;


@RestController
@RequestMapping("/api")
public class AssignmentController {
	private static final SimpleDateFormat formatHHmm = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private AssignmentService assignmentService;
  //view all
    @RequestMapping(value = "/assignment/", method = RequestMethod.GET)
    public ResponseEntity<List<Assignment>> listAllAssignment() {
        List<Assignment> assignments = assignmentService.viewAll();
        if (assignments.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Assignment>>(assignments, HttpStatus.OK);
    }
    //view 1 entity
    @RequestMapping(value = "/assignment/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAssignment(@PathVariable("id") int id) {
    	Assignment assignments = assignmentService.viewId(id);
        return new ResponseEntity<Assignment>(assignments, HttpStatus.OK);
    }
    //creat
    @RequestMapping(value = "/assignment/",method = RequestMethod.POST)
    public ResponseEntity<?> creatAssignment(Assignment assignment, UriComponentsBuilder ucBuilder) throws ParseException {
        Assignment assignments=assignmentService.add(assignment.getTestResult(),assignment.getPersonal().getPersonalId(),assignment.getTask().getTasksId());
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/assignment/{id}").buildAndExpand(assignments.getAssignmentId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    //update
    @RequestMapping(value = "/assignment/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAssignment(Assignment assignment) throws ParseException{
        Assignment currentAssignment=assignmentService.update(assignment.getAssignmentId(),assignment.getTestResult(),assignment.getPersonal().getPersonalId(),assignment.getTask().getTasksId());
        return new ResponseEntity<Assignment>(currentAssignment, HttpStatus.OK);
    }
    //delete 1 entity
    @RequestMapping(value = "/assignment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAssignment(@PathVariable("id") int assignmentId) {
        if(assignmentService.delete(assignmentId)) {
        	
        	return new ResponseEntity<Assignment>(HttpStatus.NO_CONTENT);
        }
        else
        	return new ResponseEntity("no delete personal with id {} khong co.",HttpStatus.NOT_FOUND);
    }
}
