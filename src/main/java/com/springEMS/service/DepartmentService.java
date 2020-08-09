package com.springEMS.service;

import java.util.ArrayList;

import com.springEMS.handler.CustomException;
import com.springEMS.model.Department;
import com.springEMS.model.Employee;

public interface DepartmentService {

	ArrayList<Employee> getEmployeeListForDepartment(String dept_id) throws CustomException;

	boolean addDepartment(Department dept) throws CustomException;

	boolean departmentExists(String dept_id) throws CustomException;

}
