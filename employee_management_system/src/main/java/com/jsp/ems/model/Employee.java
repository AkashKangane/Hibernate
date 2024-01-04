package com.jsp.ems.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "employee_details")
@Cacheable
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "employee_name", updatable = false, nullable = false)
	private String name;
	private String position;
	@Column(nullable = false)
	private double salary;
	@CreationTimestamp
	@Column(name = "date_of_joining")
	private LocalDateTime doj;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "employee_id"),inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> projects;

	public Employee() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDateTime getDoj() {
		return doj;
	}

	public void setDoj(LocalDateTime doj) {
		this.doj = doj;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
}
