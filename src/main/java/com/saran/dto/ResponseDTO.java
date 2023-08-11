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
    private List<Map<String, Object>> linksAndNames;


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

	public List<Map<String, Object>> getLinksAndNames() {
		return linksAndNames;
	}

	public void setLinksAndNames(List<Map<String, Object>> list) {
		this.linksAndNames = list;
	}
	
	
	

}
