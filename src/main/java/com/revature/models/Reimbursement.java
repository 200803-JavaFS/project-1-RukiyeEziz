package com.revature.models;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="ers_reimbursement")
public class Reimbursement implements Serializable{
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
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reimb_id")
	private int reimbId;
	
	@Column(name="reimb_amount", nullable=false, columnDefinition = "NUMERIC(10,2)")
	private double reimbAmount;
	
	@Column(name="reimb_submitted", nullable=false)
	private Timestamp reimbSubmitted;							// Timestamp reimbSubmitted;
	
	@Column(name="reimb_resolved")
	private Timestamp reimbResolved;							// Timestamp reimbResolved;
	
	@Column(name="reimb_description")
	private String reimbDescription;
	
	@Column(name="reimb_receipt")
	private Byte[] reimbReceipt;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="ers_users_id_fk_auth", nullable=false)
	@JsonBackReference												// no need for json
	private Users reimbAuthor; // it lionks to Users
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="ers_users_id_fk_reslvr")
	@JsonBackReference
	private Users reimbResolver; // it links to Users
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_status_id_fk", nullable=false)
	private ReimbStatus reimbStatusFK; // it links to Reimbursement
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_type_id_fk", nullable=false)			// no need for json
	private ReimbType reimbTypeFK;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbId, double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbDescription, Byte[] reimbReceipt, Users reimbAuthor, Users reimbResolver, ReimbStatus reimbStatusFK,
			ReimbType reimbTypeFK) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbReceipt = reimbReceipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusFK = reimbStatusFK;
		this.reimbTypeFK = reimbTypeFK;
	}

	public Reimbursement(double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved, String reimbDescription,
			Byte[] reimbReceipt, Users reimbAuthor, Users reimbResolver, ReimbStatus reimbStatusFK, ReimbType reimbTypeFK) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbReceipt = reimbReceipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusFK = reimbStatusFK;
		this.reimbTypeFK = reimbTypeFK;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(double reimbAmount) {
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

	public Byte[] getReimbReceipt() {
		return reimbReceipt;
	}

	public void setReimbReceipt(Byte[] reimbReceipt) {
		this.reimbReceipt = reimbReceipt;
	}

	public Users getReimbAuthor() {
		return reimbAuthor;
	}

	public void setReimbAuthor(Users reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}

	public Users getReimbResolver() {
		return reimbResolver;
	}

	public void setReimbResolver(Users reimbResolver) {
		this.reimbResolver = reimbResolver;
	}

	public ReimbStatus getReimbStatusFK() {
		return reimbStatusFK;
	}

	public void setReimbStatusFK(ReimbStatus reimbStatusFK) {
		this.reimbStatusFK = reimbStatusFK;
	}

	public ReimbType getReimbTypeFK() {
		return reimbTypeFK;
	}

	public void setReimbTypeFK(ReimbType reimbTypeFK) {
		this.reimbTypeFK = reimbTypeFK;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(reimbAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reimbAuthor == null) ? 0 : reimbAuthor.hashCode());
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbReceipt == null) ? 0 : reimbReceipt.hashCode());
		result = prime * result + ((reimbResolved == null) ? 0 : reimbResolved.hashCode());
		result = prime * result + ((reimbResolver == null) ? 0 : reimbResolver.hashCode());
		result = prime * result + ((reimbStatusFK == null) ? 0 : reimbStatusFK.hashCode());
		result = prime * result + ((reimbSubmitted == null) ? 0 : reimbSubmitted.hashCode());
		result = prime * result + ((reimbTypeFK == null) ? 0 : reimbTypeFK.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(reimbAmount) != Double.doubleToLongBits(other.reimbAmount))
			return false;
		if (reimbAuthor == null) {
			if (other.reimbAuthor != null)
				return false;
		} else if (!reimbAuthor.equals(other.reimbAuthor))
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
		if (reimbResolver == null) {
			if (other.reimbResolver != null)
				return false;
		} else if (!reimbResolver.equals(other.reimbResolver))
			return false;
		if (reimbStatusFK == null) {
			if (other.reimbStatusFK != null)
				return false;
		} else if (!reimbStatusFK.equals(other.reimbStatusFK))
			return false;
		if (reimbSubmitted == null) {
			if (other.reimbSubmitted != null)
				return false;
		} else if (!reimbSubmitted.equals(other.reimbSubmitted))
			return false;
		if (reimbTypeFK == null) {
			if (other.reimbTypeFK != null)
				return false;
		} else if (!reimbTypeFK.equals(other.reimbTypeFK))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription
				+ ", reimbReceipt=" + reimbReceipt + "]";
				
				//+ ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver
				//+ ", reimbStatusFK=" + reimbStatusFK + ", reimbTypeFK=" + reimbTypeFK + "]";
	}

	
	
}

