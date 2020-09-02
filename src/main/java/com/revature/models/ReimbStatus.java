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

@Entity
@Table(name="ers_reimbursement_status")
public class ReimbStatus implements Serializable{
	private static final long serialVersionUID = 1L;	
	//	reimb_status_id 	SERIAL PRIMARY KEY,
	//	reimb_status 		VARCHAR(10) NOT NULL
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reimb_status_id")
	private int reimbStatusId;
	
	@Column(name="reimb_status", nullable=false)
	private String reimbStatus;		// Pending, Approved, Denied
	
	@OneToMany(mappedBy="reimbStatusFK", fetch=FetchType.EAGER)
	private List<Reimbursement> reimbList;	// it links to Reimbursement
	
	
	
	public ReimbStatus() {
		super();
	}

	public ReimbStatus(int reimbStatusId, String reimbStatus, List<Reimbursement> reimbList) {
		super();
		this.reimbStatusId = reimbStatusId;
		this.reimbStatus = reimbStatus;
		this.reimbList = reimbList;
	}

	public ReimbStatus(String reimbStatus, List<Reimbursement> reimbList) {
		super();
		this.reimbStatus = reimbStatus;
		this.reimbList = reimbList;
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public List<Reimbursement> getReimbList() {
		return reimbList;
	}

	public void setReimbList(List<Reimbursement> reimbList) {
		this.reimbList = reimbList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reimbList == null) ? 0 : reimbList.hashCode());
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + reimbStatusId;
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
		ReimbStatus other = (ReimbStatus) obj;
		if (reimbList == null) {
			if (other.reimbList != null)
				return false;
		} else if (!reimbList.equals(other.reimbList))
			return false;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbStatusId != other.reimbStatusId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbStatus [reimbStatusId=" + reimbStatusId + ", reimbStatus=" + reimbStatus + ", reimbList="
				+ reimbList + "]";
	}
	
	
	

}

//public ReimbStatus(int reimbStatusId, String reimbStatus) {
//	super();
//	this.reimbStatusId = reimbStatusId;
//	this.reimbStatus = reimbStatus;
//}
//
//public int getReimbStatusId() {
//	return reimbStatusId;
//}
//
//public void setReimbStatusId(int reimbStatusId) {
//	this.reimbStatusId = reimbStatusId;
//}
//
//public String getReimbStatus() {
//	return reimbStatus;
//}
//
//public void setReimbStatus(String reimbStatus) {
//	this.reimbStatus = reimbStatus;
//}
//
//@Override
//public int hashCode() {
//	final int prime = 31;
//	int result = 1;
//	result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
//	result = prime * result + reimbStatusId;
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
//	ReimbStatus other = (ReimbStatus) obj;
//	if (reimbStatus == null) {
//		if (other.reimbStatus != null)
//			return false;
//	} else if (!reimbStatus.equals(other.reimbStatus))
//		return false;
//	if (reimbStatusId != other.reimbStatusId)
//		return false;
//	return true;
//}
//
//@Override
//public String toString() {
//	return "ReimbursementStatus [reimbStatusId=" + reimbStatusId + ", reimbStatus=" + reimbStatus + "]";
//}

