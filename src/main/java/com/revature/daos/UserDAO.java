package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.UserRoles;
import com.revature.models.Users;
import com.revature.utils.ConnectionUtil;

public class UserDAO implements IUserDAO {

	private static final Logger log = LogManager.getLogger(UserDAO.class);
	
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



}
