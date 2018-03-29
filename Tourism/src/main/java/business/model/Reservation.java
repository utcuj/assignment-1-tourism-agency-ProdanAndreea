package business.model;

import java.sql.Date;
import java.util.Calendar;

// destination, hotel name, number of persons who are going on holiday, 
// details about each member going on holiday, total price, final payment date

// DATE - format YYYY-MM-DD

public class Reservation {
	
	private int id_reserv;
	private int id_client;
	private String destination;
	private String hotel;
	private int no_persons;
	private String details;
	private float tot_price;
	private Date final_payment_date;
	private boolean canceled; // true if the holiday is canceled
	
	
	public Reservation(int id_reserv, String destination, String hotel, int no_persons, String details, float tot_price, Date final_payment_date, boolean canceled) {
		this.id_reserv = id_reserv;
		this.destination = destination;
		this.hotel = hotel;
		this.no_persons = no_persons;
		this.details = details;
		this.tot_price = tot_price;
		this.final_payment_date = final_payment_date;
		this.canceled = canceled;
	}
	
	public Reservation(int id_client, String destination, String hotel, int no_persons, String details, float tot_price, Date final_payment_date) {
		this.id_client = id_client;
		this.destination = destination;
		this.hotel = hotel;
		this.no_persons = no_persons;
		this.details = details;
		this.tot_price = tot_price;
		this.final_payment_date = final_payment_date;
	}
	
	public Reservation(int id_client, String destination, String hotel, float tot_price, Date final_payment_date) {
		this.id_client = id_client;
		this.destination = destination;
		this.hotel = hotel;
		this.tot_price = tot_price;
		this.final_payment_date = final_payment_date;
	}
	
	public int getIdReserv() { 
		return id_reserv;
	}
	
	public int getIdClient() {
		return id_client;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public String getHotel() {
		return hotel;
	}
	
	public int getNoPersons() {
		return no_persons;
	}
	
	public String getDetails() {
		return details;
	}
	
	
	public float getPrice() {
		return tot_price;
	}
	
	public Date getPaymentDate() {
		return final_payment_date;
	}
	
	public String getCanceled() {
		if (canceled == false) {
			return "Y";
		} else return "N";
	}

	// make the payment
	public void partialPayment(float sum) {
		tot_price -= sum;
	}
	
	/////
	public boolean isCanceld() {
		return canceled;
	}
	
	public void setCanceld() {
		canceled = true;
	}
	
	public boolean missedPayment() {
		if (tot_price > 0 || final_payment_date.before(new java.sql.Date(Calendar.getInstance().getTime().getTime()))) {
			return false;
		}
		return true;
	}
	















}
