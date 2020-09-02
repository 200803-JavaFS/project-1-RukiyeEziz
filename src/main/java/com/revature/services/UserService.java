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
		log.info("In UserService looking for user by user name. " + username);
		return userDao.findByUsername(username);
	}
	
	public Users findUserByUserId(int userid) {
		log.info("In UserService looking user by user id. " + userid);
		return userDao.findUserById(userid);
	}
	
	public boolean addUser(Users user) {
		log.info("In UserService adding the user. " + user);
		return userDao.addUser(user);
	}
	
	public boolean updateUser(Users user) {
		log.info("In UserService updating the user. " + user);
		return userDao.updateUser(user);
	}
	
	
}
