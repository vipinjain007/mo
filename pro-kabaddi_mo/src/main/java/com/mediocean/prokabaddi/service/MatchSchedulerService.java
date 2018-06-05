package com.mediocean.prokabaddi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediocean.prokabaddi.dao.IMatchSchedulerDao;
import com.mediocean.prokabaddi.models.Match;
import com.mediocean.prokabaddi.models.Team;

@Service
public class MatchSchedulerService implements IMatchSchedulerService {

@Autowired
IMatchSchedulerDao matchSchedulerDao;	
	
	public List<Match> generateMatchSchedule(List<Team> teamList, Date startDate){
		return matchSchedulerDao.generateMatchSchedule(teamList, startDate);
	}
	
  
}
