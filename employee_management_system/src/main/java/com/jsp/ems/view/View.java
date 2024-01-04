package com.jsp.ems.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jsp.ems.controller.Controller;
import com.jsp.ems.model.Department;
import com.jsp.ems.model.Employee;
import com.jsp.ems.model.Project;

public class View {
	static Controller controller = new Controller();
	static Scanner myInput = new Scanner(System.in);

	public static void main(String[] args) {
		do {
			System.out.println("Select perticular operation to perform");
			System.out.println(
					"1.add employee\n2.view employee\n3.update Employee\n4.Remove Employee\n5.Add department\n6.View Department\n7.Add Project\n8.View Project\n0.EXITED");
			System.out.print("Enter perticular Operation to perform : ");
			int UserInput = myInput.nextInt();
			myInput.nextLine();
			switch (UserInput) {
			case 0:
				myInput.close();
				System.out.println("---------------------Thank You-------------------------");
				System.exit(0);
				break;

			case 1:
				System.out.println("Adding Employee Details...");
				System.out.print("How many Employee you want to add : ");
				int countEmployee = myInput.nextInt();
				myInput.nextLine();
				ArrayList<Employee> employees = new ArrayList();
				for (int i = 1; i <= countEmployee; i++) {
					System.out.println("-----------------------------------");
					Employee employee = new Employee();
					System.out.print("Enter employee Name :");
					employee.setName(myInput.nextLine());
					System.out.print("Enter employee position");
					employee.setPosition(myInput.nextLine());
					System.out.print("Enter employee salary");
					employee.setSalary(myInput.nextDouble());
					myInput.nextLine();
					employees.add(employee);
				}
				if (controller.addEmployee(employees)) {
					System.out.println("Employee added......");
					System.out.println("-----------------------------------");
				} else {
					System.out.println("Enter valid details...");
					System.out.println("-----------------------------------");
				}

				break;

			case 2:
				System.out.print("Enter employee id to view details : ");
				int employeeIdToFind = myInput.nextInt();
				myInput.nextLine();
				Employee employee = controller.ReadEmployee(employeeIdToFind);
				if (employee != null) {
					System.out.println("Employee id : " + employee.getId());
					System.out.println("Employee name : " + employee.getName());
					System.out.println("Employee position : " + employee.getPosition());
					System.out.println("Employee salary : " + employee.getSalary());
					Department department = employee.getDepartment();
					if (department != null) {
						System.out.println("Employee department details....");
						System.out.println("Department id : " + department.getId());
						System.out.println("Department name : " + department.getName());
					} else {
						System.out.println("This employee does not assign in any department");
					}
					List<Project> projects = employee.getProjects();
					if (projects != null) {
						System.out.println("Employee project details....");
						for (Project project : projects) {
							System.out.println("Project id : " + project.getId());
							System.out.println("Project name : " + project.getName());
							System.out.println("Project Description : " + project.getDescription());
						}
					} else {
						System.out.println("This employee does not assign in any project");
					}
					System.out.println("-----------------------------------");
				} else {
					System.out.println("given employee id does not exist.....");
					System.out.println("-----------------------------------");
				}
				break;

			case 3:
				System.out.print("Enter employee id to update : ");
				int empId = myInput.nextInt();
				myInput.nextLine();
				Employee employee5 = controller.ReadEmployee(empId);
				if (employee5 != null) {
					System.out.println("type 1 for assign department or type 2 for assign project");
					int selectUpdate = myInput.nextInt();
					myInput.nextLine();
					switch (selectUpdate) {
					case 1:
						List<Department> allDepartment = controller.fetchAllDepartment();
						for (Department department2 : allDepartment) {
							System.out.println("Department id : " + department2.getId());
							System.out.println("Department name : " + department2.getName());
							System.out.println();
						}
						System.out.print("Enter department id : ");
						int deptIdToFind = myInput.nextInt();
						myInput.nextLine();
						Department department2 = controller.ReadDepartment(deptIdToFind);
						boolean employee3 = controller.updateEmployeeDepartment(empId, department2);
						if (employee3) {
							System.out.println("Department is assign to given employee");
						} else {
							System.out.println("Department does not exist");
						}
						break;

					case 2:
						List<Project> allProject = controller.fetchAllProject();
						for (Project project2 : allProject) {
							System.out.println("Project id : " + project2.getId());
							System.out.println("Project name : " + project2.getName());
							System.out.println("Project description : " + project2.getDescription());
						}
						System.out.print("Enter project id : ");
						int projectIdToFind = myInput.nextInt();
						myInput.nextLine();
						ArrayList<Project> project1 = new ArrayList<Project>();

						Project project2 = controller.ReadProject(projectIdToFind);
						project1.add(project2);
						boolean employee4 = controller.updateEmployeeProject(empId, project1);
						if (employee4) {
							System.out.println("Project is assign to given employee");
						} else {
							System.out.println("Project does not exist");
						}
						break;
					default:
						System.out.println("Select perticular operation");
						break;
					}
				} else {
					System.out.println("employee id does not exist");
				}

				break;

			case 4:
				System.out.println("Enter Employee id to delete");
				int removeEmp = myInput.nextInt();
				myInput.nextLine();
				Employee employee3 = controller.ReadEmployee(removeEmp);
				if (employee3 != null) {
					boolean employee4 = controller.removeEmployee(removeEmp);
					if (employee4) {
						System.out.println("Employee removed");
					} else {
						System.out.println("Employee not removed");
					}
				} else {
					System.out.println("Employee does not exist");
				}
				break;

			case 5:
				System.out.println("Adding Department Details...");
				Department department = new Department();
				System.out.print("Enter department name : ");
				department.setName(myInput.nextLine());
				if (controller.addDepartment(department)) {
					System.out.println("Department added....");
				} else {
					System.out.println("Department is alredy exist or enter valid details");
				}
				break;

			case 6:
				System.out.print("Enter department id to find : ");
				int deptId = myInput.nextInt();
				myInput.nextLine();
				Department readDepartment = controller.ReadDepartment(deptId);
				if (readDepartment != null) {
					System.out.println("Department id : " + readDepartment.getId());
					System.out.println("Department name : " + readDepartment.getName());
					List<Employee> employees2 = readDepartment.getEmployees();
					if (employees2 != null) {
						for (Employee employee2 : employees2) {
							System.out.println("Employee id : " + employee2.getId());
							System.out.println("Employee name : " + employee2.getName());
							System.out.println("Employee position : " + employee2.getPosition());
							System.out.println("Employee salary : " + employee2.getSalary());
						}
					} else {
						System.out.println("Department is empty, because this is new department");
					}
				} else {
					System.out.println("Department id does not exist..");
				}
				break;

			case 7:
				System.out.println("Adding Project Details...");
				Project project = new Project();
				System.out.print("Enter project name : ");
				project.setName(myInput.nextLine());
				System.out.print("Enter project description : ");
				project.setDescription(myInput.nextLine());
				if (controller.addProject(project)) {
					System.out.println("Project added......");
				} else {
					System.out.println("Project is alredy exist or enter valid details");
				}
				break;

			case 8:
				System.out.print("Enter project id to find : ");
				int projectid = myInput.nextInt();
				myInput.nextLine();
				Project readProject = controller.ReadProject(projectid);
				if (readProject != null) {
					System.out.println("Project id : " + readProject.getId());
					System.out.println("Project name : " + readProject.getName());
					System.out.println("Project description : " + readProject.getDescription());
					List<Employee> employees2 = readProject.getEmployees();
					if (employees2 != null) {
						for (Employee employee2 : employees2) {
							System.out.println("Employee id : " + employee2.getId());
							System.out.println("Employee name : " + employee2.getName());
							System.out.println("Employee position : " + employee2.getPosition());
							System.out.println("Employee salary : " + employee2.getSalary());
						}
					} else {
						System.out.println("This project not assigned to any employees");
					}
				} else {
					System.out.println("Given project id does not exist, please enter valid id");
				}
				break;

			case 9:
				System.out.println("Fetch all the department");
				List<Department> allDepartment = controller.fetchAllDepartment();
				for (Department department2 : allDepartment) {
					System.out.println("Department id : " + department2.getId());
					System.out.println("Department name : " + department2.getName());
					System.out.println();
				}
				break;

			case 10:
				System.out.println("Fetch all the Employee");
				List<Employee> allEmployee = controller.fetchAllEmployee();
				for (Employee employee2 : allEmployee) {
					System.out.println("Employee id : " + employee2.getId());
					System.out.println("Employee name : " + employee2.getName());
					System.out.println("Employee position : " + employee2.getPosition());
					System.out.println("Employee salary : " + employee2.getSalary());
					System.out.println();
				}
				break;

			case 11:
				System.out.println("Fetch all the project");
				List<Project> allProject = controller.fetchAllProject();
				for (Project project2 : allProject) {
					System.out.println("Project id : " + project2.getId());
					System.out.println("Project name : " + project2.getName());
					System.out.println("Project description : " + project2.getDescription());
				}
				break;

			case 12:
				System.out.println("Enter Department id to update : ");
				int DeptId = myInput.nextInt();
				myInput.nextLine();
				Department department1 = controller.ReadDepartment(DeptId);
				if (department1 != null) {
					System.out.println("Enter department name : ");
					String deptName = myInput.nextLine();
					boolean departmentDetails = controller.updateDepartmentDetails(DeptId, deptName);
					if (departmentDetails) {
						System.out.println("Department details updated");
					} else {
						System.out.println("Department details not updated");
					}
				} else {
					System.out.println("Given department id does not exist");
				}
				break;

			case 13:
				System.out.println("Enter project id to update : ");
				int projectId = myInput.nextInt();
				myInput.nextLine();
				Project project2 = controller.ReadProject(projectId);
				if (project2 != null) {
					System.out.println("Enter project name to update : ");
					String projectName = myInput.nextLine();
					System.out.println("Enter project description : ");
					String projectDescription = myInput.nextLine();
					if (controller.updateProjectDetails(projectId, projectName, projectDescription)) {
						System.out.println("Project details updated");
					} else {
						System.out.println("project details not updated");
					}
				} else {
					System.out.println("Project id does not exist");
				}
				break;

			case 14:
				System.out.println("Remove Employee Details through id");
				int empIdToRemove = myInput.nextInt();
				Employee employee2 = controller.ReadEmployee(empIdToRemove);
				if (employee2 != null) {
					if (controller.removeEmployee(empIdToRemove)) {
						System.out.println("Employee removed....");
					} else {
						System.out.println("Employee does not removed.");
					}
				} else {
					System.out.println("Employee id does not exist");
				}
				break;
				
			case 15:
				System.out.println("Remove project Details through id");
				int projectIdToRemove = myInput.nextInt();
				Project project3 = controller.ReadProject(projectIdToRemove);
				if (project3 != null) {
					if (controller.removeProject(projectIdToRemove)) {
						System.out.println("Project removed....");
					} else {
						System.out.println("Project does not removed.");
					}
				} else {
					System.out.println("Project id does not exist");
				}
				break;
				
			default:
				System.out.println("Please select proper option to perform");
				break;
			}
		} while (true);
	}
}
