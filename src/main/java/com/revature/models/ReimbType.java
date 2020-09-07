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
@Table(name="ers_reimbursement_type")
public class ReimbType implements Serializable{
	private static final long serialVersionUID = 1L;	
	//	reimb_type_id 	SERIAL PRIMARY KEY,
	//	reimb_type 		VARCHAR(10) NOT NULL
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reimb_type_id")
	private int reimbTypeId;
	
	
	@Column(name="reimb_type", nullable=false)
	private String reimbType;		// Lodging, Travel, Food, or Other
	
//	
//	// do I need this for pulling reimb list by reimb type???//////////////////
//	@OneToMany(mappedBy="reimbTypeFK", fetch=FetchType.EAGER)
//	private List<Reimbursement> reimbList;	// it links to Reimbursement

	
	
	public ReimbType() {
		super();
	}

	public ReimbType(int reimbTypeId, String reimbType) { //, List<Reimbursement> reimbList) {
		super();
		this.reimbTypeId = reimbTypeId;
		this.reimbType = reimbType;
		//this.reimbList = reimbList;
	}

	public ReimbType(String reimbType) { //, List<Reimbursement> reimbList) {
		super();
		this.reimbType = reimbType;
		//this.reimbList = reimbList;
	}
//	
//	public ReimbType(String reimbType) {
//		super();
//		this.reimbType = reimbType;
//	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	public String getReimbType() {
		return reimbType;
	}

	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}
//
//	public List<Reimbursement> getReimbList() {
//		return reimbList;
//	}
//
//	public void setReimbList(List<Reimbursement> reimbList) {
//		this.reimbList = reimbList;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((reimbList == null) ? 0 : reimbList.hashCode());
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + reimbTypeId;
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
		ReimbType other = (ReimbType) obj;
//		if (reimbList == null) {
//			if (other.reimbList != null)
//				return false;
//		} else if (!reimbList.equals(other.reimbList))
//			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		if (reimbTypeId != other.reimbTypeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbType [reimbTypeId=" + reimbTypeId + ", reimbType=" + reimbType + "]";  //", reimbList=" + reimbList + "]";
	}
	
}	

//	public ReimbType(int reimbTypeId, String reimbType) {
//		super();
//		this.reimbTypeId = reimbTypeId;
//		this.reimbType = reimbType;
//	}
//
//	public int getReimbTypeId() {
//		return reimbTypeId;
//	}
//
//	public void setReimbTypeId(int reimbTypeId) {
//		this.reimbTypeId = reimbTypeId;
//	}
//
//	public String getReimbType() {
//		return reimbType;
//	}
//
//	public void setReimbType(String reimbType) {
//		this.reimbType = reimbType;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
//		result = prime * result + reimbTypeId;
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ReimbType other = (ReimbType) obj;
//		if (reimbType == null) {
//			if (other.reimbType != null)
//				return false;
//		} else if (!reimbType.equals(other.reimbType))
//			return false;
//		if (reimbTypeId != other.reimbTypeId)
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "ReimbType [reimbTypeId=" + reimbTypeId + ", reimbType=" + reimbType + "]";
//	}
	

