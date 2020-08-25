package com.revature.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.ReimbStatus;
import com.revature.utils.ConnectionUtil;

public class ReimbStatusDAO implements IReimbStatusDAO {

	private static final Logger log = LogManager.getLogger(UserDAO.class);
	
	@Override
	public List<ReimbStatus> findReimbStatusByUserId(int userid) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursement "
					+ "JOIN ers_reimbursement_status "
					+ "ON ers_reimbursement.reimb_status_id_fk = ers_reimbursement_status.reimb_status_id "
					+ "WHERE ers_reimbursement.ers_users_id_fk_auth =" + userid + ";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			List<ReimbStatus> list = new ArrayList<>();
			
			while(result.next()) {
				ReimbStatus status = new ReimbStatus();
				status.setReimbStatusId(result.getInt("reimb_status_id")); 
				status.setReimbStatus(result.getString("reimb_status"));
				
				list.add(status);
			} 
			log.info("ReimbStatusDAO successfully found user status by user id from DB." + userid);
			return list;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		log.info("ReimbStatusDAO could not find user status by user id from DB." + userid);
		return null;
	}

}
/*  example user id = 2

	1	666.66	2020-08-23 11:22:46		travel cost				2(user id)	3	2	3	Pending
	2	101.47	2020-08-23 12:02:26		certification cost		2			3	4	3	Pending
*/