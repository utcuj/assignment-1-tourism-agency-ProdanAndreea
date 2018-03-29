package business.services;

import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import business.model.History;
import business.validators.DateValidator;
import business.validators.NumberValidator;
import business.validators.Validator;
import persistence.dao.HistoryDAO;


public class HistoryService {

			public History findHistorybyId(int id_history) {
				History history = HistoryDAO.findByID(id_history);
				if (history == null) {
					String message = "Histories fo the user with id =" + id_history + " don't exist!";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
					//throw new NoSuchElementException("The client with name =" + name + " was not found!");
				}
				return history;
			}

			// insert new activity performed by an agent into history
			public int insertHistory(int id_user, String change) {
				History history = new History(id_user, change);
				
				return HistoryDAO.insert(history);
			}
			
			
			// find all the activities performed by an agent, for a particular period
			public ArrayList<History> findActivities(String id_user, String from, String to) {
				Validator<String> validator;
				try {
					validator = new NumberValidator();
					validator.validate(id_user);
					validator = new DateValidator();
					validator.validate(from);
					validator.validate(to); 
					
					int id = Integer.parseInt(id_user);
					Date fromtime = Date.valueOf(from);
					Date totime = Date.valueOf(to);
					
					return HistoryDAO.findActivities(id, fromtime, totime);
				} catch(Exception e){}
				
				return null;
			}

		
}
