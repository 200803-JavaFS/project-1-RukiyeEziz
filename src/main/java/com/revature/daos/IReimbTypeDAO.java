package com.revature.daos;

import java.util.List;

import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;

public interface IReimbTypeDAO {
	
	public ReimbType findReimbType(int typeid);
	
	public ReimbType findReimbTypeByType(String type);
	
	public List<ReimbType> findReimbTypeByUserId(int userid);


}
