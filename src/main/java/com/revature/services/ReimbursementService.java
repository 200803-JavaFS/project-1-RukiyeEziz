package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbursementDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.models.Users;


public class ReimbursementService {

	private static final Logger log = LogManager.getLogger(UserRoleService.class);
	
	private static IReimbursementDAO reimbDao = new ReimbursementDAO();
	
	public List<Reimbursement> findAllReimb(){
		
		log.info("ReimbursementService trying to find all reimb.");
		return reimbDao.findAllReimb();
	}
	
	public Reimbursement findByReimbId(int reimbid){
		
		log.info("ReimbursementService trying to find reimb by reimb id. " + reimbid);
		return reimbDao.findByReimbId(reimbid);
	}
		
	public boolean addReimbursement(Reimbursement reimb) {
		log.info("ReimbursementService trying to add reimb. " + reimb);
		return reimbDao.addReimbursement(reimb);
	}
	
	public boolean updateReimbursement(Reimbursement reimb) {
		
		log.info("ReimbursementService trying to update reimb. " + reimb);
		return reimbDao.updateReimbursement(reimb);
	}

	public List<Reimbursement> findReimbursementByStatus(ReimbStatus reimbStatus){
		
		log.info("ReimbursementService trying to find reimb by status. " + reimbStatus);
		return reimbDao.findReimbursementByStatus(reimbStatus);
	}
	
	public List<Reimbursement> findReimbursementByUserId(Users user){
		
		log.info("ReimbursementService trying to find reimb by user id. " + user);
		return reimbDao.findReimbursementByUserId(user);
	}
	
	
	
}
