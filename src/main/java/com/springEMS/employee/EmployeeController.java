package com.springEMS.employee;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springEMS.handler.CustomException;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService empServ;
	
	@RequestMapping(method = RequestMethod.GET, value = "/employees")
	
	public ArrayList<Employee> getEmployeeList(){
		ArrayList<Employee> list = new ArrayList<Employee>();
		list = empServ.getEmployeeList();
		return list;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/employee/{id}")
	@ResponseBody
	public Employee getEmployeeSpecs(@PathVariable String id) throws Exception {
		Employee emp = empServ.getEmployeeSpecs(id);
		return emp;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/employee")
	@ResponseBody
	public boolean addEmployee(@RequestBody Employee emp) throws CustomException {
		empServ.addEmployee(emp);
		return true;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/employee/{id}")
	@ResponseBody
	public boolean updateEmployee(@RequestBody Employee emp, @PathVariable String id) throws CustomException {
		if(empServ.validateEmployee(emp)) {
			empServ.updateEmployee(id,emp);
			return true;
		}
		else {
			return false;
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/employee/{id}")
	@ResponseBody
	public void deleteEmployee(@PathVariable String id) throws CustomException {
		empServ.deleteEmployee(id);
	}

}
