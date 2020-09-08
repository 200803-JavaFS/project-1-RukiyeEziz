package com.revature.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
			
		log.info("In UserService looking for user name and pw ... " + username + " " + password);	
		return userDao.findUserByNamePW(username, password);
		
	}
	
	public Users findUserByUserName(String username) {
		log.info("In UserService looking for user by user name. " + username);
		return userDao.findByUsername(username);
	}
	
	public Users findUserByUserId(int userid) {
		log.info("In UserService looking user by user id. " + userid);
		return userDao.findUserById(userid);
	}
	
	public boolean addUser(Users user) {
		log.info("In UserService adding the user. " + user);
		
		String hashedPW = getHashSHA1(user.getPassword());
		user.setPassword(hashedPW);
		return userDao.addUser(user);
	}
	
	public boolean updateUser(Users user) {
		log.info("In UserService updating the user. " + user);
		return userDao.updateUser(user);
	}
	
	//////////////////////////
	private static String getHashSHA1(String pw) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(pw.getBytes());
			byte byteData[] = md.digest();
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString(
			(byteData[i] & 0xff) + 0x100, 16).substring(1));
			
		}
			return sb.toString();
		}catch(NoSuchAlgorithmException ex) {
			System.out.println("something went wrong with pw hashing.");
			return null;
		}
	}
	//////////////////////////////

	
	
}


