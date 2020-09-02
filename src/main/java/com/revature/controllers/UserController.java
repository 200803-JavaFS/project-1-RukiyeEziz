package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.models.UserRoles;
import com.revature.models.Users;
import com.revature.services.UserRoleService;
import com.revature.services.UserService;

public class UserController {
	
	private static UserService userService = new UserService();
	private static UserRoleService userRoleService = new UserRoleService();
	//ObjectMapper provides functionality for reading and writing JSON
	private static ObjectMapper objectMapper = new ObjectMapper();	
	
	public void getUserByNamePw(HttpServletResponse res, String username, String password) throws IOException{
		
		Users user = userService.findUserByNamePW(username, password);
		
		if(user == null) {
			
			res.setStatus(204);
		}
		else {
			res.setStatus(200);
			
			String json = objectMapper.writeValueAsString(user);
			System.out.println("User Controller get user by name pw json: " + json);
			
			res.getWriter().println(json);
		}
	}
	
	public void getUserRole(HttpServletResponse res, int userid) {
		
		UserRoles userRole = userRoleService.findUserRoleByUserId(userid);
		if(userRole == null) {
			
			res.setStatus(204);
		}
		else {
			res.setStatus(200);
			
			String json = null;
			try {
				json = objectMapper.writeValueAsString(userRole);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("User Controller get user by name pw json: " + json);
			
			try {
				res.getWriter().println(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void getUserRole(HttpServletRequest req, HttpServletResponse res, Users user) throws IOException {
		//Hibernate.initialize(u.getUserRole());
		System.out.println("in method to sent user role as object");		
		if( user == null) {
			res.setStatus(204);
		}else {
			res.setStatus(200);
			String json = objectMapper.writeValueAsString(user);
			res.getWriter().println(json);
		}
		
	}


	
	
}
