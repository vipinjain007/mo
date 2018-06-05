package com.mediocean.prokabaddi.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mediocean.prokabaddi.ResponseObject;
import com.mediocean.prokabaddi.configration.TeamsDataRepository;
import com.mediocean.prokabaddi.models.Match;
import com.mediocean.prokabaddi.models.Team;
import com.mediocean.prokabaddi.service.IMatchSchedulerService;
import com.mediocean.prokabaddi.service.ITeamService;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class MatchScheduleController {
	
	@Autowired
	ITeamService teamService; 
	
	@Autowired
	IMatchSchedulerService matchSchedulerService;
	
	@RequestMapping(value ="/hello", method= RequestMethod.GET)
	@ResponseBody
    public String sayHello() {
        return "hello";
    }
	
	@SuppressWarnings("unused")
	@RequestMapping(value ="/match/schedules", method= RequestMethod.GET)
	@ResponseBody
     ResponseEntity<ResponseObject> getSchedule(@RequestParam("teamIds") String teamIds,@RequestParam("startDate") String startDate) {
	 List<Match> matchList=new ArrayList<Match>();
	 ResponseObject rObject=new ResponseObject();	
	 try {
		 if(teamIds!=null && startDate!=null ) {
			// Split teamIds using comma
				String[] teamIdArray = teamIds.split(",");
				
			 // Format start date
			 Date scheduleStartDate = parseDate(startDate);
			 
			 teamService.validatDuplicateTeamId(teamIdArray); 
			 
			 //Find the participate team list based on id's
			 List<Team> teams=teamService.fetchParticipateTeamList(teamIdArray);
			 
			 
			 matchList= matchSchedulerService.generateMatchSchedule(teams, scheduleStartDate);
			 rObject.setStatus(HttpStatus.OK);
			 rObject.setMatchList(matchList);
			 
			 return new ResponseEntity<>(rObject, HttpStatus.OK);	 
			 
			 
					 
		 	 
		 }else {
			 rObject.setStatus(HttpStatus.BAD_REQUEST);
			 rObject.setMessage("teamIds , startDate Parameters are missing ");
			 return new ResponseEntity<>(rObject, HttpStatus.BAD_REQUEST);	 
			  
		}
	 }catch (Exception e) {
			  rObject.setStatus(HttpStatus.EXPECTATION_FAILED);
			  rObject.setMessage(e.getMessage());
			  rObject.setDebugMessage(e.getLocalizedMessage());
			return new ResponseEntity<>(rObject, HttpStatus.EXPECTATION_FAILED);
		}	
	 
	 
		
	}
	
	private Date parseDate(String startDate) {
		
		SimpleDateFormat  dateFormatter = new SimpleDateFormat("dd/MM/yyyy"); 
		try {
			return dateFormatter.parse(startDate);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
