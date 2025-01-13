package com.test.tuma.entity;

import java.util.List;

public class LeagueDetail {
    private Area area;
    private int id;
    private String name;
    private String code;
    private String type;
    private String emblem;
    private CurrentSeason currentSeason;
    private List<Season> seasons;
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public CurrentSeason getCurrentSeason() {
		return currentSeason;
	}
	public void setCurrentSeason(CurrentSeason currentSeason) {
		this.currentSeason = currentSeason;
	}
	public List<Season> getSeasons() {
		return seasons;
	}
	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

   
    
}
