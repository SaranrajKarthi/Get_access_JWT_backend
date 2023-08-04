package com.saran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saran.dto.ResponseDTO;
import com.saran.entity.User;
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

}
