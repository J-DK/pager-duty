package com.acko.pager.repo;

import com.acko.pager.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepo extends JpaRepository<Developer, Integer> {



}


