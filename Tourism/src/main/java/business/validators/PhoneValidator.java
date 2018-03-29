package business.validators;

import javax.swing.JOptionPane;

import business.model.Client;

public class PhoneValidator implements Validator<Client> {
	
	public void validate(Client client) {
			
			String message;
							
			if(!client.getPhone().matches("^[0-9]*$")) { // match numbers
				message = "Wrong phone number format";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				throw new IllegalArgumentException(message);
			}
			
		
		}
}
