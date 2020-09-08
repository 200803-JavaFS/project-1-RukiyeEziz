package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbStatusDAO;
import com.revature.daos.IReimbTypeDAO;
import com.revature.daos.IReimbursementDAO;
import com.revature.daos.IUserDAO;
import com.revature.daos.ReimbStatusDAO;
import com.revature.daos.ReimbTypeDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.Users;


public class ReimbursementService {

	private static final Logger log = LogManager.getLogger(UserRoleService.class);
	
	private static IReimbursementDAO reimbDao = new ReimbursementDAO();
	private static IUserDAO uDao = new UserDAO();
	private static IReimbStatusDAO rsDao = new ReimbStatusDAO();
	private static IReimbTypeDAO rtDao = new ReimbTypeDAO();


	public List<Reimbursement> findAllReimb(){
		
		log.info("ReimbursementService trying to find all reimb.");
		return reimbDao.findAllReimb();
	}
	
	public Reimbursement findByReimbId(int reimbid){
		
		log.info("ReimbursementService trying to find reimb by reimb id. " + reimbid);
		return reimbDao.findByReimbId(reimbid);
	}
		
	public boolean addReimbursement(ReimbursementDTO rDto) {
		Reimbursement reimb;
		log.info("ReimbursementService trying to add reimb. " + rDto);
		Users auth = uDao.findUserById(rDto.reimbAuthor);
		Users reslvr = uDao.findUserById(rDto.reimbResolver);
		ReimbStatus status = rsDao.findReimbStatus(rDto.reimbStatusFK);
		ReimbType type = rtDao.findReimbType(rDto.reimbTypeFK);		
		
		reimb = new Reimbursement(rDto.reimbAmount, rDto.reimbSubmitted, rDto.reimbResolved, rDto.reimbDescription, rDto.reimbReceipt, auth, reslvr, status, type); 
		
		return reimbDao.addReimbursement(reimb);
	}
	
	public boolean updateReimbursement(Reimbursement reimb) {
		
		log.info("ReimbursementService trying to update reimb. " + reimb);
		return reimbDao.updateReimbursement(reimb);
	}

	public List<Reimbursement> findReimbursementByStatus(int statusid){
		
		log.info("ReimbursementService trying to find reimb by status. " + statusid);
		return reimbDao.findReimbursementByStatus(statusid);
	}
	
	public List<Reimbursement> findReimbursementByUserId(Users user){
		
		log.info("ReimbursementService trying to find reimb by user id. " + user);
		return reimbDao.findReimbursementByUserId(user);
	}
	
	
	

}
