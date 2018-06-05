package com.mediocean.prokabaddi.service;

import java.util.Date;
import java.util.List;

import com.mediocean.prokabaddi.models.Match;
import com.mediocean.prokabaddi.models.Team;

public interface ITeamService {
  
	public List<Team> fetchParticipateTeamList(String[] teamIdArray); 
	public boolean validatDuplicateTeamId(String[] teamIdArray) ;
		
	
}
