package com.test.tuma.entity;

import java.util.List;
import java.util.Map;

public class CompetitionResponse {

	private int count;
    private Map<String, Object> filters;
    private List<Competition> competitions;
    
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Map<String, Object> getFilters() {
		return filters;
	}
	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
	}
	public List<Competition> getCompetitions() {
		return competitions;
	}
	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
	}
    
    
}
