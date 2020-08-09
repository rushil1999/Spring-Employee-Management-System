package com.springEMS.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springEMS.handler.CustomException;
import com.springEMS.model.Employee;
import com.springEMS.serviceImp.EmployeeServiceImp;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImp empServ;
	
	@RequestMapping(method = RequestMethod.GET, value = "/list/emp")
	@ResponseBody
	public ResponseEntity<ArrayList<Employee>> getEmployeeList(){
		ArrayList<Employee> list = new ArrayList<Employee>();
		list = empServ.getEmployeeList();
		return new ResponseEntity<ArrayList<Employee>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/emp/{id}")
	@ResponseBody
	public ResponseEntity<Employee> getEmployeeSpecs(@PathVariable String id) throws Exception {
		Employee emp = empServ.getEmployeeSpecs(id);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/save/emp")
	@ResponseBody
	public ResponseEntity<Boolean> addEmployee(@RequestBody Employee emp) throws CustomException {
		return new ResponseEntity<Boolean>(empServ.addEmployee(emp), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/employee/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> updateEmployee(@RequestBody Employee emp, @PathVariable String id) throws CustomException {
		if(empServ.validateEmployee(emp)) {
			empServ.updateEmployee(id,emp);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/delete/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> deleteEmployee(@PathVariable String id) throws CustomException {
		empServ.deleteEmployee(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

}
