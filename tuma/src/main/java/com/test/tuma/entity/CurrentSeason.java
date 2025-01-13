package com.test.tuma.entity;

public class CurrentSeason {
	
	private int id;
    private String startDate;
    private String endDate;
    private Integer currentMatchday;
    private Object winner;
    
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
	public Object getWinner() {
		return winner;
	}
	public void setWinner(Object winner) {
		this.winner = winner;
	}

}
