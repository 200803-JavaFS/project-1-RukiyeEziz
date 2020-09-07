package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

public class LoginController {
	
	private static LoginService loginService = new LoginService();
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	private static UserService userService = new UserService();
	private static UserRoleService userRoleService = new UserRoleService();
	
	public void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println(" 1 -- In LoginController request.getparam: " + req.getParameterMap().containsKey("username"));
		
		LoginDTO loginDto = new LoginDTO();	
		
		// getting info from index.html
		String userName = req.getParameter("login_username");
		String password = req.getParameter("login_pw");
		
		loginDto.username = userName;
		loginDto.password = password;
		
		System.out.println(" 2 -- In LoginController loginDTO.name;  " + loginDto.username + " " + loginDto.password);
		System.out.println(" 3 -- In LoginController request.getContent: " + req.getContentType() );// .getParameterMap().containsKey("username"));

		RequestDispatcher rd = null;
		//PrintWriter out = res.getWriter();		
		
		if(req.getMethod().equals("GET")) {		// GET		
			
			if(loginService.login(loginDto)) {
				
				HttpSession ses = req.getSession();
				
				ses.setAttribute("user", loginDto);				
				ses.setAttribute("loggedin", true);
				
				res.setStatus(200);				
				res.getWriter().println(" 4 -- Login Controller if GET: - Login Successful");
				
			} else {
				
				HttpSession ses = req.getSession(false);
				
				if(ses != null) {
					
					ses.invalidate();
				}
				
				res.setStatus(401);				
				res.getWriter().println(" 5 -- Login Controller if GET: - Login failed");
			}
			
		}
		else if(req.getMethod().equals("POST")) { 		// POST		what if i omitted GET method
			
			BufferedReader reader = req.getReader();
			StringBuilder stringBuilder = new StringBuilder();
			String line = reader.readLine();
			
			while (line != null) {
				
				stringBuilder.append(line);
				line=reader.readLine();
			}
			
			String body = new String(stringBuilder);
			System.out.println(" 6 -- In LoginController POST req.getReader body: " + body);
			
			loginDto = objectMapper.readValue(body, LoginDTO.class);
			
			//Users user = objectMapper.readValue(body, Users.class);
			System.out.println(" 7 -- In LoginController object mapper: " + loginDto.toString());
			System.out.println("user service.login " + loginService.login(loginDto));
			

			
			if(loginService.login(loginDto)) {
				
				HttpSession session = req.getSession();
				System.out.println(" 8 -- Login Controller POST session - You logged in, success!" + session);
				
				session.setAttribute("user", loginDto);
				session.setAttribute("loggedin", true);
				
				System.out.println(" 9 -- Login Controller after setAttribute session - You logged in, success!" + session);
				
				res.setStatus(200);
				res.getWriter().println(" 10 -- Login Controller POST- Login Successful");
				System.out.println("after 2200 status" + res.getStatus());
				
			}
			else {
				HttpSession session = req.getSession(false);
				
				if(session != null) {
					session.invalidate();
				}
				
				res.setStatus(401);
				res.getWriter().println(" 11 -- Login Failed.");
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
			System.out.println("Login Controller logout - logged out success! " + loginDto);
			
		}else {
			res.setStatus(401);
			res.getWriter().println("You must be logged in to logout");
		}
	
		
	}
	

	

}
