package business.services;


import business.model.User;
import persistence.dao.UserDAO;

public class UserService {
	private User user;
	
	// sign up
	public void signUp(String username, String password, String name, String type) {
		user = new User(username, password, name, type);
		
		UserDAO.insert(user);
	}
	
	// sign in, save the user's data for further use and return its type
	public String signIn(String username, String password) {
		System.out.println("signIn");
		user = UserDAO.findUser(username, password);
		
		if (user != null) {
			return user.getType();
		}
		return null;
	}
	
	public int getUserId() {
		if (user==null) {
			System.out.println("VVV");
		}
		return user.getId();
	}

}
