package com.mediocean.prokabaddi.models;

import lombok.Data;

@Data
public class Team {
	private String id;
	private String name;
	private String description;
	private String city;

	public Team(String id) {
		this.id = id;
	}

	public Team() {
		// TODO Auto-generated constructor stub
	}

}
