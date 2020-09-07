package com.revature;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.LoginDTO;
import com.revature.models.ReimbStatus;
import com.revature.models.UserRoles;
import com.revature.models.Users;
import com.revature.services.LoginService;
import com.revature.services.ReimbStatusService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserRoleService;
import com.revature.services.UserService;

public class ServiceLayerTest {
	
	public static LoginDTO loginDto;
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

