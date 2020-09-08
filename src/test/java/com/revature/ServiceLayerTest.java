package com.revature;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.daos.ReimbStatusDAO;
import com.revature.daos.ReimbTypeDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.models.LoginDTO;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.UserRoles;
import com.revature.models.Users;
import com.revature.services.LoginService;
import com.revature.services.ReimbStatusService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserRoleService;
import com.revature.services.UserService;

public class ServiceLayerTest {
	
	
	private static LoginDTO loginDto;
	private static ReimbursementDTO rDto;
	private static UserDAO uDAO = new UserDAO();
	private static ReimbursementDAO rDAO = new ReimbursementDAO();
	private static ReimbStatusDAO rsDao = new ReimbStatusDAO();
	private static ReimbTypeDAO rtDao = new ReimbTypeDAO();
	public static LoginService loginService;
	public static UserService userService;
	public static UserRoleService userRoleService;
	public static ReimbStatusService reimbStatusService;
	public static ReimbursementService reimbService;	

	public ServiceLayerTest() {
		super();
	}
	
	@BeforeClass
	public static void set() {
		System.out.println("setting before class");
		
		loginService = new LoginService();
		userService = new UserService();
		userRoleService = new UserRoleService();
		reimbStatusService = new ReimbStatusService();
		reimbService = new ReimbursementService();
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testLoginService() {
		LoginDTO ld1 = new LoginDTO("aki", "aki");
		boolean u1 = loginService.login(ld1);
		assertTrue(u1);
		
		LoginDTO ld2 = new LoginDTO("notexist", "notexist");
		boolean u2 = loginService.login(ld2);
		assertFalse(u2);
		
		LoginDTO ld3 = new LoginDTO("", "");
		boolean u3 = loginService.login(ld3);
		assertFalse(u3);
				
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testUserService() {
		assertNotNull(userService.findAllUser());
		
		Users u1 = userService.findUserByNamePW("aki", "aki");
		assertNotNull(u1);
		
		Users u2 = userService.findUserByNamePW("notexist", "notexist");
		assertNull(u2);
		
		Users u3 = userService.findUserByUserName("aki");
		assertNotNull(u3);
		
		Users u4 = userService.findUserByUserName("notexist");
		assertNull(u4);
		
		Users u5 = userService.findUserByUserId(2);
		assertNotNull(u5);
		
		Users u6 = userService.findUserByUserId(10);
		assertNull(u6);
			
		UserRoles ur1 = userRoleService.findByUserRoleId(2);
		Users u7 = new Users("amanda", "amanda", "Amanda", "Ugartecha", "junitTest@gmail.com", ur1);
		boolean success = userService.addUser(u7);
		assertTrue(success);
		
		
		
	}
	
	@Test
	public void testReimbursementService() {
		List<Reimbursement> list1 = reimbService.findAllReimb();
		assertTrue(list1 != null);
		
		Reimbursement r1 = reimbService.findByReimbId(1);
		assertNotNull(r1);
		
		Reimbursement r2 = reimbService.findByReimbId(5);
		assertNull(r2);
		
		Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		ReimbursementDTO r3 = new ReimbursementDTO(333.33, timestamp1, null, "junit testing", null, 3, 1, 2, 4); 
		boolean goodReimb = reimbService.addReimbursement(r3);
		assertTrue(goodReimb);
		
		
		int reimbId= 2;
		Reimbursement reimb = reimbService.findByReimbId(reimbId); 
		int status= 2; //denied
		ReimbStatus rStatus = new ReimbStatus(2, "Denied");		
		int resolverId = 1;
		Users resolver= userService.findUserByUserId(resolverId);		
		reimb.setReimbStatusFK(rStatus);
		reimb.setReimbResolved(new Timestamp(System.currentTimeMillis()));
		reimb.setReimbResolver(resolver);
		boolean goodUpdate = reimbService.updateReimbursement(reimb);
		assertTrue(goodUpdate);
		
		
		Users u1 = userService.findUserByUserId(2);
		List<Reimbursement> list2 = reimbService.findReimbursementByUserId(u1);
		assertTrue(list2 != null);		
		
	}
	
	@Test(expected = AssertionError.class)
	public void testUserRoleService() {
		UserRoles u1 = userRoleService.findByUserRoleId(1);
		assertNotNull(u1);
		
		UserRoles u2 = userRoleService.findByUserRoleId(3);
		assertNull(u2);
		
		UserRoles u3 = userRoleService.findUserRoleByUserId(1);
		assertNotNull(u3);
		
		UserRoles u4 = userRoleService.findUserRoleByUserId(4);
		assertNull(u4);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testReimbStatusService() {
		ReimbStatus u1 = reimbStatusService.findReimbStatus("Approved");
		assertNotNull(u1);
		
		ReimbStatus u2 = reimbStatusService.findReimbStatus("notexist");
		assertNull(u2);
		
		ReimbStatus u3 = reimbStatusService.findReimbStatusById(1);
		assertNotNull(u3);
		
		ReimbStatus u4 = reimbStatusService.findReimbStatusById(5);
		assertNull(u4);
	}
		
	
}

