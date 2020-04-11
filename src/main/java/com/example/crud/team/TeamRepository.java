package com.example.crud.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.team.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

}