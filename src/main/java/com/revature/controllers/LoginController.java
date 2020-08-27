package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.models.UserRoles;
import com.revature.models.Users;
import com.revature.services.LoginService;
import com.revature.services.UserRoleService;
import com.revature.services.UserService;

public class LoginController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static LoginService loginService = new LoginService();
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static UserService userService = new UserService();
	private static UserRoleService userRoleService = new UserRoleService();
	
	
	RequestDispatcher rd = null;
	
	public void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LoginDTO loginDto = new LoginDTO();
		
		String userName = req.getParameter("login_username");
		String password = req.getParameter("login_pw");
		
		loginDto.username = userName;
		loginDto.password = password;
		
		//loginDto.setUsername(userName);
		//loginDto.setPassword(password);
		
		System.out.println("login controller loginDTO info for  " + loginDto.username + " " + loginDto.password);
		
//		 if(req.getMethod().equals("POST")){
//			 
//			 Users user = new Users();
//			 user.setUserName(req.getParameter("login_username"));
//			 user.setPassword(req.getParameter("login_pw"));
//			 
//			 System.out.println("Login controller login method user " + user);
//			 
//			 RequestDispatcher rd = null;
//			 PrintWriter out = res.getWriter();
//		 
//		 }
		RequestDispatcher rd = null;
		PrintWriter out = res.getWriter();
		
		
		if(req.getMethod().equals("GET")) {
			
			if(loginService.login(loginDto)) {
				
				HttpSession ses = req.getSession();
				ses.setAttribute("user", loginDto);
				ses.setAttribute("loggedin", true);
				res.setStatus(200);
				res.getWriter().println("Login Controller GET - Login Successful");
				
			} else {
				
				HttpSession ses = req.getSession(false);
				if(ses != null) {
					ses.invalidate();
				}
				res.setStatus(401);
				res.getWriter().println("Login Controller - Login failed");
			}
		}
		else if(req.getMethod().equals("POST")) { // i omitted if for GET method
			
			BufferedReader reader = req.getReader();
			StringBuilder stringBuilder = new StringBuilder();
			String line = reader.readLine();
			
			while (line != null) {
				stringBuilder.append(line);
				line=reader.readLine();
			}
			
			String body = new String(stringBuilder);
			System.out.println("body: "+ body);
			
//			LoginDTO loginDto = objectMapper.readValue(body, LoginDTO.class);
			
			if(loginService.login(loginDto)) {
				
				HttpSession session = req.getSession();
				System.out.println("Login Controller POST- You logged in, success!");
				
				session.setAttribute("user", loginDto);
				session.setAttribute("loggedin", true);
				
				res.setStatus(200);
				res.getWriter().println("Login Controller POST- Login Successful");
				

				res.setContentType("text/html");
				
				Users user = userService.findUserByUsername(userName) ;
				UserRoles userRole = userRoleService.findUserRoleByUserId(user.getUsersId());
				
				// what should i do???????????
				
//				In Login Servlet...
//				login controller loginDTO info for  Aki Aki
//				body: 
//				22:43:47.078 [http-nio-8080-exec-9] INFO  com.revature.services.UserService - In UserService looking for user name and pw ... AkiAki
//				22:43:47.881 [http-nio-8080-exec-9] INFO  com.revature.daos.UserDAO - UserDAO found user and pw info from DB: Users [usersId=2, userName=Aki, password=Aki, firstName=Aki, lastName=Tartamella, email=akitartamella@gmail.com, userRolesFK=2]
//				22:43:47.882 [http-nio-8080-exec-9] INFO  com.revature.services.LoginService - Login Service loginAkiAki
//				22:43:47.882 [http-nio-8080-exec-9] INFO  com.revature.services.LoginService - LoginService successfully processed. User Logged in.
//				Login Controller POST- You logged in, success!
//				22:43:47.890 [http-nio-8080-exec-9] INFO  com.revature.services.UserService - In UserService find the user by user name.
//				22:43:48.172 [http-nio-8080-exec-9] INFO  com.revature.daos.UserDAO - UserDAO successfully found user by user name from DB.Users [usersId=2, userName=Aki, password=Aki, firstName=Aki, lastName=Tartamella, email=akitartamella@gmail.com, userRolesFK=2]
//				22:43:48.173 [http-nio-8080-exec-9] INFO  com.revature.services.UserRoleService - UserRoleService tying to found user role by user id from DB.2
//				22:43:48.452 [http-nio-8080-exec-9] INFO  com.revature.daos.UserRolesDAO - UserRolesDAO successfully found user role by user id from DB.2
//				
				
				
				
//				find user role by user id or whatever
//				depend on the user role maybe 2 different html or one for now?
				
			}
			else {
				HttpSession session = req.getSession(false);
				
				if(session != null) {
					session.invalidate();
				}
				
				res.setStatus(401);
				res.getWriter().println("Login Failed.");
			}
			
		}// end of POST if
		 
		 
		 
	}//end of login
	
	public void logout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		
		if(session != null) {
			
			LoginDTO loginDto = (LoginDTO)session.getAttribute("user");
			session.invalidate();
			
			res.setStatus(200);
			res.getWriter().println(loginDto.username + " has logged out successfully");
			
		}else {
			res.setStatus(401);
			res.getWriter().println("You must be logged in to logout");
		}
	
		
	}
	
	
	

}
