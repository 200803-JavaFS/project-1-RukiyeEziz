package com.revature.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.UserRoles;
import com.revature.utils.ConnectionUtil;

public class UserRolesDAO implements IUserRoleDAO {

	private static final Logger log = LogManager.getLogger(UserDAO.class);
	
	@Override
	public UserRoles findUserRoleByUserId(int userid) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_users "
					+ "JOIN ers_user_roles "
					+ "ON ers_users.user_role_id_fk = ers_user_roles.ers_user_role_id "
					+ "WHERE ers_users.ers_users_id =" + userid + ";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (result.next()) {
				UserRoles userRole = new UserRoles();
				
				userRole.setUserRoleId(result.getInt("ers_user_role_id")); 
				userRole.setUserRole(result.getString("ers_user_role"));
				
				log.info("UserDAO successfully found user role by user id from DB." + userid);
				return userRole;
			} else {
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		log.info("UserDAO could not find user role by user id from DB." + userid);
		return null;
	}

}
