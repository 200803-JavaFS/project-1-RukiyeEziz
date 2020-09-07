package com.revature.daos;

import java.util.List;

import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.models.Users;

public interface IReimbursementDAO {
	
	public List<Reimbursement> findAllReimb();
	
	public Reimbursement findByReimbId(int reimbid);
		
	public boolean addReimbursement(Reimbursement reimb);
	
	public boolean updateReimbursement(Reimbursement reimb);

	public List<Reimbursement> findReimbursementByStatus(int statusid);
	
	public List<Reimbursement> findReimbursementByUserId(Users user);

}
