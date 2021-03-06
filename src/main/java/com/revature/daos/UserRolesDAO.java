package com.revature.daos;

//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import com.revature.utils.ConnectionUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.revature.models.UserRoles;
import com.revature.utils.HibernateUtil;

public class UserRolesDAO implements IUserRoleDAO {

	private static final Logger log = LogManager.getLogger(UserRolesDAO.class);

	@Override
	public UserRoles findByUserRoleId(int userroleid) {
		Session session = HibernateUtil.getSession();
		UserRoles userRole = session.get(UserRoles.class, userroleid);
		return userRole;
		
	}
	
	@Override
	public UserRoles findUserRoleByUserId(int userid) {
		System.out.println("find user role by user id isnt implemented");		
		return null;
	}

}

/* example user id = 2

	2(user id)	Aki	 Aki	Aki	 Tartamella	akitartamella@gmail.com		2	2	Employee
*/

/*
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
			
			log.info("UserRolesDAO successfully found user role by user id from DB." + userid);
			return userRole;
		} else {
			return null;
		}
		
	} catch(SQLException e) {
		e.printStackTrace();
	}
	
	log.info("UserRolesDAO could not find user role by user id from DB." + userid);
	return null;
}
*/