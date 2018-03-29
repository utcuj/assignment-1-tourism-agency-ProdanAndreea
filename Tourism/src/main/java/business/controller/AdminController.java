package business.controller;

import java.util.ArrayList;

import business.model.History;
import business.model.User;
import business.services.AgentService;
import business.services.HistoryService;

public class AdminController {
	
	private HistoryService historyservice;
	private AgentService agentservice;
	
	public AdminController() {
		historyservice = new HistoryService();
		agentservice = new AgentService();
	}

	
	public ArrayList<User> getAgents()  {
		return agentservice.getAgents();
	}
	
	// find all the activities performed by an agent, for a particular period
	public ArrayList<History> findActivities(String id_user, String from, String to) {
		return historyservice.findActivities(id_user, from, to);
	}	
}
