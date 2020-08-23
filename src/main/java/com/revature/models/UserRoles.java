package com.revature.models;

import java.io.Serializable;

public class UserRoles implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//	ers_user_role_id 	SERIAL PRIMARY KEY,
	//	ers_user_role 		VARCHAR(10) NOT NULL
	
	private int userRoleId;
	private String userRole;		// employee, finance managers
	
	public UserRoles() {
		super();
	}

	public UserRoles(int userRoleId, String userRole) {
		super();
		this.userRoleId = userRoleId;
		this.userRole = userRole;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		result = prime * result + userRoleId;
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
		UserRoles other = (UserRoles) obj;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		if (userRoleId != other.userRoleId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRoles [userRoleId=" + userRoleId + ", userRole=" + userRole + "]";
	}
	
	
	
	
	
	
	
}
