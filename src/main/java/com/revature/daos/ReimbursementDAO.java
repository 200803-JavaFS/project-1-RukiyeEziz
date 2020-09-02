package com.revature.daos;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import com.revature.utils.ConnectionUtil;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.models.Users;
import com.revature.utils.HibernateUtil;


public class ReimbursementDAO implements IReimbursementDAO {

	private static final Logger log = LogManager.getLogger(ReimbursementDAO.class);

	@Override
	public List<Reimbursement> findAllReimb() {
		Session session = HibernateUtil.getSession();
		List<Reimbursement> list = session.createQuery("FROM Reimbursement").list();
		log.info("reimbursement DAO find all reimbursement. " + list);
		return list;
	}

	@Override
	public Reimbursement findByReimbId(int reimbid) {
		Session session = HibernateUtil.getSession();
		Reimbursement reimb = session.get(Reimbursement.class, reimbid);
		log.info("reimbursement DAO find reimb by reimb id. " + reimb);
		return reimb;
	}

	@Override
	public boolean addReimbursement(Reimbursement reimb) {
		Session session = HibernateUtil.getSession();
		
		try {
			session.save(reimb);
			log.info("reimbursement DAO added reimb. ");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateReimbursement(Reimbursement reimb) {
		Session session = HibernateUtil.getSession();
		
		try {
			session.merge(reimb);
			log.info("reimbursement DAO updated the reimb.");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Reimbursement> findReimbursementByStatus(ReimbStatus reimbStatus) {
		Session session = HibernateUtil.getSession();
		
		//????????????????
		List<Reimbursement> list = session.createQuery("FROM Reimbursement WHERE reimbStatusFK = " + reimbStatus, Reimbursement.class).list();
		log.info("reimbursement DAO find remib by status. " + list);
		return list;
	}

	@Override
	public List<Reimbursement> findReimbursementByUserId(Users user) {
		Session session = HibernateUtil.getSession();
		
		List<Reimbursement> list = session.createQuery("FROM Reimbursement WHERE reimbAuthor = "+ user.getUsersId(), Reimbursement.class).list();
		log.info("reimbursement DAO find reimb by user " + list);
		return list;
	}

	
	
	

}

//@Override
//public List<Reimbursement> findAllReimb() {
//	
//	try(Connection conn = ConnectionUtil.getConnection()) {
//		
//		String sql = "SELECT * FROM ers_reimbursement;";
//		Statement statement = conn.createStatement();
//		List<Reimbursement> list = new ArrayList<>();
//		ResultSet result = statement.executeQuery(sql);
//		
//		while(result.next()) {
//			Reimbursement reimb = new Reimbursement();
//			reimb.setReimbId(result.getInt("reimb_id")); 
//			reimb.setReimbAmount(result.getDouble("reimb_amount"));
//			reimb.setReimbSubmitted(result.getString("reimb_submitted"));
//			reimb.setReimbResolved(result.getString("reimb_resolved"));
//			reimb.setReimbDescription(result.getString("reimb_description"));
//			reimb.setReimbReceipt(result.getByte("reimb_receipt"));
//			reimb.setReimbAuthor(result.getInt("ers_users_id_fk_auth"));
//			reimb.setReimbResolver(result.getInt("ers_users_id_fk_reslvr"));
//			reimb.setReimbStatusId(result.getInt("reimb_status_id_fk"));
//			reimb.setReimbTypeId(result.getInt("reimb_type_id_fk"));
//			
//			list.add(reimb);
//		}
//		log.info("ReimbursementDAO successfully found all reimbs from DB.");
//		return list;
//	} catch(SQLException e) {
//		e.printStackTrace();
//	}		
//	log.info("ReimbursementDAO could not find all reimbs from DB.");
//	return null;
//}

//@Override
//public Reimbursement findByReimbId(int reimbid) {
//	
//	try (Connection conn = ConnectionUtil.getConnection()) {
//		
//		String sql = "SELECT * FROM ers_reimbursement WHERE reimb_id =" + reimbid + ";";
//		Statement statement = conn.createStatement();
//		ResultSet result = statement.executeQuery(sql);
//		
//		if (result.next()) {
//			Reimbursement reimb = new Reimbursement();
//			reimb.setReimbId(result.getInt("reimb_id")); 
//			reimb.setReimbAmount(result.getDouble("reimb_amount"));
//			reimb.setReimbSubmitted(result.getString("reimb_submitted"));
//			reimb.setReimbResolved(result.getString("reimb_resolved"));
//			reimb.setReimbDescription(result.getString("reimb_description"));
//			reimb.setReimbReceipt(result.getByte("reimb_receipt"));
//			reimb.setReimbAuthor(result.getInt("ers_users_id_fk_auth"));
//			reimb.setReimbResolver(result.getInt("ers_users_id_fk_reslvr"));
//			reimb.setReimbStatusId(result.getInt("reimb_status_id_fk"));
//			reimb.setReimbTypeId(result.getInt("reimb_type_id_fk"));
//			
//			log.info("ReimbursementDAO successfully found reimb by reimb id from DB." + reimbid);
//			return reimb;
//		} else {
//			return null;
//		}
//	} catch(SQLException e) {
//		e.printStackTrace();
//	}
//	log.info("ReimbursementDAO could not find the reimb by reimb id from DB." + reimbid);
//	return null;
//}

//@Override
//public List<Reimbursement> findReimbursementByUserId(int userid) {
//	
//	try (Connection conn = ConnectionUtil.getConnection()) {
//		
//		String sql = "SELECT * FROM ers_reimbursement WHERE ers_users_id_fk_auth =" + userid + ";";
//		Statement statement = conn.createStatement();
//		List<Reimbursement> list = new ArrayList<>();
//		ResultSet result = statement.executeQuery(sql);
//		
//		while(result.next()) {
//			Reimbursement reimb = new Reimbursement();
//			reimb.setReimbId(result.getInt("reimb_id")); 
//			reimb.setReimbAmount(result.getDouble("reimb_amount"));
//			reimb.setReimbSubmitted(result.getString("reimb_submitted"));
//			reimb.setReimbResolved(result.getString("reimb_resolved"));
//			reimb.setReimbDescription(result.getString("reimb_description"));
//			reimb.setReimbReceipt(result.getByte("reimb_receipt"));
//			reimb.setReimbAuthor(result.getInt("ers_users_id_fk_auth"));
//			reimb.setReimbResolver(result.getInt("ers_users_id_fk_reslvr"));
//			reimb.setReimbStatusId(result.getInt("reimb_status_id_fk"));
//			reimb.setReimbTypeId(result.getInt("reimb_type_id_fk"));
//			
//			list.add(reimb);
//		}
//		log.info("ReimbursementDAO successfully found reimbs by user id from DB.");
//		return list;
//	} catch(SQLException e) {
//		e.printStackTrace();
//	}		
//	log.info("ReimbursementDAO could not find reimbs by user id from DB.");
//	return null;
//}

//@Override
//public List<Reimbursement> findReimbursementByStatus(String status) {
//	
//	try (Connection conn = ConnectionUtil.getConnection()) {
//		
//		String sql = "SELECT * FROM ers_reimbursement " + 
//					"JOIN ers_reimbursement_status " + 
//					"ON ers_reimbursement.reimb_status_id_fk = ers_reimbursement_status.reimb_status_id " + 
//					"WHERE ers_reimbursement_status.reimb_status = " + status + ";";
//		
//		Statement statement = conn.createStatement();
//		List<Reimbursement> list = new ArrayList<>();
//		ResultSet result = statement.executeQuery(sql);
//		
//		while(result.next()) {
//			Reimbursement reimb = new Reimbursement();
//			reimb.setReimbId(result.getInt("reimb_id")); 
//			reimb.setReimbAmount(result.getDouble("reimb_amount"));
//			reimb.setReimbSubmitted(result.getString("reimb_submitted"));
//			reimb.setReimbResolved(result.getString("reimb_resolved"));
//			reimb.setReimbDescription(result.getString("reimb_description"));
//			reimb.setReimbReceipt(result.getByte("reimb_receipt"));
//			reimb.setReimbAuthor(result.getInt("ers_users_id_fk_auth"));
//			reimb.setReimbResolver(result.getInt("ers_users_id_fk_reslvr"));
//			reimb.setReimbStatusId(result.getInt("reimb_status_id_fk"));
//			reimb.setReimbTypeId(result.getInt("reimb_type_id_fk"));
//			
//			list.add(reimb);
//		}
//		log.info("ReimbursementDAO successfully found reimbs by status from DB.");
//		return list;
//	} catch(SQLException e) {
//		e.printStackTrace();
//	}		
//	log.info("ReimbursementDAO could not find reimbs by status from DB.");
//	return null;
//}	
	/* example status = 'Pending'
	1	666.66	2020-08-23 11:22:46		travel cost				2		3	2	3	Pending
	2	101.47	2020-08-23 12:02:26		certification cost		2		3	4	3	Pending
	3	222.11	2020-08-24 20:19:35		Food cost				3		3	3	3	Pending
	*/
//
//
//@Override
//public boolean addReimbursement(Reimbursement reimb) {
//	
//	try (Connection conn = ConnectionUtil.getConnection()) {
//		String sql = "INSERT INTO ers_reimbursement (reimb_amount , reimb_submitted , reimb_resolved , reimb_description , "
//				+ "reimb_receipt , ers_users_id_fk_auth , ers_users_id_fk_reslvr , reimb_status_id_fk , reimb_type_id_fk )"
//				+ "VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?);";
//		PreparedStatement statement = conn.prepareStatement(sql);
//		
//		int index = 0;
//		statement.setDouble(++index,reimb.getReimbAmount());
//		statement.setString(++index, reimb.getReimbSubmitted());
//		statement.setString(++index, reimb.getReimbResolved());
//		statement.setString(++index, reimb.getReimbDescription());
//		statement.setByte(++index,  reimb.getReimbReceipt());
//		statement.setInt(++index, reimb.getReimbAuthor());
//		statement.setInt(++index, reimb.getReimbResolver());
//		statement.setInt(++index, reimb.getReimbStatusId());
//		statement.setInt(++index, reimb.getReimbTypeId());
//
//		statement.execute();
//		log.info("ReimbursementDAO successfully inserted reimbursement info to DB.");
//		return true;
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	log.info("ReimbursementDAO could not insert reimbursement info to DB.");
//	return false;
//}

//@Override
//public boolean updateReimbursement(Reimbursement reimb) {
//	try (Connection conn = ConnectionUtil.getConnection()) {
//		String sql = "UPDATE ers_reimbursement "
//				+ "SET reimb_amount = ?, reimb_submitted = ?, "
//				+ "reimb_resolved = ?, reimb_description = ?, "
//				+ "reimb_receipt = ?, ers_users_id_fk_auth = ?, "
//				+ "ers_users_id_fk_reslvr = ?, reimb_status_id_fk = ?, "
//				+ "reimb_type_id_fk = ?, "
//				+ "WHERE reimb_id = ?;";
//				
//		PreparedStatement statement = conn.prepareStatement(sql);
//		
//		int index = 0;
//		statement.setDouble(++index,reimb.getReimbAmount());
//		statement.setString(++index, reimb.getReimbSubmitted());
//		statement.setString(++index, reimb.getReimbResolved());
//		statement.setString(++index, reimb.getReimbDescription());
//		statement.setByte(++index,  reimb.getReimbReceipt());
//		statement.setInt(++index, reimb.getReimbAuthor());
//		statement.setInt(++index, reimb.getReimbResolver());
//		statement.setInt(++index, reimb.getReimbStatusId());
//		statement.setInt(++index, reimb.getReimbTypeId());
//
//		statement.execute();
//		log.info("ReimbursementDAO successfully updated reimbursement info to DB.");
//		return true;
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	log.info("ReimbursementDAO could not update reimbursement info to DB.");
//	return false;
//}

