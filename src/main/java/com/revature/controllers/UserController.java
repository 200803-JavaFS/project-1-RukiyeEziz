package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Users;
import com.revature.services.UserService;

public class UserController {
	
	private static final Logger log = LogManager.getLogger(UserController.class);
	
	private static UserService userService = new UserService();
	//private static UserRoleService userRoleService = new UserRoleService();
	//ObjectMapper provides functionality for reading and writing JSON
	
	private static ObjectMapper objectMapper = new ObjectMapper();	
	
	public void getUserByNamePw(HttpServletResponse res, String username, String password) throws ServletException, IOException{
		
		Users user = userService.findUserByNamePW(username, password);
		
		if(user == null) {
			
			res.setStatus(204);
		}
		else {
			res.setStatus(200);
			
			String json = objectMapper.writeValueAsString(user);
			System.out.println("User Controller get user by name pw json: " + json);
			log.info("user controller json" + json);
			res.getWriter().println(json);
			
		}
	}
	
	public void setUserRole(HttpServletRequest req, HttpServletResponse res, Users user) throws IOException {
		
		System.out.println("in method to sent user role as object");		
		if( user == null) {
			res.setStatus(204);
		}else {
			res.setStatus(200);
			String json = objectMapper.writeValueAsString(user);
			log.info("user controller json" + json);
			res.getWriter().println(json);
		}
		
	}

//	public void getUserRole(HttpServletRequest req, HttpServletResponse res, int urid) throws IOException {
//		
//		UserRoles userRole = userRoleService.findByUserRoleId(urid);
//		if(userRole == null) {
//			
//			res.setStatus(204);
//		}
//		else {
//			res.setStatus(200);
//			String json = objectMapper.writeValueAsString(userRole);
//			System.out.println("User Controller get user by name pw json: " + json);
//			res.getWriter().println(json);
//			
//		}
//	}
//	
	

	
	
	
}
