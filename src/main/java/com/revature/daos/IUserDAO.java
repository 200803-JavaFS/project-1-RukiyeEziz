package com.revature.daos;

import java.util.List;

import com.revature.models.Users;

public interface IUserDAO {
	
	public List<Users> findAllUser();
	
	public Users findUserById(int userid);
	
	/* public Users findUserByIdPW(int userid, String password); */
	
	/* public List<String> findUserRoleByUserId(int userid); */
	
	public boolean addUser(Users user);  // do i need it???
	
	public boolean updateUser(Users user); // do i need it???
	
	
	
	
	
	

}
