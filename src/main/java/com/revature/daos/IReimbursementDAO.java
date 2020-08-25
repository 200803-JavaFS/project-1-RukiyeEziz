package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface IReimbursementDAO {
	
	public List<Reimbursement> findAllReimb();
	
	public Reimbursement findByReimbId(int reimbid);
		
	public boolean addReimbursement(Reimbursement reimb);
	
	public boolean updateReimbursement(Reimbursement reimb);

	public List<Reimbursement> findReimbursementByStatus(String status);
	
	public List<Reimbursement> findReimbursementByUserId(int userid);

}
