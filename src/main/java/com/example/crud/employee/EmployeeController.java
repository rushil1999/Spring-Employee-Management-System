package com.example.crud.employee;

import java.util.ArrayList;
import java.util.List;

import com.example.crud.handler.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/start")
    public String start() {
        return "Hello World";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/addEmployee", produces = "application/json")
    public ResponseEntity<?> addEmployee(@RequestBody Employee emp) {
        System.out
                .println("ID: " + emp.getEmp_id() + " Name: " + emp.getEmp_first_name() + " " + emp.getEmp_last_name());
        employeeService.addEmployee(emp);
        return new ResponseEntity<Object>(emp, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/employees", produces = "application/json")
    public ResponseEntity<?> fetchEmployees() {
        // System.out.println("Fetch Employees Called");
        List<Employee> list = new ArrayList<Employee>();
        list = employeeService.fetchAllEmployees();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/deleteEmployee", produces = "application/json")
    public ResponseEntity<?> deleteEmployee(@RequestBody Employee emp) throws CustomException {
        employeeService.deleteEmployee(emp);
        boolean operation = true;
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/employee/{id}", produces = "application/json")
    public ResponseEntity<?> fetchEmployee(@PathVariable(value="id") int id) {
        Employee emp = new Employee();
        emp = this.employeeService.fetchEmployee(String.valueOf(id));
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(method = RequestMethod.PUT, value = "/updateEmployee/{id}")
	@ResponseBody
	public ResponseEntity<?> updateEmployee(@RequestBody Employee emp, @PathVariable(value = "id") int id) {
        this.employeeService.updateEmployee(emp, String.valueOf(id));
        boolean operation = true;
        return new ResponseEntity<>(operation, HttpStatus.OK);
	}

}