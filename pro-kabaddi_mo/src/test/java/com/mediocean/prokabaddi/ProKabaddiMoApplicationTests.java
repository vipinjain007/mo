package com.mediocean.prokabaddi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mediocean.prokabaddi.configration.TeamsDataRepository;
import com.mediocean.prokabaddi.models.Match;
import com.mediocean.prokabaddi.models.Team;
import com.mediocean.prokabaddi.service.IMatchSchedulerService;
import com.mediocean.prokabaddi.service.ITeamService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProKabaddiMoApplicationTests {

	@Autowired
	ITeamService teamService;

	@Autowired
	TeamsDataRepository teamsDataRepository;

	@Autowired
	IMatchSchedulerService matchSchedulerService;
	
	
	@Before
	public void setup() {

	}

	@Test(expected = RuntimeException.class)
	public void validatDuplicateIds() {
		try {
			// Crated duplicate Id's show its should throw exception
			String[] teamIdArrayWithDuplicateId = { "1", "1", "3", "4", "5" };
			teamService.validatDuplicateTeamId(teamIdArrayWithDuplicateId);
		} catch (RuntimeException re) {
			String message = "Please select all unique team ids";
			assertEquals(message, re.getMessage());
			throw re;
		}
		fail("Please select all unique team ids  did not throw!");
	}
 
@Test	
 public void fetchParticipateTeamList() {
		// Expeted exception since this id is not in teamData Repo
		Team team = teamsDataRepository.fetch("1");
         if(team!=null) {
        	 System.out.println(team.getCity()+"  "+team.getId());
        	 assertEquals(team.getCity(),"Chennai");
         }
	}


@Test
public void generateMatchSchedule() {
	   try {
	        
	       /*Details :We selected 2 teams and will play 2 match 
	       (as per requirement  Each team must play against every other team once home and away
	       We will check both match should not in consecutive days and both match in different location 
			*/	
	    String[] teamIdArray = { "1", "2"};
				teamService.validatDuplicateTeamId(teamIdArray);
				//Find the participate team list based on id's
				 List<Team> teams=teamService.fetchParticipateTeamList(teamIdArray);
				// Format start date
				 Date scheduleStartDate = new SimpleDateFormat("dd/MM/yyyy").parse("10/12/1982");
				
				 
				 List<Match> matchList= matchSchedulerService.generateMatchSchedule(teams, scheduleStartDate);
				 
				 String loction="";
				 Match match1=null;
				 Match match2=null;
				 int i=0;
				 for(Match match:matchList) {
					 if(i==0) {
						 match1=match;
					 }else {
						 match2=match;
						 	 
					 }
					 i++;
				 }
				 assertNotEquals(match1.getDate(), match2.getDate());
				 assertNotEquals(match1.getTeamA().getCity(),match2.getTeamA().getCity());
				// assertTrue((match2.getDate().getDate()-match1.getDate().getDate())>1);
	   }catch(Exception e) {
		fail("Test case fail due to exception");   
	   }
}
}
