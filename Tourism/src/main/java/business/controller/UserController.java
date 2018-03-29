package business.controller;

import business.services.UserService;

public class UserController {
	
	private UserService userservice;
	
	public UserController() {
		userservice = new UserService();
	}
	
	
	
	public void signUp(String username, String password, String name, String type) {
		userservice.signUp(username, password, name, type);
	}
	
	public String signIn(String username, String password) {
		return userservice.signIn(username, password); // return the type of the user	
	}
	

}
