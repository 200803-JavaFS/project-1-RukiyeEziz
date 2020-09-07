package com.revature.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="ers_user_roles")
public class UserRoles implements Serializable{
	private static final long serialVersionUID = 1L;
	//	ers_user_role_id 	SERIAL PRIMARY KEY,
	//	ers_user_role 		VARCHAR(10) NOT NULL
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ers_user_role_id")
	private int userRoleId;
	
	@Column(name="ers_user_role", nullable=false)
	private String userRole;		// employee, finance managers
	
	
//	@OneToMany(mappedBy="userRoleFK", fetch=FetchType.EAGER)
//	@JsonManagedReference
//	private List<Users> userList;	// it links to Users
//	
	
	public UserRoles() {
		super();
	}


	public UserRoles(int userRoleId, String userRole) { //, List<Users> userList) {
		super();
		this.userRoleId = userRoleId;
		this.userRole = userRole;
		//this.userList = userList;
	}


	public UserRoles(String userRole) { //, List<Users> userList) {
		super();
		this.userRole = userRole;
		//this.userList = userList;
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

//
//	public List<Users> getUserList() {
//		return userList;
//	}
//
//
//	public void setUserList(List<Users> userList) {
//		this.userList = userList;
//	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((userList == null) ? 0 : userList.hashCode());
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
//		if (userList == null) {
//			if (other.userList != null)
//				return false;
//		} else if (!userList.equals(other.userList))
//			return false;
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

//public UserRoles(int userRoleId, String userRole) {
//super();
//this.userRoleId = userRoleId;
//this.userRole = userRole;
//}
//
//public int getUserRoleId() {
//return userRoleId;
//}
//
//public void setUserRoleId(int userRoleId) {
//this.userRoleId = userRoleId;
//}
//
//public String getUserRole() {
//return userRole;
//}
//
//public void setUserRole(String userRole) {
//this.userRole = userRole;
//}
//
//@Override
//public int hashCode() {
//final int prime = 31;
//int result = 1;
//result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
//result = prime * result + userRoleId;
//return result;
//}
//
//@Override
//public boolean equals(Object obj) {
//if (this == obj)
//	return true;
//if (obj == null)
//	return false;
//if (getClass() != obj.getClass())
//	return false;
//UserRoles other = (UserRoles) obj;
//if (userRole == null) {
//	if (other.userRole != null)
//		return false;
//} else if (!userRole.equals(other.userRole))
//	return false;
//if (userRoleId != other.userRoleId)
//	return false;
//return true;
//}
//
//@Override
//public String toString() {
//return "UserRoles [userRoleId=" + userRoleId + ", userRole=" + userRole + "]";
//}
//
