package com.springEMS.model;

import java.io.Serializable;

public class TeamMemberId implements Serializable{
	
	private String emp_id;
	private String team_id;
	
	public TeamMemberId() {
		
	}
	
	public TeamMemberId(String emp_id, String team_id) {
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
	
	//Since Serializable is implemented
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamMemberId that = (TeamMemberId) o;

        if (!emp_id.equals(that.emp_id)) return false;
        return team_id.equals(that.team_id);
    }

    @Override
    public int hashCode() {
        int result = emp_id.hashCode();
        result = 31 * result + team_id.hashCode();
        return result;
    }
	

}
