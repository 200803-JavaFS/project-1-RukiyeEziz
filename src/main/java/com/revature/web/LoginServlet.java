package com.revature.web;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbController;
import com.revature.controllers.UserController;
import com.revature.models.LoginDTO;
import com.revature.models.UserRoles;
import com.revature.models.Users;
import com.revature.services.UserRoleService;
import com.revature.services.UserService;

public class LoginServlet extends HttpServlet{
	
	private static LoginController loginController = new LoginController();
	private static UserController userController = new UserController();
	private static ReimbController reimbController = new ReimbController();
	private static UserService userService = new UserService();
	private static UserRoleService userRoleService = new UserRoleService();
	RequestDispatcher rd = null;
	
	public LoginServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("application/json");
		res.setStatus(404);
		System.out.println("In Login Servlet...");	
		
		//RequestDispatcher td = null;
		
		//example of 	URI =  /avenger/{id}  --> length is 2, index[0] is ---> /avenger,  index[1] is --> /1
		final String URI = req.getRequestURI().replace("/project1/", "");		
		String[] portions = URI.split("/");		
		System.out.println("In Login Servlet portions: " + Arrays.toString(portions));
		System.out.println("portions[0] =  " + portions[0]);
		
//		Users user = null;
//		UserRoles userRole = null;
		
		if(portions.length == 0) {
			
			System.out.println(" in if portions.length ...");
			req.getRequestDispatcher("index.html").forward(req, res);		
		}
		
		
		try {
			
			switch(portions[0]) {
				case "login":
					loginController.login(req, res);	
					System.out.println("req = " + req);
					System.out.println("res = "+ res);
					break;
					//////////////////
				case "success":
					
					//System.out.println("reqest in login servlet " + req.getSession().getAttribute("user"));
					//req.getSession().getAttributeNames();
					
					if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("loggedin")) {
							
						
						LoginDTO loginDto = (LoginDTO) req.getSession().getAttribute("user");
						System.out.println("loginDTO name = " + loginDto.username + " pw = " + loginDto.password);
						//System.out.println("what is req session " + req.getSession());
											
						System.out.println(" success user " + loginDto);
						
						String hashedPWFromClient = getHashSHA1(loginDto.password);
						System.out.println("hashed pw " + hashedPWFromClient);
						
						Users user = userService.findUserByNamePW(loginDto.username, hashedPWFromClient);
						UserRoles userRole = user.getUserRoleFK();
						
						System.out.println(user + " \n " + userRole);
						
						int uId = user.getUsersId();
						String uRole = user.getUserRoleFK().getUserRole();
						
						System.out.println("user id = " + uId+ " , user role = "+uRole);
						
						if(req.getMethod().equals("GET")) {
							
							if(portions.length == 1) {
								
								System.out.println("user " + res);
								userController.setUserRole(req, res, user);
								
						
							}
						}
						
					}
							
					break;
					////////////////////
				case "employee":
					
					System.out.println("in employee case ");
					//System.out.println("portions[1]  " + portions[1]);
					
					if(req.getMethod().equals("GET")) {
						if(portions.length == 2) {
							int userid = Integer.parseInt(portions[1]);
							reimbController.getEmpReimbursement(res, userid);
						}	
					}
					else if(req.getMethod().equals("POST")){
						//System.out.println("portions[0]  " + portions[0]);
						reimbController.addReimbursement(req, res);
					}

					break;
					///////////////////
				case "manager":
					System.out.println("manager case");
					if(portions.length == 2) {
						
					}else {
						reimbController.getAllReimbursements(res);
					}
					break;
					//////////////////
				case "logout":
					loginController.logout(req, res);
					break;
					
			}// end of switch
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
			res.getWriter().print("The id you provided is not integer");
			res.setStatus(400);
		}	
		
	}// end of do get
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);		
	}
	//////////////////////////
	private static String getHashSHA1(String pw) {
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
	//////////////////////////////
	


}
