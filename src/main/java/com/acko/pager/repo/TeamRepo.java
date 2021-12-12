package com.acko.pager.repo;

import com.acko.pager.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {

    Optional<Team> findByName(String teamName);

    Optional<Team> findById(int teamId);
}
