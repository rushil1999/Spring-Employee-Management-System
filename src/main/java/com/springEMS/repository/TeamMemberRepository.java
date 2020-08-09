package com.springEMS.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springEMS.model.TeamMember;
import com.springEMS.model.TeamMemberId;

@Repository
public interface TeamMemberRepository extends CrudRepository<TeamMember, TeamMemberId>{
	
	@Query(nativeQuery=true, value="select * from team_member where emp_id= ?1")
	public ArrayList<TeamMember> getTeamListForEmployee(@Param("emp_id") String emp_id);
	
	@Query(nativeQuery=true, value="select * from team_member where team_id= ?1")
	public ArrayList<TeamMember> getEmployeeListForTeam(@Param("team_id") String emp_id);
	

	@Modifying
	@Query(nativeQuery = true, value="delete from team_member where team_id= ?1")
	public void deleteTeamMemberUsingTeamId(String team_id);
	
	@Modifying
	@Query(nativeQuery = true, value="delete from team_member where emp_id = ?1")
	public void deleteTeamMemberUsingEmployeeId(String emp_id);
}
