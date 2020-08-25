package com.revature.models;

import java.io.Serializable;

public class Users implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//	ers_users_id 		SERIAL PRIMARY KEY,
	//	ers_username 		VARCHAR(50) NOT NULL UNIQUE,
	//	ers_password 		VARCHAR(50) NOT NULL,
	//	user_first_name 	VARCHAR(100) NOT NULL,
	//	user_last_name 		VARCHAR(100) NOT NULL,
	//	user_email 			VARCHAR(150) NOT NULL UNIQUE,
	//	user_role_id_fk 	INTEGER NOT NULL REFERENCES ers_user_roles(ers_user_role_id)
	
	private int usersId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int userRolesFK; // this FK and it links to UserRoles   UserRoles
	
	public Users() {
		super();
	}

	public Users(int usersId, String userName, String password, String firstName, String lastName, String email,
			int userRolesFK) {
		super();
		this.usersId = usersId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRolesFK = userRolesFK;
	}

	public Users(String userName, String password, String firstName, String lastName, String email,
			int userRolesFK) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRolesFK = userRolesFK;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserRolesFK() {
		return userRolesFK;
	}

	public void setUserRolesFK(int userRolesFK) {
		this.userRolesFK = userRolesFK;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + userRolesFK;
		result = prime * result + usersId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userRolesFK != other.userRolesFK)
			return false;
		if (usersId != other.usersId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Users [usersId=" + usersId + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", userRolesFK=" + userRolesFK + "]";
	}
	
	
}
