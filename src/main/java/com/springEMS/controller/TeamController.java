package com.springEMS.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springEMS.handler.CustomException;
import com.springEMS.model.Employee;
import com.springEMS.model.Team;
import com.springEMS.model.TeamMember;
import com.springEMS.serviceImp.TeamServiceImp;

@RestController
@RequestMapping(value = "/team")
public class TeamController {
		
	@Autowired
	private TeamServiceImp teamServ;
	
	//Team Requests
	
	@RequestMapping(method = RequestMethod.GET, value = "/list/team")
	@ResponseBody
	public ResponseEntity<ArrayList<Team>> getTeamList(){
		ArrayList<Team> list = new ArrayList<Team>();
		list = teamServ.getTeamList();
		return new ResponseEntity<ArrayList<Team>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/save/team")
	@ResponseBody
	public ResponseEntity<Boolean> addTeam(@RequestBody Team team) throws CustomException {
		
		if(teamServ.validateTeam(team)) {
			teamServ.addTeam(team);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/save/team/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> updateTeam(@RequestBody Team team, @PathVariable String id) throws CustomException {
		if(teamServ.validateTeam(team)) {
			teamServ.addTeam(team);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/team/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> deleteTeam(@PathVariable String id) throws CustomException {
		teamServ.deleteTeam(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
	}
	
	//Team Member Requests
	
	@RequestMapping(method = RequestMethod.POST, value = "/save/teamMem")
	public ResponseEntity<Boolean> addTeamMember(@RequestBody TeamMember teamMem) throws CustomException {
		teamServ.addTeamMember(teamMem);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/list/teamMem/{id}")
	@ResponseBody
	public ResponseEntity<ArrayList<Team>> getTeamListForEmployee(@PathVariable String id) throws CustomException{
		
		ArrayList<Team> list = new ArrayList<Team>();
		list = teamServ.getTeamListForEmployee(id);
		return new ResponseEntity<ArrayList<Team>>(list, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list/emp/{team_id}")
	@ResponseBody
	public ResponseEntity<ArrayList<Employee>> getEmployeeForTeam(@PathVariable String team_id) throws Exception{
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		list = teamServ.getEmployeeListForTeam(team_id);
		return new ResponseEntity<ArrayList<Employee>>(list, HttpStatus.OK);
	}

}
