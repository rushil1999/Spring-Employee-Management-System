package com.springEMS.employee;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String>{
	
	@Query(nativeQuery = true, value="select * from employee where dept_id = ?1")
	public ArrayList<Employee> getEmployeelistForDepartment(String dept_id);

}
