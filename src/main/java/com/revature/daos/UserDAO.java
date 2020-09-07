package com.revature.daos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import com.revature.utils.ConnectionUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.revature.models.Users;
import com.revature.utils.HibernateUtil;


public class UserDAO implements IUserDAO {

	private static final Logger log = LogManager.getLogger(UserDAO.class);
	public UserDAO() {
		super();
	}
	
	@Override
	public List<Users> findAllUser() {
		Session session = HibernateUtil.getSession();
		List<Users> list = session.createQuery("From Users").list();
		log.info("User DAO find all user. " + list);
		return list;
	}
	
	@Override
	public Users findUserByNamePW(String username, String password) {
		
		
		log.info("UserDao looking for user name and pw ... " + username + " ---- " + password);
		Session session = HibernateUtil.getSession();
		List<Users> list  = session.createQuery("FROM Users WHERE userName = '" + username + "' AND password = '" + password + "'", Users.class).list();
		Users user = list.get(0);
	
		log.info("User Dao find user by name and pw " + user);
		return user;
	}	
	
	@Override
	public Users findUserById(int userid) {
		Session session = HibernateUtil.getSession();
		Users user = session.get(Users.class, userid);
		log.info("User Dao find user by user id " + user);
		return user;
	}

	@Override
	public Users findByUsername(String username) {
		Session session = HibernateUtil.getSession();
		List<Users> list  = session.createQuery("FROM Users WHERE userName = '" + username + "'", Users.class).list();
		Users user = list.get(0);
		log.info("User Dao find user by name " + user);
		return user;
	}

