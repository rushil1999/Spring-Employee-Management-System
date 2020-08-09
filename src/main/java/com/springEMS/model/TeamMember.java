package com.springEMS.team;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.springEMS.employee.Employee;

@Entity
@Table(name = "team_member")
@IdClass(TeamMemberId.class)
public class TeamMember {
	
	@Id
	private String emp_id;
	
	@Id
	private String team_id;
	
//	@OneToOne
//	@JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
//	private Employee emp;
//	
//	@OneToOne
//	@JoinColumn(name = "team_id", referencedColumnName = "team_id")
//	private Team team;
	


	public TeamMember() {
		
	}
	
	public TeamMember(String emp_id, String team_id) {
		super();
		this.emp_id = emp_id;
		this.team_id = team_id;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

//	public Employee getEmp() {
//		return emp;
//	}
//
//	public void setEmp(Employee emp) {
//		this.emp = emp;
//	}

	
	

}
