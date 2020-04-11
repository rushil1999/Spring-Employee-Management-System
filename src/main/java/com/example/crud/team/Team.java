package com.example.crud.team;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team {
	
	@Id
	private String team_id;
	private String team_name;
	
	public Team() {
		
	}

	public Team(String team_id, String team_name) {
		super();
		this.team_id = team_id;
		this.team_name = team_name;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

}