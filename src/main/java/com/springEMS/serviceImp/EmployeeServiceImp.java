package com.springEMS.employee;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springEMS.department.DepartmentService;
import com.springEMS.handler.CustomException;
import com.springEMS.team.TeamService;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private TeamService teamServ;
	
	@Autowired
	private DepartmentService deptServ;
	
	public ArrayList<Employee> getEmployeeList(){
		ArrayList<Employee> list = new ArrayList<Employee>();
		empRepo.findAll().forEach(list::add);
		return list;
		
	}
	
	public Employee getEmployeeSpecs(String id) throws Exception {
		if(!empRepo.existsById(id)) {
			throw new CustomException("Entity Not Found");
		}
		else {
			Employee emp = empRepo.findById(id).get();
			return emp;
		}
	}
	
	public boolean addEmployee(Employee emp) throws CustomException{
		if(this.validateEmployee(emp)) {
			
			if(this.checkIfEmployeeExists(emp.getEmp_id())){
				throw new CustomException("Entity Already Exists");
			}
			else {
				if(deptServ.departmentExists(emp.getDept_id())) {
					empRepo.save(emp);
					return true;
				}
				else {
					throw new CustomException("Department Does Not Exist");
				}
				
			}
		}	
		else {
			return false;
		}	
		
	}

	public void updateEmployee(String id, Employee emp) {
		empRepo.save(emp);
		return ;
		
	}
	
	public void deleteEmployee(String id) throws CustomException {
		if(!empRepo.existsById(id)) {
			
			throw new CustomException("Entity Not Found");
		}
		else {
			Employee emp = empRepo.findById(id).get();
			teamServ.deleteTeamMemberUsingEmployeeId(emp);
			empRepo.deleteById(id);
		}	
	}
	
	public ArrayList<Employee> getEmployeeListForDepartment(String dept_id){
		ArrayList<Employee> list = new ArrayList<Employee>();
		list = empRepo.getEmployeelistForDepartment(dept_id);
		return list;
		
	}
	
	
	public boolean checkIfEmployeeExists(String id) {
		return empRepo.existsById(id);
	}

	public boolean validateEmployee(Employee emp) throws CustomException {
		if(emp.getEmp_id() == null || emp.getEmp_id().equals("") || emp.getEmp_id().length()>20) {
			throw new CustomException("Invalid Field Employee ID");
		}
		
		if(emp.getF_name() == null || emp.getF_name().equals("") || emp.getF_name().length()>20) {
			throw new CustomException("Invalid Field First Name");
		}
		if(emp.getL_name() == null || emp.getL_name().equals("") || emp.getL_name().length()>20) {
			throw new CustomException("Invalid Field Last Name");
		}
		if(emp.getDob() == null || emp.getDob().equals("") || emp.getDob().length()>10) {
			throw new CustomException("Invalid Field DOB");
		}
		if(emp.getJob_title() == null || emp.getJob_title().equals("") || emp.getJob_title().length()>20) {
			throw new CustomException("Invalid Field Job Title");
		}
		if(emp.getPhone_no() == null || emp.getPhone_no().equals("") || emp.getPhone_no().length()>13 || !emp.getPhone_no().matches("[0-9]+")) {
			throw new CustomException("Invalid Field Phone Number");
		}
		return true;
	}
	
}
