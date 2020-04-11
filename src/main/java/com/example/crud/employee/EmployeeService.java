package com.example.crud.employee;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crud.handler.*;
import com.example.crud.team.TeamService;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeamService teamService;

    public void addEmployee(Employee emp) {
        this.employeeRepository.save(emp);
        System.out.println("Employee Added!");
    }

	public ArrayList<Employee> fetchAllEmployees() {
		ArrayList<Employee> list = new ArrayList<Employee>();
		this.employeeRepository.findAll().forEach(list::add);
		return list;
    }
    
    public void deleteEmployee(Employee emp) throws CustomException {
        teamService.deleteTeamMemberUsingEmployeeId(emp);
        this.employeeRepository.delete(emp);
    }

    public Employee fetchEmployee(String emp_id) {
        Employee emp = new Employee();
        emp = this.employeeRepository.findById(emp_id).get();
        return emp;
    }

    public void updateEmployee(Employee emp, String emp_id) {
        this.employeeRepository.save(emp);
    }

    public boolean checkIfEmployeeExists(String id) {
		return employeeRepository.existsById(id);
	}

    public boolean validateEmployee(Employee emp) throws CustomException {
		if(emp.getEmp_id() == null || emp.getEmp_id().equals("") || emp.getEmp_id().length()>20) {
			throw new CustomException("Invalid Field Employee ID");
		}
		
		if(emp.getEmp_first_name() == null || emp.getEmp_first_name().equals("") || emp.getEmp_first_name().length()>50) {
			throw new CustomException("Invalid Field First Name");
		}
		if(emp.getEmp_last_name() == null || emp.getEmp_last_name().equals("") || emp.getEmp_last_name().length()>50) {
			throw new CustomException("Invalid Field Last Name");
		}
		return true;
	}


}