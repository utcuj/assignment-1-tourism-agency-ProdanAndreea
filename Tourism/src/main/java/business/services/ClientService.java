package business.services;

// Client Business Logic Layer

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import business.model.Client;
import business.validators.CNPValidator;
import business.validators.EmailValidator;
import business.validators.NumberValidator;
import business.validators.PhoneValidator;
import business.validators.Validator;
import persistence.dao.ClientDAO;

public class ClientService {
		
	    // create the list with all the validators
		private List<Validator<Client>> validators;

		public ClientService() {
			validators = new ArrayList<Validator<Client>>();
			validators.add(new EmailValidator());
			validators.add(new PhoneValidator());
			validators.add(new CNPValidator());
		}

		// find a client by id 
		public Client findClientByName(String name) {
			Client client = ClientDAO.findByName(name);
			if (client == null) {
				String message = "The client " + name + " was not found!";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				//throw new NoSuchElementException("The client with name =" + name + " was not found!");
			}
			return client;
		}

		// add a new client to DB 
		public int insertClient(String name, String card_no, String cnp, String address, String phone, String email) {
			Client client = new Client(name, card_no, cnp, address, phone, email);
			try {	
					for (Validator<Client> v : validators) {
								v.validate(client);
					}
			} catch(Exception e) {}
			return ClientDAO.insert(client);
		}
		
		// updatee a client's data
		public void updateClient(String id, String name, String card_no, String cnp, String address, String phone, String email) {
			Validator<String> validator = new NumberValidator();
			validator.validate(id);
			int id_client = Integer.parseInt(id);
			Client client = new Client(id_client, name, card_no, cnp, address, phone, email);
			try {	
					for (Validator<Client> v : validators) {
								v.validate(client);
					}
			} catch(Exception e) {}
			ClientDAO.update(client);
		}
		
		
		// get all clients
		public ArrayList<Client> getAllClients() { 
			return ClientDAO.getAllClients();
		}
		
		
		
		
}
