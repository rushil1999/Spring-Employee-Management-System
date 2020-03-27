package com.springEMS.team;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springEMS.employee.Employee;
import com.springEMS.handler.CustomException;

@RestController
public class TeamController {
		
	@Autowired
	private TeamService teamServ;
	
	//Team Requests
	
	@RequestMapping(method = RequestMethod.GET, value = "/teams")
	@ResponseBody
	public ArrayList<Team> getTeamList(){
		ArrayList<Team> list = new ArrayList<Team>();
		list = teamServ.getTeamList();
		return list;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/team")
	public boolean addTeam(@RequestBody Team team) throws CustomException {
		
		if(teamServ.validateTeam(team)) {
			teamServ.addTeam(team);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/team/{id}")
	public boolean updateTeam(@RequestBody Team team, @PathVariable String id) throws CustomException {
		if(teamServ.validateTeam(team)) {
			teamServ.addTeam(team);
			return true;
		}
		else {
			return false;
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/team/{id}")
	public void deleteTeam(@PathVariable String id) throws CustomException {
		teamServ.deleteTeam(id);
	}
	
	//Team Member Requests
	
	@RequestMapping(method = RequestMethod.POST, value = "/teamMemberAdd")
	public boolean addTeamMember(@RequestBody TeamMember teamMem) throws CustomException {
		teamServ.addTeamMember(teamMem);
		return true;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/teamMember/{id}")
	@ResponseBody
	public ArrayList<Team> getTeamListForEmployee(@PathVariable String id) throws CustomException{
		
		ArrayList<Team> list = new ArrayList<Team>();
		list = teamServ.getTeamListForEmployee(id);
		return list;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/team/{team_id}")
	@ResponseBody
	public ArrayList<Employee> getEmployeeForTeam(@PathVariable String team_id) throws Exception{
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		list = teamServ.getEmployeeListForTeam(team_id);
		return list;
	}

}
