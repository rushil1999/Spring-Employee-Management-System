package com.springEMS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springEMS.model.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, String> {
	

}
