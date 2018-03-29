package business.services;

import java.util.ArrayList;

import business.model.User;
import persistence.dao.UserDAO;

public class AgentService {

	// insert 
	
	// get agent data
	public ArrayList<User> getAgents() {
		return UserDAO.getAgents();
	}
	
	// update
	
	// delete
	
	

}
