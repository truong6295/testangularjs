package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Proxy;


/**
 * The persistent class for the Assignment database table.
 * 
 */
@Entity
@NamedQuery(name="Assignment.findAll", query="SELECT a FROM Assignment a")
@Proxy(lazy=false)
public class Assignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int assignmentId;

	private String testResult;

	//bi-directional many-to-one association to Personal
	@ManyToOne
	@JoinColumn(name="personalId")
	private Personal personal;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="tasksId")
	private Task task;

	public Assignment() {
	}
	public Assignment(String testResult,Personal personal,Task task) {
		this.testResult=testResult;
		this.personal=personal;
		this.task=task;
	}
	public int getAssignmentId() {
		return this.assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getTestResult() {
		return this.testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public Personal getPersonal() {
		return this.personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}