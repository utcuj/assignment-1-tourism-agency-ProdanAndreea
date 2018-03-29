package business.validators;

import javax.swing.JOptionPane;

public class DateValidator implements Validator<String> {
	
	public void validate(String date) {
		if (!date.matches("^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$")) { // match numbers	
					String message = "Wrong date format";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
	}

}
