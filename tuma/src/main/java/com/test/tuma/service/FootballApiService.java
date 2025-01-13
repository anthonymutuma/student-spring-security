package com.test.tuma.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.tuma.entity.CompetitionResponse;
import com.test.tuma.entity.LeagueDetail;

@Service
public class FootballApiService {

	private static final String API_URL = "https://api.football-data.org/v4/competitions";
	
	private static final String BASE_URL = "http://api.football-data.org/v4";
    private final RestTemplate restTemplate;

    public FootballApiService() {
        this.restTemplate = new RestTemplate();
    }

    public CompetitionResponse getCompetitions() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(API_URL, CompetitionResponse.class);
    }
    
    public LeagueDetail getCompetitionDetails(String leagueCode) {
        String url = BASE_URL + "/competitions/" + leagueCode;
        return restTemplate.getForObject(url, LeagueDetail.class);
    }
}
