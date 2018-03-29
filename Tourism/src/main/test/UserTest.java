package test;

import static org.junit.Assert.*;
import org.junit.Test;

import business.services.UserService;


public class UserTest {

	UserService us = new UserService();
	
	@Test
	public void test() {
		us.signUp("alex", "pass", "Alex Dat", "AGENT");
		
		// sign up and get the user's type 
		System.out.println(us.signIn("alex", "pass"));
		assertEquals("AGENT", us.signIn("alex", "pass"));		
	}
	
	
	
	

}