	@Override
	public boolean addUser(Users user) {
		Session session = HibernateUtil.getSession();
		
		try {
			
			session.save(user);
			log.info("User DAO added the user .....");
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
		
	}

	@Override
	public boolean updateUser(Users user) {
		Session session = HibernateUtil.getSession();
		
		try {
			
			session.merge(user);
			log.info("User DAO updated the user .....");
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
		
	}
	
	
	
	


}


/*	
@Override
public List<Users> findAllUser() {
	
	try(Connection conn = ConnectionUtil.getConnection()) {
		String sql = "SELECT * FROM ers_users;";
		Statement statement = conn.createStatement();
		List<Users> list = new ArrayList<>();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()) {
			Users user = new Users();
			user.setUsersId(result.getInt("ers_users_id"));				// PK
			user.setUserName(result.getString("ers_username"));
			user.setPassword(result.getString("ers_password"));
			user.setFirstName(result.getString("user_first_name"));
			user.setLastName(result.getString("user_last_name"));
			user.setEmail(result.getString("user_email"));
			user.setUserRolesFK(result.getInt("user_role_id_fk"));		// FK
			
			list.add(user);
		}
		log.info("UserDAO successfully found all users from DB.");
		return list;
		
	} catch(SQLException e) {
		e.printStackTrace();
	}
	log.info("UserDAO could not find all users from DB.");
	return null;
}

@Override
public Users findUserById(int userid) {
	
	try (Connection conn = ConnectionUtil.getConnection()) {
		String sql = "SELECT * FROM ers_users WHERE ers_users_id =" + userid + ";";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		if (result.next()) {
			Users user = new Users();
			user.setUsersId(result.getInt("ers_users_id"));				// PK
			user.setUserName(result.getString("ers_username"));
			user.setPassword(result.getString("ers_password"));
			user.setFirstName(result.getString("user_first_name"));
			user.setLastName(result.getString("user_last_name"));
			user.setEmail(result.getString("user_email"));
			user.setUserRolesFK(result.getInt("user_role_id_fk"));		// FK
			
			log.info("UserDAO successfully found user by id from DB." + userid);
			return user;
		} else {
			return null;
		}
	} catch(SQLException e) {
		e.printStackTrace();
	}
	log.info("UserDAO could not find user by id from DB." + userid);
	return null;
}

@Override
public Users findByUsername(String username) {
	try (Connection conn = ConnectionUtil.getConnection()) {
		
		String sql = "SELECT * FROM ers_users WHERE ers_username = ? ;";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet result = statement.executeQuery();
		
		if (result.next()) {
			Users user = new Users();
			user.setUsersId(result.getInt("ers_users_id"));				// PK
			user.setUserName(result.getString("ers_username"));
			user.setPassword(result.getString("ers_password"));
			user.setFirstName(result.getString("user_first_name"));
			user.setLastName(result.getString("user_last_name"));
			user.setEmail(result.getString("user_email"));
			user.setUserRolesFK(result.getInt("user_role_id_fk"));		// FK
			
			log.info("UserDAO successfully found user by user name from DB." + user);
			return user;
			
		} else {
			return null;
		}
	} catch(SQLException e) {
		e.printStackTrace();
	}
	log.info("UserDAO could not find user by user name from DB." + username);
	return null;
}

/// this is for login check ????
@Override
public Users findUserByNamePW(String username, String password) {
	
	try(Connection conn = ConnectionUtil.getConnection()){
		String sql = "SELECT * FROM ers_users WHERE ers_username = ? and ers_password = ?;";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		
		statement.setString(1, username);
		statement.setString(2, password);
		
		ResultSet result = statement.executeQuery();
		if(result.next()) {

			Users user = new Users();
//					result.getInt("ers_users_id"),
//					result.getString("ers_username"),
//					result.getString("ers_password"));
//					//result.getString("user_type"));
					
			user.setUsersId(result.getInt("ers_users_id"));				// PK
			user.setUserName(result.getString("ers_username"));
			user.setPassword(result.getString("ers_password"));
			user.setFirstName(result.getString("user_first_name"));
			user.setLastName(result.getString("user_last_name"));
			user.setEmail(result.getString("user_email"));
			user.setUserRolesFK(result.getInt("user_role_id_fk"));		// FK
	
			log.info("UserDAO found user and pw info from DB: " + user);
			
			return user;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	log.info("UserDAO could not find user name and pw from DB.");
	return null;
}
*/

/*
 * @Override public Users findUserByIdPW(int userid, String password) {
 * 
 * try (Connection conn = ConnectionUtil.getConnection()) { String sql =
 * "SELECT * FROM ers_users WHERE ers_users_id = " + userid +
 * " AND ers_password = " + password + ";"; Statement statement =
 * conn.createStatement(); ResultSet result = statement.executeQuery(sql);
 * 
 * if (result.next()) { Users user = new Users();
 * user.setUsersId(result.getInt("ers_users_id")); // PK
 * user.setUserName(result.getString("ers_username"));
 * user.setPassword(result.getString("ers_password"));
 * user.setFirstName(result.getString("user_first_name"));
 * user.setLastName(result.getString("user_last_name"));
 * user.setEmail(result.getString("user_email"));
 * user.setUserRolesFK(result.getInt("user_role_id_fk")); // FK
 * 
 * log.info("UserDAO successfully found user by id and pw from DB." + userid);
 * return user; } else { return null; } } catch(SQLException e) {
 * e.printStackTrace(); }
 * log.info("UserDAO could not find user by id and pw from DB." + userid);
 * 
 * return null; }
 */

/*
 * @Override public List<String> findUserRoleByUserId(int userroleid) {
 * 
 * try (Connection conn = ConnectionUtil.getConnection()) { String sql =
 * "SELECT ers_users.ers_users_id, ers_users.user_role_id_fk, ers_user_roles.ers_user_role"
 * + " FROM ers_users , ers_user_roles WHERE ers_users.user_role_id_fk = " +
 * userroleid + ";"; Statement statement = conn.createStatement(); ResultSet
 * result = statement.executeQuery(sql); List<String> list = new ArrayList<>();
 * 
 * if (result.next()) { Users user = new Users(); UserRoles userRole = new
 * UserRoles(); user.setUsersId(result.getInt("ers_users_id")); // PK
 * //user.setUserName(result.getString("ers_username"));
 * //user.setPassword(result.getString("ers_password"));
 * //user.setFirstName(result.getString("user_first_name"));
 * //user.setLastName(result.getString("user_last_name"));
 * //user.setEmail(result.getString("user_email"));
 * user.setUserRolesFK(result.getInt("user_role_id_fk")); // FK
 * userRole.setUserRole(result.getString("ers_user_role"));
 * 
 * list.add(user);
 * 
 * 
 * log.info("UserDAO successfully found user by id from DB." + userid); return
 * user; } else { return null; } } catch(SQLException e) { e.printStackTrace();
 * } log.info("UserDAO could not find user by id from DB." + userid); return
 * null; }
 */
/*
@Override
public boolean addUser(Users user) {
	try (Connection conn = ConnectionUtil.getConnection()) {
		String sql = "INSERT INTO users (ers_username , ers_password , user_first_name , user_last_name , user_email , user_role_id_fk )"
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement statement = conn.prepareStatement(sql);
		
		int index = 0;
		statement.setString(++index, user.getUserName());
		statement.setString(++index, user.getPassword());
		statement.setString(++index, user.getFirstName());
		statement.setString(++index, user.getLastName());
		statement.setString(++index, user.getEmail());
		statement.setInt(++index, user.getUserRolesFK());
		
		statement.execute();
		
		log.info("UserDAO successfully added new user to DB: " + user);
		return true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	log.info("UserDAO could not add new user to DB.");
	return false;
}

@Override
public boolean updateUser(Users user) {
	
	try (Connection conn = ConnectionUtil.getConnection()) {
		String sql = "UPDATE users SET ers_username = ?, ers_password + ?, user_first_name = ?, user_last_name = ?, user_email = ?, user_role_id_fk = ? ;";
		PreparedStatement statement = conn.prepareStatement(sql);
		
		int index = 0;
		statement.setString(++index, user.getUserName());
		statement.setString(++index, user.getPassword());
		statement.setString(++index, user.getFirstName());
		statement.setString(++index, user.getLastName());
		statement.setString(++index, user.getEmail());
		statement.setInt(++index, user.getUserRolesFK());
		
		statement.execute();
		
		log.info("UserDAO successfully updated user from DB: " + user);
		return true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	log.info("UserDAO could not update user from DB.");
	return false;
}

*/

