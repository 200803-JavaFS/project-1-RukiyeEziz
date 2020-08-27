package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IUserRoleDAO;
import com.revature.daos.UserRolesDAO;
import com.revature.models.UserRoles;

public class UserRoleService {
	
	private static final Logger log = LogManager.getLogger(UserRoleService.class);
	private static IUserRoleDAO userRoleDao = new UserRolesDAO();
	
	public UserRoles findUserRoleByUserId(int userid) {
		
		log.info("UserRoleService tying to found user role by user id from DB." + userid);
		return userRoleDao.findUserRoleByUserId(userid);
		
	}
	
	

}
