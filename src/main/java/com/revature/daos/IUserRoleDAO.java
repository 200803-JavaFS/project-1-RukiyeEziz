package com.revature.daos;

import com.revature.models.UserRoles;

public interface IUserRoleDAO {
	
	public UserRoles findUserRoleByUserId(int userid);

}
