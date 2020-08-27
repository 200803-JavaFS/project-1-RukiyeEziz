package com.revature.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.LoginController;
import com.revature.controllers.UserController;

public class LoginServlet extends HttpServlet{
	
	private static LoginController loginController = new LoginController();
	private static UserController HomeController = new UserController();
	
	public LoginServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404);
		System.out.println("In Login Servlet...");
		
		res.setStatus(404);
		
		///// avengerrrrrrrrrrrrrrrrrrrrrr
		
//		final String URI = req.getRequestURI().replace("/project0/", "");
		//example of 	URI =  /avenger/{id}  --> length is 2, index[0] is ---> /avenger,  index[1] is --> /1
		
//		String[] portions = URI.split("/");
		
//		System.out.println(Arrays.toString(portions));
		
		loginController.login(req, res);
		
//		try {
//			switch(portions[0]) {
//				case "user":
//					if(req.getSession(false) != null && (boolean)req.getSession().getAttribute("loggedin")) {			
//					
//						
//						if(req.getMethod().equals("GET")) {
//							
//							if(portions.length == 2) {		//		/avenger/1   get one avenger	http://localhost:8080/HelloJackson/avenger/1
//								// this means avenger coming in with number
//								int id = Integer.parseInt(portions[1]);
//								
//								
////								avengerController.getAvenger(res, id);
//								
//							}else if(portions.length == 1){  //		/avenger  get all avenger   http://localhost:8080/HelloJackson/avenger
////								avengerController.getAllAvengers(res);
//							}
//							
//						}else if(req.getMethod().equals("POST")) {	
////							avengerController.addAvenger(req,res);									
//						}
//					}else {
//						res.setStatus(403);
//						res.getWriter().println("You must be logged in to do that");
//					}
//					break;
//					
//				case "login":
//					loginController.login(req, res);				
//					break;
//					
//				case "logout":
//					loginController.logout(req, res);
//					break;
//					
//			}// end of switch
//		}catch(NumberFormatException e) {
//			e.printStackTrace();
//			res.getWriter().print("The id you provided is not integer");
//			res.setStatus(400);
//		}
	
		
	}// end of do get
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
		
	}
	

}
