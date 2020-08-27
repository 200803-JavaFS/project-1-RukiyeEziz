package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IUserDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Users;

public class UserService {
	
	private static final Logger log = LogManager.getLogger(UserService.class);
	private static IUserDAO userDao = new UserDAO();
	
	public List<Users> findAllUser() {
		log.info("UserService found all users.");
		return userDao.findAllUser();	
	}
	
	public Users findUserByNamePW(String username, String password) {
		
		log.info("In UserService looking for user name and pw ... " + username + password);
		return userDao.findUserByNamePW(username, password);
	}
	
	public Users findUserByUsername(String username) {
		log.info("In UserService find the user by user name.");
		return userDao.findByUsername(username);
	}
	
	
	
	
	
	
}
