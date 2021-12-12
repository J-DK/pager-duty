package com.acko.pager.service;

import com.acko.pager.entity.Developer;
import com.acko.pager.entity.Team;
import com.acko.pager.model.AlertResponse;
import com.acko.pager.model.TeamRequest;
import com.acko.pager.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepo teamRepo;


    @Autowired
    NotificationService notificationService;


    private boolean doesTeamExist(String teamName) {
        return teamRepo.findByName(teamName).isPresent();
    }

    private boolean doesTeamExist(int teamId) {
        return teamRepo.findById(teamId).isPresent();
    }

    public void addTeam(TeamRequest request) {

        if (!doesTeamExist(request.getName())) {
            Team team = new Team();

            team.setName(request.getName());
            List<Developer> developers = new ArrayList<>();

            request.getDevelopers().forEach(developerRequest -> {
                Developer developer = new Developer();
                developer.setName(developerRequest.getName());
                developer.setPhone(developerRequest.getPhone_number());
                developers.add(developer);
            });

            team.setDevelopers(developers);
            teamRepo.save(team);
        } else {
            throw new RuntimeException("A team already exists with the given team name");
        }

    }

    public Team getTeamById(int teamId) {
        return teamRepo.findById(teamId).get();
    }

    private int getRandomNumber(int size) {
        int min = 0;
        int max = size - 1;
        return  (int) ((Math.random() * (max - min)) + min);
    }

    public AlertResponse receiveAlert(int teamId) {

        if (doesTeamExist(teamId)) {

            String message = "Something is failing!!";

            Team team = teamRepo.findById(teamId).get();
            int randomDeveloperId = getRandomNumber(team.getDevelopers().size());
            Developer randomDeveloper = team.getDevelopers().get(randomDeveloperId);

            String alertMessage = notificationService.notify(randomDeveloper.getPhone(), message);

            return AlertResponse.builder()
                    .name(randomDeveloper.getName())
                    .phone(randomDeveloper.getPhone())
                    .messageSent(message)
                    .alertResponseMessage(alertMessage)
                    .build();
        } else {
            throw new RuntimeException("No team exist with the given team id");
        }
    }
}
