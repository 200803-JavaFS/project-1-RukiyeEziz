package com.revature.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Reimbursment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//	reimb_id 				SERIAL PRIMARY KEY,
	//	reimb_amount 			INTEGER NOT NULL,
	//	reimb_submitted 		TIMESTAMP NOT NULL,
	//	reimb_resolved 			TIMESTAMP,
	//	reimb_description 		VARCHAR(250),
	//	reimb_receipt 			BYTEA,
	//	ers_users_id_fk_auth 	INTEGER NOT NULL REFERENCES ers_users(ers_users_id),
	//	ers_users_id_fk_reslvr 	INTEGER REFERENCES ers_users(ers_users_id),
	//	reimb_status_id_fk 		INTEGER NOT NULL REFERENCES ers_reimbursement_status(reimb_status_id),
	//	reimb_type_id_fk 		INTEGER NOT NULL REFERENCES ers_reimbursement_type(reimb_type_id)	
	
	private int reimbId;
	private int reimbAmount;
	private Timestamp reimbSubmitted;
	private Timestamp reimbResolved;
	private String reimbDescription;
	private Byte reimbReceipt;
	private Users users;
	private ReimbStatus reimbStatus;
	private ReimbType reimbType;
	
	public Reimbursment() {
		super();
	}

	public Reimbursment(int reimbId, int reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbDescription, Byte reimbReceipt, Users users, ReimbStatus reimbStatus, ReimbType reimbType) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbReceipt = reimbReceipt;
		this.users = users;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}

	public Reimbursment(int reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved, String reimbDescription,
			Byte reimbReceipt, Users users, ReimbStatus reimbStatus, ReimbType reimbType) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbReceipt = reimbReceipt;
		this.users = users;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public int getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(int reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public Timestamp getReimbSubmitted() {
		return reimbSubmitted;
	}

	public void setReimbSubmitted(Timestamp reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}

	public Timestamp getReimbResolved() {
		return reimbResolved;
	}

	public void setReimbResolved(Timestamp reimbResolved) {
		this.reimbResolved = reimbResolved;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public Byte getReimbReceipt() {
		return reimbReceipt;
	}

	public void setReimbReceipt(Byte reimbReceipt) {
		this.reimbReceipt = reimbReceipt;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public ReimbStatus getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(ReimbStatus reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public ReimbType getReimbType() {
		return reimbType;
	}

	public void setReimbType(ReimbType reimbType) {
		this.reimbType = reimbType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbAmount;
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbReceipt == null) ? 0 : reimbReceipt.hashCode());
		result = prime * result + ((reimbResolved == null) ? 0 : reimbResolved.hashCode());
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + ((reimbSubmitted == null) ? 0 : reimbSubmitted.hashCode());
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		Reimbursment other = (Reimbursment) obj;
		if (reimbAmount != other.reimbAmount)
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbReceipt == null) {
			if (other.reimbReceipt != null)
				return false;
		} else if (!reimbReceipt.equals(other.reimbReceipt))
			return false;
		if (reimbResolved == null) {
			if (other.reimbResolved != null)
				return false;
		} else if (!reimbResolved.equals(other.reimbResolved))
			return false;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbSubmitted == null) {
			if (other.reimbSubmitted != null)
				return false;
		} else if (!reimbSubmitted.equals(other.reimbSubmitted))
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursment [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription
				+ ", reimbReceipt=" + reimbReceipt + ", users=" + users + ", reimbStatus=" + reimbStatus
				+ ", reimbType=" + reimbType + "]";
	}
	
	
	
	
}
