package com.springEMS.team;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springEMS.employee.Employee;
import com.springEMS.employee.EmployeeService;
import com.springEMS.handler.CustomException;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private TeamMemberRepository teamMemRepo;
	
	@Autowired
	private EmployeeService empServ;
	
	//Team Services
	
	public ArrayList<Team> getTeamList(){
		ArrayList<Team> list = new ArrayList<Team>();
		teamRepo.findAll().forEach(list::add);
		return list;
	}
	
	public void addTeam(Team team) throws CustomException {
		if(this.validateTeam(team)) {
			teamRepo.save(team);
		}
		return;
	}
	
	public void updateTeam(Team team, String team_id) {
		teamRepo.save(team);
		return;
	}
	
	public void deleteTeam(String team_id) throws CustomException {
		Team team = teamRepo.findOne(team_id);
		if(team == null) {
			throw new CustomException("Entity Not Found");
		}
		else {
			this.deleteTeamMemberUsingTeamId(team);
			teamRepo.delete(team_id);
		}	
		return;
	}
	
	public Team getTeamDetails(String team_id) throws CustomException {
		
		Team team = teamRepo.findOne(team_id);
		if(team != null){
			return teamRepo.findOne(team_id);
		}
		else {
			throw new CustomException("Entity Not Found");
		}
			
	}
	
	
	//Team Member Services
	public boolean addTeamMember(TeamMember teamMem) throws CustomException {
		if(this.validateTeamMember(teamMem)) {
			teamMemRepo.save(teamMem);
		}
		return true;
	}
	
	@Transactional
	public boolean deleteTeamMemberUsingTeamId(Team team) throws CustomException {
		if(this.validateTeam(team)) {
			teamMemRepo.deleteTeamMemberUsingTeamId(team.getTeam_id());
		}
		return true;
	}
	@Transactional
	public boolean deleteTeamMemberUsingEmployeeId(Employee emp) throws CustomException {
		if(empServ.validateEmployee(emp)) {
			teamMemRepo.deleteTeamMemberUsingEmployeeId(emp.getEmp_id());
		}
		return true;
	}
	
	
	public ArrayList<Team> getTeamListForEmployee(String emp_id) throws CustomException{
		ArrayList<TeamMember> teamMemberList = new ArrayList<TeamMember>();
		teamMemberList = teamMemRepo.getTeamListForEmployee(emp_id);
		
		ArrayList<Team> list = new ArrayList<Team>();
		
		for(int i=0;i<teamMemberList.size();i++) {
			list.add(this.getTeamDetails(teamMemberList.get(i).getTeam_id()));
		}
		
		return list;
	}
	
	
	public ArrayList<Employee> getEmployeeListForTeam(String team_id) throws Exception{
		ArrayList<TeamMember> teamMemberList = new ArrayList<TeamMember>();
		teamMemberList = teamMemRepo.getEmployeeListForTeam(team_id);
		
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		for(int i=0;i<teamMemberList.size();i++) {
			list.add(empServ.getEmployeeSpecs(teamMemberList.get(i).getEmp_id()));
		}
		
		return list;
	}
	
	
	
	
	
	
	
	
	//Validation Services
	public boolean validateTeam(Team team) throws CustomException {
		
		if(team.getTeam_name() == null || team.getTeam_name().equals("") || team.getTeam_name().length()>20) {
			throw new CustomException("Invalid Field Team Name");
		}
		if(team.getTeam_id() == null || team.getTeam_id().equals("") || team.getTeam_id().length()>20) {
			throw new CustomException("Invalid Field Team Id");
		}
		return true;
	}
	
	public boolean validateTeamMember(TeamMember teamMem) throws CustomException{
		if(teamMem.getTeam_id() == null || teamMem.getTeam_id().equals("") || teamMem.getTeam_id().length()>20) {
			throw new CustomException("Invalid Field Team Name");
		}
		if(teamMem.getEmp_id() == null || teamMem.getEmp_id().equals("") || teamMem.getEmp_id().length()>20) {
			throw new CustomException("Invalid Field Team Id");
		}
		return true;
		
	}
}
