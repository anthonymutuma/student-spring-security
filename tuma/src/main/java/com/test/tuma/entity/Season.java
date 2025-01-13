package com.test.tuma.entity;

import java.util.List;

public class Season {
    private int id;
    private String startDate;
    private String endDate;
    private Integer currentMatchday;
    private Winner winner;
    private List<String> stages;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getCurrentMatchday() {
		return currentMatchday;
	}
	public void setCurrentMatchday(Integer currentMatchday) {
		this.currentMatchday = currentMatchday;
	}
	public Winner getWinner() {
		return winner;
	}
	public void setWinner(Winner winner) {
		this.winner = winner;
	}
	public List<String> getStages() {
		return stages;
	}
	public void setStages(List<String> stages) {
		this.stages = stages;
	}

}