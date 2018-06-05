package com.mediocean.prokabaddi;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.mediocean.prokabaddi.models.Match;

import lombok.Data;

@Data
public class ResponseObject {
	private HttpStatus status;
	private String message;
	private String debugMessage;
	List<Match> matchList=null;
       

}
