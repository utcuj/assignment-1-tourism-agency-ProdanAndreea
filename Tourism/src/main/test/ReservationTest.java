package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import business.model.Reservation;
import business.services.ReservationService;

public class ReservationTest {
	ReservationService rs = new ReservationService();
	
	
	@Test
	public void testInsertGet() {
		rs.addReservation("1", "Japan", "Holly", "1", "", "234.2", "2018-06-04");

		// view all reservations for a client 
		ArrayList<Reservation> reservations = rs.getReservations("3");
		for (Reservation res: reservations) {
			System.out.println(res.getDestination());
		} 
	}
	
	@Test
	public void missedPayment() {
		ArrayList<Reservation> reservations = rs.getMissedPayments();
		for (Reservation res: reservations) {
			System.out.println(res.getIdClient());
		}
	}
	
	//cancel a client's holiday
	@Test
	public void testCancel() {
		rs.cancelReservation("4");
	}
	
	
	// Accept partial payments from a client before final payment date 
	@Test
	public void testMakePayment() {
		rs.makePayment("4", "100");
	}
	
	// Delete a reservation
	@Test
	public void testDeleteReserv() {		
		rs.deleteReservation("1");
	}
}
