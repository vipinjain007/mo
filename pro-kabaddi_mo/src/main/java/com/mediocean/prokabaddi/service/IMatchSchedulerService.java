package com.mediocean.prokabaddi.service;

import java.util.Date;
import java.util.List;

import com.mediocean.prokabaddi.models.Match;
import com.mediocean.prokabaddi.models.Team;

public interface IMatchSchedulerService {
	List<Match> generateMatchSchedule(List<Team> teamList, Date startDate);


}
