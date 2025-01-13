package com.test.tuma.entity;

public class Competition {

	private int id;
    private Area area;
    private String name;
    private String code;
    private String type;
    private String emblem;
    private String plan;
    private CurrentSeason currentSeason;
    private int numberOfAvailableSeasons;
    private String lastUpdated;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmblem() {
		return emblem;
	}
	public void setEmblem(String emblem) {
		this.emblem = emblem;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public CurrentSeason getCurrentSeason() {
		return currentSeason;
	}
	public void setCurrentSeason(CurrentSeason currentSeason) {
		this.currentSeason = currentSeason;
	}
	public int getNumberOfAvailableSeasons() {
		return numberOfAvailableSeasons;
	}
	public void setNumberOfAvailableSeasons(int numberOfAvailableSeasons) {
		this.numberOfAvailableSeasons = numberOfAvailableSeasons;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
    
    
}
