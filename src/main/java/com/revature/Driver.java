package com.revature;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.revature.daos.ReimbStatusDAO;
import com.revature.daos.ReimbTypeDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.daos.UserRolesDAO;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.Users;
import com.revature.services.UserService;


public class Driver {
	
	public static UserDAO uDAO = new UserDAO();
	public static UserRolesDAO urDAO = new UserRolesDAO();
	public static ReimbursementDAO rDAO = new ReimbursementDAO();
	private static ReimbStatusDAO rsDao = new ReimbStatusDAO();
	private static ReimbTypeDAO rtDao = new ReimbTypeDAO();
	private static UserService us = new UserService();
	
	
	public static void main(String[] args) {
		
		// testing testing testing ****************** DO NOT DELETE **********************
		
//		
//		String emp = uDAO.findByUsername("Aki").getUserRoleFK().getUserRole();		
//		System.out.println("Aki is an  " + emp); //
		// Aki is an  Employee
//		
//		String mng= uDAO.findByUsername("Ruki").getUserRoleFK().getUserRole();		
//		System.out.println("Ruki is " + mng);
		// Ruki is Manager
//		
		
//		List<Users> users = uDAO.findAllUser();
//		for (Users r: users) {
//			System.out.println(r);
//		}
		//output
		// Users [usersId=1, userName=Ruki, password=Ruki, firstName=Rukiye, lastName=Eziz, email=rukiye.eziz@revature.net, userRole=UserRoles [userRoleId=1, userRole=Manager]]
		// Users [usersId=2, userName=Aki, password=Aki, firstName=Aki, lastName=Tartamella, email=akitartamella@gmail.com, userRole=UserRoles [userRoleId=2, userRole=Employee]]
		// Users [usersId=3, userName=JJ, password=JJ, firstName=Jamie, lastName=Tartamella, email=jtartam@gmail.com, userRole=UserRoles [userRoleId=2, userRole=Employee]]
		
		
//		UserRoles ur = urDAO.findByUserRoleId(1);
//		System.out.println("find by userrole id 1 is : "+ur);		
		// find by userrole id 1 is : UserRoles [userRoleId=1, userRole=Manager]
		
//		UserRoles uRole = users.get(1).getUserRoleFK();
//		System.out.println("user role of id from users list : "+ uRole);		
		// user role of id  from users list : UserRoles [userRoleId=2, userRole=Employee]
		
//		Reimbursement r = rDAO.findByReimbId(2);
//		System.out.println("reimb id 2 = " + r);	
		// reimb id 2 = Reimbursement [reimbId=2, reimbAmount=101.47, reimbSubmitted=2020-08-29 22:24:49.495248, reimbResolved=null, reimbDescription=certification cost, reimbReceipt=null]		
//		System.out.println("its status is " + r.getReimbStatusFK());
		// its status is ReimbStatus [reimbStatusId=1, reimbStatus=Approved]
		
//		Users u= uDAO.findUserById(1);		
//		System.out.println("user by userid = " + u);
		// user by userid = Users [usersId=1, userName=Ruki, password=Ruki, firstName=Rukiye, lastName=Eziz, email=rukiye.eziz@revature.net, userRole=UserRoles [userRoleId=1, userRole=Manager]]

//		List<Reimbursement> reimbs = rDAO.findAllReimb();
//		System.out.println("find all reimbs");
//		for (Reimbursement r: reimbs) {
//			System.out.println(r);
//		// Reimbursement [reimbId=1, reimbAmount=666.66, reimbSubmitted=2020-08-23 11:22:46.453997, reimbResolved=null, reimbDescription=travel cost, reimbReceipt=null]
		// Reimbursement [reimbId=3, reimbAmount=222.11, reimbSubmitted=2020-08-24 20:19:35.095783, reimbResolved=null, reimbDescription=Food cost, reimbReceipt=null]
		// Reimbursement [reimbId=2, reimbAmount=101.47, reimbSubmitted=2020-08-29 22:24:49.495248, reimbResolved=null, reimbDescription=certification cost, reimbReceipt=null]	
//		System.out.println("index 0 from list reimb " + reimbs.get(0));
		// index 0 from list reimb Reimbursement [reimbId=1, reimbAmount=666.66, reimbSubmitted=2020-08-23 11:22:46.453997, reimbResolved=null, reimbDescription=travel cost, reimbReceipt=null]
//		System.out.println("index 0 from list reimb;s status " + reimbs.get(0).getReimbStatusFK());
		// index 0 from list reimb ReimbStatus [reimbStatusId=3, reimbStatus=Pending]
//		System.out.println("index 0 from list reimb;s status get status " + reimbs.get(0).getReimbStatusFK().getReimbStatus());
		// index 0 from list reimb;s status get status  Pending
		
		
		//findAllReimbs();			// find all the reimbs for manager
		
		//findReimbByStatusId();	// find the status approved all reimbs
		
		//findReimbByUserId();		// find the all reimbs that belong to specific user 
		
		//addReimb();
		
		//updateEmpReimb();
		
		
		
		////////////////////////////////////// hash password v2
//		
//		String pw = "JJ";
//		String name = "JJ";
//		
//		String hashedPW1 = "";
//		hashedPW1 = getHashSHA1(pw);
//		System.out.println("hashedPW1 = " + hashedPW1);
//		
//		String hashedPW2= "";
//		Users u = uDAO.findUserByNamePW(name, hashedPW1);
//		//System.out.println("hashedPW2 = " + hashedPW2);
//		
//
//		
//		if(hashedPW1.equals(u.getPassword())) {
//			System.out.println(hashedPW1 + " = " + u.getPassword());
//			System.out.println("GOOD");
//		}else {
//			System.out.println(hashedPW2 + " not equal to  " + u.getPassword());
//		}
//		
//		System.out.println("user service result   " + us.findUserByNamePW(name, hashedPW1));
		
		
		
//		String hashedPW2= "";
//		hashedPW2 = getHashSHA1(pw);
//		System.out.println("hashedPW2 = " + hashedPW2);
		
		
//		if(pw.equals(hashedPW1)) {
//			System.out.println(pw + " = " + hashedPW1);
//		}else {
//			System.out.println(pw + " not equal to  " + hashedPW1);
//		}
		
		
		
		/////////////////////////////////////////////////////////////
		
		//findUserPW(); 		// use for login, finding user id and the role, 
		//21:10:43.662 [main] INFO  com.revature.daos.UserDAO - User Dao find user by name and pw Users [usersId=2, userName=Aki, password=Aki, firstName=Aki, lastName=Tartamella, email=akitartamella@gmail.com, userRole=UserRoles [userRoleId=2, userRole=Employee]]
		//User = Aki, pw = Aki
		//User id = 2, user role info = UserRoles [userRoleId=2, userRole=Employee], user role = Employee, user role id = 2


	}
	
	
	
