package com.jsp.ems.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.jsp.ems.model.Department;
import com.jsp.ems.model.Employee;
import com.jsp.ems.model.Project;

public class Controller {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgsql");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public boolean addEmployee(List<Employee> employees) {
		if (employees != null) {
			entityTransaction.begin();
			for (Employee employee2 : employees) {
				entityManager.persist(employee2);
			}
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	public Employee ReadEmployee(int employeeId) {
		return entityManager.find(Employee.class, employeeId);
	}

	public void updateEmployeeSalary(int employeeId, double salary) {
		Employee employee = entityManager.find(Employee.class, employeeId);
		if (employee != null) {

		}
	}

	public boolean updateEmployeeDepartment(int employeeId, Department departmentId) {
		Employee employee = entityManager.find(Employee.class, employeeId);
		if (employee != null) {
			employee.setDepartment(departmentId);
			entityTransaction.begin();
			entityManager.merge(employee);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	public boolean updateEmployeeProject(int employeeId, List<Project> projectId) {
		Employee employee = entityManager.find(Employee.class, employeeId);
		if (employee != null) {
			employee.setProjects(projectId);
			entityTransaction.begin();
			entityManager.merge(employee);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	public List<Employee> fetchAllEmployee() {
		String jpql = "SELECT s FROM Employee s";
		TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
		return query.getResultList();
	}

	public boolean addDepartment(Department department) {
		if (department != null) {
			entityTransaction.begin();
			entityManager.persist(department);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	public Department ReadDepartment(int departmentId) {
		return entityManager.find(Department.class, departmentId);
	}

	public List<Department> fetchAllDepartment() {
		String jpql = "SELECT s FROM Department s";
		TypedQuery<Department> query = entityManager.createQuery(jpql, Department.class);
		return query.getResultList();
	}

	public boolean addProject(Project project) {
		if (project != null) {
			entityTransaction.begin();
			entityManager.persist(project);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	public Project ReadProject(int projectId) {
		return entityManager.find(Project.class, projectId);
	}

	public List<Project> fetchAllProject() {
		String jpql = "SELECT s FROM Project s";
		TypedQuery<Project> query = entityManager.createQuery(jpql, Project.class);
		return query.getResultList();
	}
	
	public boolean updateProjectDetails(int projectId, String projectName, String Description) {
		Project project = entityManager.find(Project.class, projectId);
		if (project!=null) {
			project.setName(projectName);
			project.setDescription(Description);
			entityTransaction.begin();
			entityManager.merge(project);
			entityTransaction.commit();
			return true;
		}
		return false;	
	}
	
	public boolean updateDepartmentDetails(int empId, String DeptName) {
		Department department = entityManager.find(Department.class, DeptName);
		if (department!=null) {
			department.setName(DeptName);
			entityTransaction.begin();
			entityManager.merge(department);
			entityTransaction.commit();
			return true;
		}
		return false;
	}
	
	public boolean removeEmployee(int empId) {
		Employee employee = entityManager.find(Employee.class, empId);
		if (employee!=null) {
			employee.setDepartment(null);
			employee.setProjects(null);
			entityTransaction.begin();
			entityManager.remove(employee);
			entityTransaction.commit();
			return true;
		}
		return false;
	}
	
	public boolean removeProject(int projectId) {
		Project project = entityManager.find(Project.class, projectId);
		if (project!=null) {
			project.setEmployees(null);
			List<Employee> employees = project.getEmployees();
			for (Employee employee : employees) {
				employee.setProjects(null);
			}
			entityTransaction.begin();
			entityManager.remove(project);
			entityTransaction.commit();
		}
		return false;
	}
}
