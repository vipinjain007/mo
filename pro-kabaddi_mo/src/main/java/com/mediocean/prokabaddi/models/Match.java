package com.mediocean.prokabaddi.models;

import java.util.Date;

import lombok.Data;

@Data
public class Match {

	private final Team teamA;
	private final Team teamB;
	private String location;
	private Date date;

	public Match(Team teamA, Team teamB) {
		this.teamA = teamA;
		this.teamB = teamB;
	}

}
