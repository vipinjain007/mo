package com.mediocean.prokabaddi.dao;

import java.util.Date;
import java.util.List;

import com.mediocean.prokabaddi.models.Match;
import com.mediocean.prokabaddi.models.Team;

public interface IMatchSchedulerDao {
	List<Match> generateMatchSchedule(List<Team> teamList, Date startDate);
}
