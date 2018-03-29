package business.validators;

import javax.swing.JOptionPane;

public class ShortValidator implements Validator<String> {
	
	public void validate(String number) {
		String message;
		
		if (!number.matches("^[0-9]+$")) { // if it doesn't match the pattern
			message = "Input not a number or too long";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException(message);
		}
		
		try {
			Short.parseShort(number);
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Input not a number or too long", "Error", JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	

}
