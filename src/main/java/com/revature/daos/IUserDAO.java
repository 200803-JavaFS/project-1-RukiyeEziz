package com.revature.daos;

import java.util.List;

import com.revature.models.Users;

public interface IUserDAO {
	
	// login check ???????????
	public Users findUserByNamePW(String username, String password); 
	/////////////////////
	
	public List<Users> findAllUser();
	
	public Users findUserById(int userid);
	
	public Users findByUsername(String username);
	
	/* public List<String> findUserRoleByUserId(int userid); */
	
	public boolean addUser(Users user);  // do i need it???
	
	public boolean updateUser(Users user); // do i need it???
	
	
	
	
	
	

}
