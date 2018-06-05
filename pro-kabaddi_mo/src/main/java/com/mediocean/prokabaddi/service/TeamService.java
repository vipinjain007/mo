package com.mediocean.prokabaddi.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediocean.prokabaddi.configration.TeamsDataRepository;
import com.mediocean.prokabaddi.dao.IMatchSchedulerDao;
import com.mediocean.prokabaddi.models.Match;
import com.mediocean.prokabaddi.models.Team;

@Service
public class TeamService implements ITeamService {
	
	@Autowired
	TeamsDataRepository teamsDataRepository;
	
	@Autowired
	IMatchSchedulerDao matchSchedulerDao;
	
  
	public List<Team> fetchParticipateTeamList(String[] teamIdArray) {
		List<Team> teams = new ArrayList<Team>();
		for (String teamId : teamIdArray) {
			Team team = teamsDataRepository.fetch(teamId);
			if (team != null) {
				teams.add(team);
				
			} else {
				throw new RuntimeException("Team Id :" + teamId
						+ " is not valid or record not found,Please pass all valid Team Id's to generate Matchs schedules");
			}
		}
		return teams;

	}
	
	public boolean validatDuplicateTeamId(String[] teamIdArray) {
		boolean isduplicate=false;
		List<Object> teamIds = Arrays.asList(teamIdArray);
		Set<Object> hSet = new HashSet<Object>(teamIds);
		if(teamIds.size()!=hSet.size()){
			isduplicate=true;
			throw new RuntimeException("Please select all unique team ids");
	
		}
		
		return isduplicate;
		

	}

	
	

}
