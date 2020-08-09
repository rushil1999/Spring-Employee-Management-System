package com.springEMS.service;

import java.util.ArrayList;

import com.springEMS.handler.CustomException;
import com.springEMS.model.Employee;
import com.springEMS.model.Team;
import com.springEMS.model.TeamMember;

public interface TeamService {

	ArrayList<Team> getTeamList();

	void addTeam(Team team) throws CustomException;

	void updateTeam(Team team, String team_id);

	void deleteTeam(String team_id) throws CustomException;

	Team getTeamDetails(String team_id) throws CustomException;

	boolean addTeamMember(TeamMember teamMem) throws CustomException;

	boolean deleteTeamMemberUsingTeamId(Team team) throws CustomException;

	boolean deleteTeamMemberUsingEmployeeId(Employee emp) throws CustomException;

	ArrayList<Team> getTeamListForEmployee(String emp_id) throws CustomException;

	ArrayList<Employee> getEmployeeListForTeam(String team_id) throws Exception;

}
