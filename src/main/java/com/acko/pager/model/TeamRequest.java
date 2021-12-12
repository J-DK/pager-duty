package com.acko.pager.model;

import lombok.Data;

import java.util.List;

@Data
public class TeamRequest {

    String name;
    List<DeveloperRequest> developers;
}
