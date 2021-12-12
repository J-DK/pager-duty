package com.acko.pager.controller;

import com.acko.pager.entity.Team;
import com.acko.pager.model.AlertResponse;
import com.acko.pager.model.ErrorResponse;
import com.acko.pager.model.TeamRequest;
import com.acko.pager.service.TeamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Pager Duty APIs")
public class PagerController {

    @Autowired
    TeamService teamService;

    @GetMapping("/health")
    public String getHealthCheckStatus() {
        return "Health is Ok!";
    }


    @PostMapping("/team")
    public ResponseEntity addTeam(@RequestBody TeamRequest teamRequest) {

        try {
            teamService.addTeam(teamRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage(e.getLocalizedMessage())
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);

    }


    @GetMapping("/team/{teamId}")
    public Team getTeam(@PathVariable int teamId) {
        return teamService.getTeamById(teamId);
    }

    @GetMapping("/alert/{teamId}")
    public ResponseEntity receiveAlert(@PathVariable int teamId) {
        AlertResponse response;
        try {
             response = teamService.receiveAlert(teamId);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage(e.getLocalizedMessage())
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
