package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the Tasks database table.
 * 
 */
@Entity
@Table(name="Tasks")
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
@Proxy(lazy=false)
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tasksId;

	private String detail;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	private String nameTasks;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	//bi-directional many-to-one association to Assignment
	@OneToMany(mappedBy="task", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Assignment> assignments;

	public Task() {
	}
	public Task(String detail,Date endDate,String nameTasks,Date startDate) {
		this.detail=detail;
		this.endDate=endDate;
		this.startDate=startDate;
		this.nameTasks=nameTasks;
	}

	public int getTasksId() {
		return this.tasksId;
	}

	public void setTasksId(int tasksId) {
		this.tasksId = tasksId;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getNameTasks() {
		return this.nameTasks;
	}

	public void setNameTasks(String nameTasks) {
		this.nameTasks = nameTasks;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Set<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Assignment addAssignment(Assignment assignment) {
		getAssignments().add(assignment);
		assignment.setTask(this);

		return assignment;
	}

	public Assignment removeAssignment(Assignment assignment) {
		getAssignments().remove(assignment);
		assignment.setTask(null);

		return assignment;
	}

}