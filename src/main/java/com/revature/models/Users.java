package com.revature.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ers_users")
public class Users implements Serializable{
	private static final long serialVersionUID = 1L;	
	//	ers_users_id 		SERIAL PRIMARY KEY,
	//	ers_username 		VARCHAR(50) NOT NULL UNIQUE,
	//	ers_password 		VARCHAR(50) NOT NULL,
	//	user_first_name 	VARCHAR(100) NOT NULL,
	//	user_last_name 		VARCHAR(100) NOT NULL,
	//	user_email 			VARCHAR(150) NOT NULL UNIQUE,
	//	user_role_id_fk 	INTEGER NOT NULL REFERENCES ers_user_roles(ers_user_role_id)
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ers_users_id")
	private int usersId;
	
	@Column(name="ers_username", unique=true, nullable=false)
	private String userName;
	
	@Column(name="ers_password", nullable=false)
	private String password;
	
	@Column(name="user_first_name", nullable=false)
	private String firstName;
	
	@Column(name="user_last_name", nullable=false)
	private String lastName;
	
	@Column(name="user_email", unique=true, nullable=false)
	private String email;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_role_id_fk", nullable=false)
	private UserRoles userRoleFK; // this FK and it links to UserRoles   UserList
	
	
	@OneToMany(mappedBy="reimbAuthor", fetch=FetchType.LAZY)
	private List<Reimbursement> reimbAuthorList; // it links to Reimbursement
	
	@OneToMany(mappedBy="reimbResolver", fetch=FetchType.EAGER)
	private List<Reimbursement> reimbRevolverList; // it links to Reimbursement
	
	
	
	public Users() {
		super();
	}

	public Users(int usersId, String userName, String password, String firstName, String lastName, String email,
			UserRoles userRoleFK, List<Reimbursement> reimbAuthorList, List<Reimbursement> reimbRevolverList) {
		super();
		this.usersId = usersId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleFK = userRoleFK;
		this.reimbAuthorList = reimbAuthorList;
		this.reimbRevolverList = reimbRevolverList;
	}


	public Users(String userName, String password, String firstName, String lastName, String email,
			UserRoles userRoleFK, List<Reimbursement> reimbAuthorList, List<Reimbursement> reimbRevolverList) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleFK = userRoleFK;
		this.reimbAuthorList = reimbAuthorList;
		this.reimbRevolverList = reimbRevolverList;
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


	public UserRoles getUserRoleFK() {
		return userRoleFK;
	}


	public void setUserRoleFK(UserRoles userRoleFK) {
		this.userRoleFK = userRoleFK;
	}


	public List<Reimbursement> getReimbAuthorList() {
		return reimbAuthorList;
	}


	public void setReimbAuthorList(List<Reimbursement> reimbAuthorList) {
		this.reimbAuthorList = reimbAuthorList;
	}


	public List<Reimbursement> getReimbRevolverList() {
		return reimbRevolverList;
	}


	public void setReimbRevolverList(List<Reimbursement> reimbRevolverList) {
		this.reimbRevolverList = reimbRevolverList;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((reimbAuthorList == null) ? 0 : reimbAuthorList.hashCode());
		result = prime * result + ((reimbRevolverList == null) ? 0 : reimbRevolverList.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userRoleFK == null) ? 0 : userRoleFK.hashCode());
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
		if (reimbAuthorList == null) {
			if (other.reimbAuthorList != null)
				return false;
		} else if (!reimbAuthorList.equals(other.reimbAuthorList))
			return false;
		if (reimbRevolverList == null) {
			if (other.reimbRevolverList != null)
				return false;
		} else if (!reimbRevolverList.equals(other.reimbRevolverList))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userRoleFK == null) {
			if (other.userRoleFK != null)
				return false;
		} else if (!userRoleFK.equals(other.userRoleFK))
			return false;
		if (usersId != other.usersId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Users [usersId=" + usersId + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

	
	

	
}


//public Users(int usersId, String userName, String password, String firstName, String lastName, String email,
//		int userRolesFK) {
//	super();
//	this.usersId = usersId;
//	this.userName = userName;
//	this.password = password;
//	this.firstName = firstName;
//	this.lastName = lastName;
//	this.email = email;
//	this.userRolesFK = userRolesFK;
//}
//
//public Users(String userName, String password, String firstName, String lastName, String email,
//		int userRolesFK) {
//	super();
//	this.userName = userName;
//	this.password = password;
//	this.firstName = firstName;
//	this.lastName = lastName;
//	this.email = email;
//	this.userRolesFK = userRolesFK;
//}
//
//public Users(int usersId, String userName, String password) {
//	super();
//	this.usersId = usersId;
//	this.userName = userName;
//	this.password = password;
//}
//
//public int getUsersId() {
//	return usersId;
//}
//
//public void setUsersId(int usersId) {
//	this.usersId = usersId;
//}
//
//public String getUserName() {
//	return userName;
//}
//
//public void setUserName(String userName) {
//	this.userName = userName;
//}
//
//public String getPassword() {
//	return password;
//}
//
//public void setPassword(String password) {
//	this.password = password;
//}
//
//public String getFirstName() {
//	return firstName;
//}
//
//public void setFirstName(String firstName) {
//	this.firstName = firstName;
//}
//
//public String getLastName() {
//	return lastName;
//}
//
//public void setLastName(String lastName) {
//	this.lastName = lastName;
//}
//
//public String getEmail() {
//	return email;
//}
//
//public void setEmail(String email) {
//	this.email = email;
//}
//
//public int getUserRolesFK() {
//	return userRolesFK;
//}
//
//public void setUserRolesFK(int userRolesFK) {
//	this.userRolesFK = userRolesFK;
//}
//
//
//@Override
//public int hashCode() {
//	final int prime = 31;
//	int result = 1;
//	result = prime * result + ((email == null) ? 0 : email.hashCode());
//	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
//	result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
//	result = prime * result + ((password == null) ? 0 : password.hashCode());
//	result = prime * result + ((userName == null) ? 0 : userName.hashCode());
//	result = prime * result + userRolesFK;
//	result = prime * result + usersId;
//	return result;
//}
//
//@Override
//public boolean equals(Object obj) {
//	if (this == obj)
//		return true;
//	if (obj == null)
//		return false;
//	if (getClass() != obj.getClass())
//		return false;
//	Users other = (Users) obj;
//	if (email == null) {
//		if (other.email != null)
//			return false;
//	} else if (!email.equals(other.email))
//		return false;
//	if (firstName == null) {
//		if (other.firstName != null)
//			return false;
//	} else if (!firstName.equals(other.firstName))
//		return false;
//	if (lastName == null) {
//		if (other.lastName != null)
//			return false;
//	} else if (!lastName.equals(other.lastName))
//		return false;
//	if (password == null) {
//		if (other.password != null)
//			return false;
//	} else if (!password.equals(other.password))
//		return false;
//	if (userName == null) {
//		if (other.userName != null)
//			return false;
//	} else if (!userName.equals(other.userName))
//		return false;
//	if (userRolesFK != other.userRolesFK)
//		return false;
//	if (usersId != other.usersId)
//		return false;
//	return true;
//}
//
//@Override
//public String toString() {
//	return "Users [usersId=" + usersId + ", userName=" + userName + ", password=" + password + ", firstName="
//			+ firstName + ", lastName=" + lastName + ", email=" + email + ", userRolesFK=" + userRolesFK + "]";
//}
//