package business.validators;

import javax.swing.JOptionPane;

import business.model.Client;

public class CNPValidator implements Validator<Client> {
		
		public void validate(Client client) {
				
				String message;
				
				if(!client.GetCnp().matches("^[0-9]{13}$")) { // match numbers	
					message = "Wrong PNC number format";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
	
			}
}



