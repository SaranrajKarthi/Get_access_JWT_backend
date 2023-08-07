package com.saran.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

	private String statusCode;

	private String token;

	private String error;

	private String message;

	private List<String> links;

	private List<String> linksName;
    private Map<String, String> linksAndNames;


	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> list) {
		this.links = list;
	}

	public List<String> getLinksName() {
		return linksName;
	}

	public void setLinksName(List<String> linksName) {
		this.linksName = linksName;
	}

	public Map<String, String> getLinksAndNames() {
		return linksAndNames;
	}

	public void setLinksAndNames(Map<String, String> linksAndNames) {
		this.linksAndNames = linksAndNames;
	}
	
	
	

}
