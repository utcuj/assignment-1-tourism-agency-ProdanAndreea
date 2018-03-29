package business.services;

import java.sql.Date;
import java.util.ArrayList;

import business.model.Reservation;
import business.validators.DateValidator;
import business.validators.NumberValidator;
import business.validators.Validator;
import persistence.dao.ReservationDAO;

public class ReservationService {	
	
	// add reservation for a client
	public void addReservation(String id_client, String destination, String hotel, String no_persons, String details, String tot_price, String final_payment_date) {
		Validator<String> validator;
		try {
			validator = new NumberValidator();
			validator.validate(id_client);
			Integer id = Integer.parseInt(id_client);
			validator.validate(no_persons);
			Integer n_persons = Integer.parseInt(no_persons);
			validator.validate(tot_price);
			Float tot = Float.parseFloat(tot_price);
			
			validator = new DateValidator();
			validator.validate(final_payment_date);
			Date f_payment_date = Date.valueOf(final_payment_date);
			
			Reservation reservation = new Reservation(id, destination, hotel, n_persons, details, tot, f_payment_date);
			ReservationDAO.insert(reservation);
		} catch(Exception e){}
	}
	
	// view reservations for a client 
	
	public ArrayList<Reservation> getReservations(String id_client) {
		Validator<String> validator =  new NumberValidator();
		validator.validate(id_client);
		Integer id = Integer.parseInt(id_client);  
		ArrayList<Reservation> rs = ReservationDAO.getReservations(id);
		for(int i=0; i<rs.size(); i++) {
			System.out.println(rs.get(i).getIdReserv());
		}
			
		return ReservationDAO.getReservations(id);
	}
	
	// delete a reservation for a client
	public void deleteReservation(String id_reserv) {
		Validator<String> validator =  new NumberValidator();
		validator.validate(id_reserv);
		Integer id = Integer.parseInt(id_reserv);
		
		ReservationDAO.deleteReservation(id);
	}
	
	// View all the clients who missed the final payment deadline 
	public ArrayList<Reservation> getMissedPayments() {
		return ReservationDAO.getMissedPayments();
	}
	
	// cancel a client's holiday
	public void cancelReservation(String id_reserv) {
		Validator<String> validator =  new NumberValidator();
		validator.validate(id_reserv);
		Integer id = Integer.parseInt(id_reserv);
		
		ReservationDAO.cancelReservation(id);
	}
	
	// Accept partial payments from a client before final payment date 
	public void makePayment(String id_reserv, String sum) {
		// validate data
		Validator<String> validator =  new NumberValidator();
		validator.validate(id_reserv);
		Integer id = Integer.parseInt(id_reserv);
		validator.validate(sum);
		float p_sum = Integer.parseInt(sum);
		
		// get the reservation data
		Reservation reservation = ReservationDAO.findReservationById(id);

		// calculate the remained payment
		reservation.partialPayment(p_sum);
		
		// update data
		ReservationDAO.updatePayment(id, reservation.getPrice());
	}
	
	
	

}
