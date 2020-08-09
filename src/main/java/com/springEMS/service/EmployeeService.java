package com.springEMS.service;

import java.util.ArrayList;

import com.springEMS.handler.CustomException;
import com.springEMS.model.Employee;

public interface EmployeeService {

	boolean checkIfEmployeeExists(String id);

	ArrayList<Employee> getEmployeeListForDepartment(String dept_id);

	void deleteEmployee(String id) throws CustomException;

	void updateEmployee(String id, Employee emp);

	boolean addEmployee(Employee emp) throws CustomException;

	Employee getEmployeeSpecs(String id) throws Exception;

	ArrayList<Employee> getEmployeeList();

}
