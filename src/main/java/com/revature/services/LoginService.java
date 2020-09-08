
package com.revature.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.LoginDTO;
import com.revature.models.Users;

// AKA UserService = LoginService
public class LoginService {
	
	private static final Logger log = LogManager.getLogger(LoginService.class);
	
	private static UserService userService = new UserService();
	
	public boolean login(LoginDTO loginDto) {
		
		String hashedPWFromClient = getHashSHA1(loginDto.password);
		
		Users user = userService.findUserByNamePW(loginDto.username, hashedPWFromClient);		
				
		log.info("Login Service login" + user.getUserName() + user.getPassword());

		if(loginDto.username.equals(user.getUserName()) && hashedPWFromClient.equals(user.getPassword())) {
			
			log.info("LoginService authentication successed. User Logged in.");
			return true;
		}
		
		log.error("LoginService authentication failed. User loggede in ");
		return false;
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

