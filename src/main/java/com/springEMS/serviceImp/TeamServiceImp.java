package com.springEMS.serviceImp;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springEMS.handler.CustomException;
import com.springEMS.model.Employee;
import com.springEMS.model.Team;
import com.springEMS.model.TeamMember;
import com.springEMS.repository.TeamMemberRepository;
import com.springEMS.repository.TeamRepository;
import com.springEMS.service.TeamService;

@Service
public class TeamServiceImp implements TeamService {

	@Autowired
	private TeamRepository teamRepo;

	@Autowired
	private TeamMemberRepository teamMemRepo;

	@Autowired
	private EmployeeServiceImp empServ;

	// Team Services

	@Override
	public ArrayList<Team> getTeamList() {
		ArrayList<Team> list = new ArrayList<Team>();
		teamRepo.findAll().forEach(list::add);
		return list;
	}

	@Override
	public void addTeam(Team team) throws CustomException {
		if (this.validateTeam(team)) {
			teamRepo.save(team);
		}
		return;
	}

	@Override
	public void updateTeam(Team team, String team_id) {
		teamRepo.save(team);
		return;
	}

	@Override
	public void deleteTeam(String team_id) throws CustomException {
		if (!teamRepo.existsById(team_id)) {
			throw new CustomException("Entity Not Found");
		} else {
			Team team = teamRepo.findById(team_id).get();
			this.deleteTeamMemberUsingTeamId(team);
			teamRepo.deleteById(team_id);
		}
	}

	@Override
	@Transactional
	public Team getTeamDetails(String team_id) throws CustomException {
		if (!teamRepo.existsById(team_id)) {
			throw new CustomException("Entity Not Found");

		} else {
			return teamRepo.findById(team_id).get();
		}

	}

	// Team Member Services
	@Override
	public boolean addTeamMember(TeamMember teamMem) throws CustomException {
		if (this.validateTeamMember(teamMem)) {
			if (!teamRepo.existsById(teamMem.getTeam_id()) || !empServ.checkIfEmployeeExists(teamMem.getEmp_id())) {
				throw new CustomException("Entity Not Found");
			} else {
				teamMemRepo.save(teamMem);
			}

		}
		return true;
	}

	@Transactional
	@Override
	public boolean deleteTeamMemberUsingTeamId(Team team) throws CustomException {
		if (this.validateTeam(team)) {
			teamMemRepo.deleteTeamMemberUsingTeamId(team.getTeam_id());
		}
		return true;
	}

	@Transactional
	@Override
	public boolean deleteTeamMemberUsingEmployeeId(Employee emp) throws CustomException {
		if (empServ.validateEmployee(emp)) {
			teamMemRepo.deleteTeamMemberUsingEmployeeId(emp.getEmp_id());
		}
		return true;
	}

	@Override
	public ArrayList<Team> getTeamListForEmployee(String emp_id) throws CustomException {
		ArrayList<TeamMember> teamMemberList = new ArrayList<TeamMember>();
		teamMemberList = teamMemRepo.getTeamListForEmployee(emp_id);

		ArrayList<Team> list = new ArrayList<Team>();

		for (int i = 0; i < teamMemberList.size(); i++) {
			list.add(this.getTeamDetails(teamMemberList.get(i).getTeam_id()));
		}

		return list;
	}

	@Override
	public ArrayList<Employee> getEmployeeListForTeam(String team_id) throws Exception {
		ArrayList<TeamMember> teamMemberList = new ArrayList<TeamMember>();
		teamMemberList = teamMemRepo.getEmployeeListForTeam(team_id);

		ArrayList<Employee> list = new ArrayList<Employee>();

		for (int i = 0; i < teamMemberList.size(); i++) {
			// System.out.println(teamMemberList.get(i).getEmp_id());
			list.add(empServ.getEmployeeSpecs(teamMemberList.get(i).getEmp_id()));
		}

		return list;
	}

	// Validation Services
	public boolean validateTeam(Team team) throws CustomException {

		if (team.getTeam_name() == null || team.getTeam_name().equals("") || team.getTeam_name().length() > 20) {
			throw new CustomException("Invalid Field Team Name");
		}
		if (team.getTeam_id() == null || team.getTeam_id().equals("") || team.getTeam_id().length() > 20) {
			throw new CustomException("Invalid Field Team Id");
		}
		return true;
	}

	public boolean validateTeamMember(TeamMember teamMem) throws CustomException {
		if (teamMem.getTeam_id() == null || teamMem.getTeam_id().equals("") || teamMem.getTeam_id().length() > 20) {
			throw new CustomException("Invalid Field Team Name");
		}
		if (teamMem.getEmp_id() == null || teamMem.getEmp_id().equals("") || teamMem.getEmp_id().length() > 20) {
			throw new CustomException("Invalid Field Team Id");
		}
		return true;

	}
}
