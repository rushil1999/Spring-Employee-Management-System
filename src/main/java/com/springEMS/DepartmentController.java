package com.springEMS.department;

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

import com.springEMS.employee.Employee;
import com.springEMS.handler.CustomException;

@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService deptServ;

	@RequestMapping(method = RequestMethod.POST, value = "/department")
	@ResponseBody
	public ResponseEntity<Boolean> addDepartment(@RequestBody Department dept) throws CustomException{
		return new ResponseEntity<Boolean>(deptServ.addDepartment(dept), HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/department/{dept_id}")
	@ResponseBody
	public ResponseEntity<ArrayList<Employee>> getEmployeeListForDepartment(@PathVariable String dept_id) throws CustomException{
		ArrayList<Employee> list = new ArrayList<Employee>();
		list = deptServ.getEmployeeListForDepartment(dept_id);
		return new ResponseEntity<ArrayList<Employee>>(list,HttpStatus.OK);
	}
	
}
