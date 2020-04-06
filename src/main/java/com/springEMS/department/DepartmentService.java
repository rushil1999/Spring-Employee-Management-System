package com.springEMS.department;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springEMS.handler.CustomException;
import com.springEMS.employee.Employee;
import com.springEMS.employee.EmployeeService;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository deptRepo;
	
	@Autowired
	private EmployeeService empServ;
	
	public boolean departmentExists(String dept_id) throws CustomException{
		if(!deptRepo.existsById(dept_id)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean addDepartment(Department dept) throws CustomException {
		if(!this.departmentExists(dept.getDept_id())) {
			deptRepo.save(dept);
			return true;
		}
		else {
			throw new CustomException("Entity already Exists");
		}	
	}
	
	public ArrayList<Employee> getEmployeeListForDepartment(String dept_id) throws CustomException{
		
		ArrayList<Employee> list = new ArrayList<Employee>();
		if(this.departmentExists(dept_id)) {
			list = empServ.getEmployeeListForDepartment(dept_id);
			return list;
		}
		else {
			throw new CustomException("Entity Not Found");
		}
	}
}
