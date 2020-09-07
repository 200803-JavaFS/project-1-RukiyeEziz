package com.revature.daos;

import java.util.List;

import com.revature.models.ReimbStatus;

public interface IReimbStatusDAO {
	
	public ReimbStatus findReimbStatus(int statusid);
	
	public ReimbStatus findReimbStatus(String status);
	
	public List<ReimbStatus> findReimbStatusByUserId(int userid);

}