	public static String getHashSHA1(String pw) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(pw.getBytes());
			byte byteData[] = md.digest();
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString(
						(byteData[i] & 0xff) + 0x100, 16).substring(1));
				
			}
			return sb.toString();
		}catch(NoSuchAlgorithmException ex) {
			System.out.println("something went wrong with pw hashing.");
			return null;
		}
	}
	

	private static void findUserPW() {
		String u = "Aki";
		String pw = "Aki";
		
		Users user = uDAO.findUserByNamePW(u, pw);
		System.out.println("User = " + user.getUserName() + ", pw = " + user.getPassword());
		System.out.println("User id = " + user.getUsersId() + ", user role info = " + user.getUserRoleFK()
							+ ", user role = " + user.getUserRoleFK().getUserRole() + ", user role id = " + user.getUserRoleFK().getUserRoleId());
		
	}
	
	private static void findAllReimbs() {
		List<Reimbursement> rs = rDAO.findAllReimb();
		for (int i = 0; i < rs.size(); ++ i) {
		     System.out.println(rs.get(i));
		     System.out.println(rs.get(i).getReimbAuthor()+"\n");
		     //Reimbursement [reimbId=1, reimbAmount=666.66, reimbSubmitted=2020-08-23 11:22:46.453997, reimbResolved=null, reimbDescription=travel cost, reimbReceipt=null]
    		 //Users [usersId=2, userName=Aki, password=Aki, firstName=Aki, lastName=Tartamella, email=akitartamella@gmail.com, userRole=UserRoles [userRoleId=2, userRole=Employee]]

			 //Reimbursement [reimbId=3, reimbAmount=222.11, reimbSubmitted=2020-08-24 20:19:35.095783, reimbResolved=null, reimbDescription=Food cost, reimbReceipt=null]
			 //Users [usersId=3, userName=JJ, password=JJ, firstName=Jamie, lastName=Tartamella, email=jtartam@gmail.com, userRole=UserRoles [userRoleId=2, userRole=Employee]]

			 //Reimbursement [reimbId=2, reimbAmount=101.47, reimbSubmitted=2020-08-29 22:24:49.495248, reimbResolved=null, reimbDescription=certification cost, reimbReceipt=null]
			 //Users [usersId=2, userName=Aki, password=Aki, firstName=Aki, lastName=Tartamella, email=akitartamella@gmail.com, userRole=UserRoles [userRoleId=2, userRole=Employee]]

			 //Reimbursement [reimbId=6, reimbAmount=999.22, reimbSubmitted=2020-09-02 22:21:59.441196, reimbResolved=2020-09-02 22:21:56.541, reimbDescription=other cost, reimbReceipt=null]
			 //Users [usersId=2, userName=Aki, password=Aki, firstName=Aki, lastName=Tartamella, email=akitartamella@gmail.com, userRole=UserRoles [userRoleId=2, userRole=Employee]]		 
		}
	}

	
	private static void findReimbByStatusId() {
		List<Reimbursement> rs = rDAO.findReimbursementByStatus(1); 

		for (Reimbursement r: rs) {
			System.out.println(r);
			// Reimbursement [reimbId=2, reimbAmount=101.47, reimbSubmitted=2020-08-29 22:24:49.495248, reimbResolved=null, reimbDescription=certification cost, reimbReceipt=null]
			System.out.println(r.getReimbStatusFK());
			// ReimbStatus [reimbStatusId=1, reimbStatus=Approved]
			System.out.println(r.getReimbStatusFK().getReimbStatus());
			// Approved
		}
	}
	private static void findReimbByUserId() {
		Users emp = uDAO.findByUsername("Aki");
		System.out.println("emp Aki : " + emp);
		List<Reimbursement> rs = rDAO.findReimbursementByUserId(emp);
		for (Reimbursement r: rs) {
			System.out.println(r+"\n");
			//Reimbursement [reimbId=1, reimbAmount=666.66, reimbSubmitted=2020-08-23 11:22:46.453997, reimbResolved=null, reimbDescription=travel cost, reimbReceipt=null]

			//Reimbursement [reimbId=2, reimbAmount=101.47, reimbSubmitted=2020-08-29 22:24:49.495248, reimbResolved=null, reimbDescription=certification cost, reimbReceipt=null]

			//Reimbursement [reimbId=6, reimbAmount=999.22, reimbSubmitted=2020-09-02 22:21:59.441196, reimbResolved=2020-09-02 22:21:56.541, reimbDescription=other cost, reimbReceipt=null]
			System.out.println(r.getReimbStatusFK() +"\n");
		
		}
		for (int i = 0; i < rs.size(); ++ i) {
		     System.out.println(rs.get(i));
		     System.out.println(rs.get(i).getReimbAuthor());
		     System.out.println(rs.get(i).getReimbStatusFK()+"\n");
		     

			//Reimbursement [reimbId=1, reimbAmount=666.66, reimbSubmitted=2020-08-23 11:22:46.453997, reimbResolved=null, reimbDescription=travel cost, reimbReceipt=null]
			//Users [usersId=2, userName=Aki, password=Aki, firstName=Aki, lastName=Tartamella, email=akitartamella@gmail.com, userRole=UserRoles [userRoleId=2, userRole=Employee]]
			//ReimbStatus [reimbStatusId=3, reimbStatus=Pending]
			//
			//Reimbursement [reimbId=2, reimbAmount=101.47, reimbSubmitted=2020-08-29 22:24:49.495248, reimbResolved=null, reimbDescription=certification cost, reimbReceipt=null]
			//Users [usersId=2, userName=Aki, password=Aki, firstName=Aki, lastName=Tartamella, email=akitartamella@gmail.com, userRole=UserRoles [userRoleId=2, userRole=Employee]]
			//ReimbStatus [reimbStatusId=1, reimbStatus=Approved]
			//
			//Reimbursement [reimbId=6, reimbAmount=999.22, reimbSubmitted=2020-09-02 22:21:59.441196, reimbResolved=2020-09-02 22:21:56.541, reimbDescription=other cost, reimbReceipt=null]
			//Users [usersId=2, userName=Aki, password=Aki, firstName=Aki, lastName=Tartamella, email=akitartamella@gmail.com, userRole=UserRoles [userRoleId=2, userRole=Employee]]
			//ReimbStatus [reimbStatusId=1, reimbStatus=Approved]
		   }
	}
	private static void addReimb() {
		Users aki= uDAO.findByUsername("aki");
		ReimbStatus rs = rsDao.findReimbStatus("Pending");
		ReimbType rt = rtDao.findReimbTypeByType("Other");
		
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		System.out.println(timestamp);		
		System.out.println(((Object)timestamp).getClass().getName());

		Reimbursement r = new Reimbursement(111.22, timestamp, null, "other cost", null , aki, null, rs, rt);
		rDAO.addReimbursement(r);
	}
	
	
	private static void updateEmpReimb() {
		
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		
		Users mng = uDAO.findByUsername("ruki"); // resolver
		Users emp = uDAO.findByUsername("aki"); // emp
		Reimbursement r = rDAO.findByReimbId(2);	
		ReimbStatus rs = rsDao.findReimbStatus("Approved"); 
		
		r.setReimbResolver(mng);		
		r.setReimbStatusFK(rs);
		r.setReimbResolved(timestamp);

		rDAO.updateReimbursement(r);
		System.out.println(r);
	}




}
