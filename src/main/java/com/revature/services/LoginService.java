package com.revature.services;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.LoginDTO;
import com.revature.models.Users;

// AKA UserService = LoginService
public class LoginService {
	private static final Logger log = LogManager.getLogger(LoginService.class);
	private static UserService userService = new UserService();
	
	private static UserService us = new UserService();
	
	public boolean login(LoginDTO loginDto) {
		
		
		Users user = userService.findUserByNamePW(loginDto.username, loginDto.password);
		
		log.info("Login Service login" + user.getUserName() + user.getPassword());
		
		if(loginDto.username.equals(user.getUserName()) && loginDto.password.equals(user.getPassword())) {
			
			log.info("LoginService successfully processed. User Logged in.");
			return true;
		}
		
		log.info("LoginService failed the login processed.");
		return false;
	}

	

}
