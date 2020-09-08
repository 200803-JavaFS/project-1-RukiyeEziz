package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.Users;
import com.revature.services.ReimbStatusService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class ReimbController {
	
	private static ReimbursementService rs = new ReimbursementService();
	private static ReimbStatusService rss = new ReimbStatusService();
	private static ObjectMapper om = new ObjectMapper();
	private static UserService us = new UserService();
	
	public void getEmpReimbursement(HttpServletResponse res, int userId) throws IOException {
		
		Users user = us.findUserByUserId(userId);
		
		List<Reimbursement> reimbs = rs.findReimbursementByUserId(user);
		
		System.out.println("in reimb controller get reimb by a user: "+reimbs);
		
		if (reimbs == null) {
			
			res.setStatus(204);
			
		} else {
			
			res.setStatus(200);
			
			String json = om.writeValueAsString(reimbs);
			
			System.out.println("in reimb controller getEmpReimbs json: " + json);
			
			res.getWriter().println(json);
		}
	}
	public void getReimbursement(HttpServletResponse res, int reimbid) throws IOException {
		
		Reimbursement r = rs.findByReimbId(reimbid);
		
		System.out.println("in rc get reimb, r: "+r);
		
		if (r == null) {
			
			res.setStatus(204);
			
		} else {
			
			res.setStatus(200);
			
			String json = om.writeValueAsString(r);
			
			System.out.println("in reimb controller getReimb by reimb id json: "+json);
			
			res.getWriter().println(json);
		}
	}
	
	public void getAllReimbursements(HttpServletResponse res) throws IOException {
		
		List<Reimbursement> all = rs.findAllReimb();
		
		res.getWriter().println(om.writeValueAsString(all));
		
		res.setStatus(200);
	}
	
	public void addReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line= reader.readLine();
		
		while (line != null) {
			
			s.append(line);
			
			line = reader.readLine();								
		}
			String body = new String(s);
		
			System.out.println(body);
			
			ReimbursementDTO rdto= om.readValue(body, ReimbursementDTO.class);
			
			System.out.println("body that is taken in from java: "+ rdto);
			
			if (rs.addReimbursement(rdto)){ //.addReimbursement(rdto)) {
				
				res.setStatus(201);
				
				res.getWriter().println("Reimb was created");
				
			} else {
				res.setStatus(403);
			}
			
//			double amount= rdto.getReimbAmount();				
//			Timestamp ts= new Timestamp(System.currentTimeMillis());			
//			String description = rdto.getReimbDescription();
//			Users author = us.findUserByUserId(rdto.getReimbAuthor().getUsersId());
//			ReimbStatus rnewStatus= rss.findReimbStatus("Pending");		
//			String type = rdto.getReimbTypeFK().getReimbType();
//			System.out.println(type);
//			
//			ReimbType rt=null;
//			if (type.equals("Lodging")) {
//				rt=typeS.findByType("Lodging");
//			}else if (type.equals("Travel")) {
//				rt=typeS.findByType("Travel");
//			}else if (type.equals("Food")) {
//				rt=typeS.findByType("Food");
//			}else if (type.equals("Other")) {
//				rt=typeS.findByType("Other");
//			}
//			//create new reimbursement with constructor
//			Reimbursement addedReimb=new Reimbursement(amount, ts,null,description, author, null, rnewStatus, rt);
//			System.out.println(addedReimb);
//
//			if(rs.addReimbursement(addedReimb)) {
//				//add to database
//				res.setStatus(200);
//				res.getWriter().println("Reimbursement was created");
//			}else {
//				res.setStatus(403);
//			}
		
		}
	

	public void updateReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedReader reader = req.getReader();
		
		StringBuilder sb = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line!=null) {
			
			sb.append(line);
			
			line = reader.readLine();
		}
		
		String body = new String(sb);
		
		ReimbursementDTO rdto= om.readValue(body, ReimbursementDTO.class);
		
		System.out.println("json body that is came from java font end "+ rdto);
		
		
		int reimbId= rdto.getReimbId();
		Reimbursement reimb = rs.findByReimbId(reimbId); 
		
		int status= rdto.getReimbStatusFK(); 
		System.out.println("new status:" +status);
		
		ReimbStatus rStatus = null;
		if (status == 1) {
			rStatus= new ReimbStatus(1,"Approved");
		}else if (status == 2) {
			rStatus= new ReimbStatus(2, "Denied");
		}
		
		int resolverId= rdto.getReimbResolver();
		System.out.println("resolver id: "+ resolverId);
		
		
		reimb.setReimbStatusFK(rStatus);
		reimb.setReimbResolved(new Timestamp(System.currentTimeMillis()));
		
		Users resolver= us.findUserByUserId(resolverId);
		System.out.println("resolver: " + resolver);
		reimb.setReimbResolver(resolver);
		
		System.out.println(reimb);;
		
		if (rs.updateReimbursement(reimb)) {
			res.setStatus(201);
			res.getWriter().println("Reimbursement was updated");
		}else {
			res.setStatus(403);
		}
		
	}
	

		
}

