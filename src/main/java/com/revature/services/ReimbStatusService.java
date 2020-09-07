package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbStatusDAO;
import com.revature.daos.ReimbStatusDAO;
import com.revature.models.ReimbStatus;

public class ReimbStatusService {
	
	private static final Logger log = LogManager.getLogger(UserRoleService.class);
	private static IReimbStatusDAO rsDao = new ReimbStatusDAO(); 

	public ReimbStatus findReimbStatus(String status) {
		
		log.info("Reimb status service by status " + status);
		return rsDao.findReimbStatus(status);
	}
	public ReimbStatus findReimbStatusById(int statusid) {
		
		log.info("Reimb status service by status " + statusid);
		return rsDao.findReimbStatus(statusid);
	}

}

