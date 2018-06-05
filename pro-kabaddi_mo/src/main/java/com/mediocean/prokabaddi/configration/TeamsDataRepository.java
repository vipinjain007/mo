package com.mediocean.prokabaddi.configration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.mediocean.prokabaddi.models.Team;

/**
 * 
 * @author vipin
 * This Configration is take care to crate team list using json data from txt 
 */


@Configuration
public class TeamsDataRepository{

	List<Team> teamList=new ArrayList<Team>();
	private Map<String, Team> teamMap = new ConcurrentHashMap<String, Team>();
	
	private static String TEAM_FILE_NAME= "teamJson.txt";
	FileInputStream fileInputStream = null;
	URL resource = null;
	File file = null;

	@PostConstruct
    public void init() throws Exception {
		JsonReader jsonReader=null;
		JsonObject jsonObject=null;
	
		//Gets the json file under src/main/resources/json folder
		Resource resource = new ClassPathResource("json/teamsJson.txt");
		InputStream ipStream=resource.getInputStream(); 
		//File file = resource.getFile();
		//fileInputStream = new FileInputStream(file);
		
		 jsonReader = Json.createReader(ipStream);
			
		//get JsonObject from JsonReader
		 jsonObject = jsonReader.readObject();
	    
	      
		  JsonArray jsonArray = jsonObject.getJsonArray("team");
					
					for(int i=0;i<jsonArray.size();i++){
						Team team=new Team();
						team.setId(jsonArray.getJsonObject(i).getString("id"));
						team.setName(jsonArray.getJsonObject(i).getString("name"));
						team.setDescription(jsonArray.getJsonObject(i).getString("description"));
						team.setCity(jsonArray.getJsonObject(i).getString("city"));
						teamMap.put(team.getId(), team);
						teamList.add(team);
					}
				
				
		
		
	}
	
	public List<Team> getListFromJson(){
		return teamList;
	}
	
	
	public Team fetch(String id) {
		return teamMap.get(id);		
	}
	
	//Will Use when we configure Database instead of json
	public List<Team> getListFromDB(){
		return teamList;
	}
}
