//package com.revature.web;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.revature.controllers.LoginController;
//import com.revature.controllers.UserController;
//import com.revature.services.UserService;
//
//public class MasterServlet extends HttpServlet {
//	
//	private static LoginController lc = new LoginController();
//	private static UserController uc = new UserController();
//	private static UserService us = new UserService();
//	
//	public MasterServlet() {
//		super();
//	}
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		
//		res.setContentType("application/json");
//		res.setStatus(404);
//		System.out.println("In Master Servlet...");
//		
//		final String URI = req.getRequestURI().replace("/project1/", "");
//		String[] portions = URI.split("/");		
//		System.out.println("In Master Servlet portions: " + Arrays.toString(portions));
//		
//		if(portions.length == 0) {
//			req.getRequestDispatcher("index.html").forward(req, res);;
//		}
//		
//		try {
//			switch(portions[0]) {
////				case "user":
////					if(req.getSession(false) != null && (boolean)req.getSession().getAttribute("loggedin")) {			
////					
////						
////						if(req.getMethod().equals("GET")) {
////							
////							if(portions.length == 2) {		//		/avenger/1   get one avenger	http://localhost:8080/HelloJackson/avenger/1
////								// this means avenger coming in with number
////								int id = Integer.parseInt(portions[1]);
////								
////								
//////								avengerController.getAvenger(res, id);
////								
////							}else if(portions.length == 1){  //		/avenger  get all avenger   http://localhost:8080/HelloJackson/avenger
//////								avengerController.getAllAvengers(res);
////							}
////							
////						}else if(req.getMethod().equals("POST")) {	
//////							avengerController.addAvenger(req,res);									
////						}
////					}else {
////						res.setStatus(403);
////						res.getWriter().println("You must be logged in to do that");
////					}
////					break;
////					
//				case "login":
//					lc.login(req, res);				
//					break;
//					
//				case "logout":
//					lc.logout(req, res);
//					break;
//					
//			}// end of switch
//		}catch(NumberFormatException e) {
//			e.printStackTrace();
//			res.getWriter().print("The id you provided is not integer");
//			res.setStatus(400);
//		}
//		
//		
//		
//	}
//		
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doGet(req, res);
//		
//	}
//}
