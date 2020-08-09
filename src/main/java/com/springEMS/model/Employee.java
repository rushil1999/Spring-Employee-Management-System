package com.springEMS.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@Column(name = "empId")
	private String emp_id;
	
	private String f_name;
	private String l_name;
	@Column(name = "dept_id")
	private String dept_id;
	private String job_title;
	private String dob;
	private String phone_no;

	//Constructors
	public Employee(){
	}


	public Employee(String emp_id, String f_name, String l_name, String dept_id, String job_title, String dob,
			String phone_no) {
		super();
		this.emp_id = emp_id;
		this.f_name = f_name;
		this.l_name = l_name;
		this.dept_id = dept_id;
		this.job_title = job_title;
		this.dob = dob;
		this.phone_no = phone_no;
	}


	public String getEmp_id() {
		return emp_id;
	}


	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}


	public String getF_name() {
		return f_name;
	}


	public void setF_name(String f_name) {
		this.f_name = f_name;
	}


	public String getL_name() {
		return l_name;
	}


	public void setL_name(String l_name) {
		this.l_name = l_name;
	}


	public String getDept_id() {
		return dept_id;
	}


	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}


	public String getJob_title() {
		return job_title;
	}


	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getPhone_no() {
		return phone_no;
	}


	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	
	
	
	
	
	

}
