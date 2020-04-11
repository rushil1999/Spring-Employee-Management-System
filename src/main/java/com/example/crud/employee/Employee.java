package com.example.crud.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
	@Column(name = "emp_id")
    private String emp_id;

    @Column(name = "emp_first_name")
    private String emp_first_name;

    @Column(name = "emp_last_name")
    private String emp_last_name;

    public Employee() {

    }

    public Employee(String emp_id, String empFirstName, String empLastName) {
        this.emp_id = emp_id;
        this.emp_first_name = empFirstName;
        this.emp_last_name = empLastName;
    }

    public String getEmp_id(){
        return this.emp_id;
    }

    public String getEmp_first_name(){
        return this.emp_first_name;
    }

    public String getEmp_last_name(){
        return this.emp_last_name;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setEmp_first_name(String empFirstName) {
        this.emp_first_name = empFirstName;
    }

    public void setEmp_last_name(String empLastName) {
        this.emp_last_name = empLastName;
    }

}