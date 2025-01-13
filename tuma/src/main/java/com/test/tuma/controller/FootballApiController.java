package com.test.tuma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.tuma.entity.CompetitionResponse;
import com.test.tuma.entity.LeagueDetail;
import com.test.tuma.service.FootballApiService;

@RestController
public class FootballApiController {

	@Autowired
    private FootballApiService footballApiService;

    @GetMapping("/competitions")
    public CompetitionResponse fetchCompetitions() {
        return footballApiService.getCompetitions();
    }
    
    @GetMapping("/competitions/{code}")
    public LeagueDetail fetchCompetitionDetails(@PathVariable String code) {
        return footballApiService.getCompetitionDetails(code);
    }
}
