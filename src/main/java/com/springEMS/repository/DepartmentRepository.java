package com.springEMS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springEMS.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, String> {
	

}
