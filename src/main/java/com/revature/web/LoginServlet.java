package com.revature.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.LoginController;
import com.revature.controllers.UserController;
import com.revature.models.LoginDTO;
import com.revature.models.UserRoles;
import com.revature.models.Users;
import com.revature.services.UserRoleService;
import com.revature.services.UserService;

public class LoginServlet extends HttpServlet{
	
	private static LoginController loginController = new LoginController();
	private static UserController userController = new UserController();
	private static UserService userService = new UserService();
	private static UserRoleService userRoleService = new UserRoleService();
	
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
		System.out.println("portions[0] =  " + portions[0] + " ...");
		
		if(portions.length == 0) {
			
			System.out.println(" in if portions.length ...");
			req.getRequestDispatcher("index.html").forward(req, res);		
		}
		
		try {
			
			switch(portions[0]) {
				case "takemehome":
					
					System.out.println("reqest in login servlet " + req.getContentType());
					
					if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("loggedin")) {
						
						LoginDTO loginDto = (LoginDTO) req.getSession().getAttribute("user");
						
						System.out.println("**************** User username: " +  loginDto.username);
						
						Users user = userService.findUserByUsername(loginDto.username);
						UserRoles ur= user.getUserRoleFK();	
						
						System.out.println("user " + user);
						System.out.println("user role " + ur);
					
						if(req.getMethod().equals("GET")) {
							
							res.setContentType("text/html");
							userController.getUserRole( req, res, user);
							//req.getRequestDispatcher("emp_home.html").forward(req,res);
//							
//							
						}
					}
					
					/*
					
					if(req.getSession(false) != null && (boolean)req.getSession().getAttribute("loggedin")) {	
						
						System.out.println("in loggedin if block");
						
						if(req.getMethod().equals("GET")) {
							
							System.out.println("in req GET ");
							
							if(portions.length == 1) {
								System.out.println("user information response " + res ) ;
								
								LoginDTO loginDto = (LoginDTO) req.getSession().getAttribute("user");
								System.out.println("Login Servlet: case user: " + loginDto.username);
								
								req.getRequestDispatcher("emp_home.html").forward(req,res);
								userController.getUserByNamePw(res, loginDto.username, loginDto.password);
					*/			
								
								
//								Users user = userService.findUserByNamePW(loginDto.username,loginDto.password);   
//								UserRoles userRole = userRoleService.findUserRoleByUserId(user.getUsersId());
//								String role = userRole.getUserRole();
//								System.out.println("login servlet user role "+ role);
								
										
//								userController.getUserRole(res, userid);
								
//								
								
//								if (userRole.getUserRole().equals("Employee")) {	
//								
//								req.getRequestDispatcher("emp_home.html").forward(req,res);
//								
//								System.out.println("Employee Home page Loaded.");
//								
//							} else if (userRole.getUserRole().equals("Manager")) {
//								
//								req.getRequestDispatcher("admin_home.html").forward(req,res);
//								
//								System.out.println("Admin Home page Loaded.");
//							}
				
					
					/*
		
							}
	
							
						} // end of if GET
						else if(req.getMethod().equals("POST")) {	
							System.out.println("In POST req");
							//avengerController.addAvenger(req,res);									
						}
										

					} // end of if loggedin
					else {
						res.setStatus(403);
						res.getWriter().println("You must be logged in to do that");
					}
					
					*/
					
					break;
					
				case "login":
					loginController.login(req, res);				
					break;
					
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
	

}
