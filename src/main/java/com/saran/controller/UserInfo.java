package com.saran.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saran.dto.ResponseDTO;
import com.saran.entity.User;
import com.saran.repo.UserRepo;
import com.saran.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api")
public class UserInfo {
	
	@Autowired
	UserService userService;

	@GetMapping("/my-info")
	public ResponseEntity<Object> getMyInfo(@RequestHeader("Authorization") String token){
		ResponseDTO response = new ResponseDTO();
		Claims claims = validateToken(token);
		
		if (claims == null) {
			response.setStatusCode("Access Denied - 401");
			response.setMessage("Unauthorized Access");
			return ResponseEntity.status(401).body(response);			
		}
		String email =  claims.getSubject();
		User user = userService.getUserByEmail(email);
		
		
		return ResponseEntity.ok(user);
	}

	private Claims validateToken(String token) {

		try {
			return Jwts.parser().setSigningKey("SECRET_KEY").parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}
	
//	@GetMapping("/get-links")
//	public ResponseDTO getLinks(@RequestBody User user) {
//		ResponseDTO response = new ResponseDTO();
//
//		List<String> links = getLinksOnly(user);
//
//		response.setStatusCode("Success - 200");
//		response.setMessage("Get Links Successfully");
//		response.setLinks(links);
//		return response;
//	}
	
	
//	@GetMapping("/get-links")
//	public ResponseDTO getLinks(@RequestHeader ("Authorization") String token) {
//		ResponseDTO response = new ResponseDTO();
//		Claims user = validateTokenGetLinks(token);
//		if (user == null) {
//			response.setStatusCode("Access Denied - 401");
//			response.setMessage("Unauthorized Access");
//			return response;
//		}
//
//		List<String> links = getLinksOnly(user);
//
//		response.setStatusCode("Success - 200");
//		response.setMessage("Get Links Successfully");
//		response.setLinks(links);
//		return response;
//		
//	}
//	private Claims validateTokenGetLinks(String token) {
//		try {
//			return Jwts.parser().setSigningKey("SECRET_KEY").parseClaimsJws(token).getBody();
//		} catch (Exception e) {
//			return null;
//		}
//}
//
//	private List<String> getLinksOnly(Claims user) {
//		String role = user.getSubject();
//		User users = UserRepo.getByRole(role);
//		List<String> links = new ArrayList<>();
//		if ("ADMIN".equals(user.getRole())) {
//			links.add("/menu/dashboard");
//			links.add("/menu/users");
//		} else {
//			links.add("/menu/dashboard");
//			links.add("/menu/profile");
//		}
//		return links;
//	}


}
