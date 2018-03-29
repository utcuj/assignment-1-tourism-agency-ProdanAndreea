package business.controller;

import java.util.ArrayList;

import business.model.Client;
import business.model.Reservation;
import business.services.ClientService;
import business.services.HistoryService;
import business.services.ReservationService;
import business.services.UserService;
import persistence.dao.ClientDAO;

public class AgentController {
	
	private ClientService clientservice;
	private HistoryService historyservice;
	private UserService userservice;
	private ReservationService reservservice;
	private final static String changeInsertClientMessage = "insert new client";
	private final static String changeAddReservationMessage = "add new reservation";
	private final static String changeUpdateClientMessage = "updated client";
	
	public AgentController(UserService userservice) {
		clientservice = new ClientService();
		historyservice = new HistoryService();
		this.userservice = userservice;
		reservservice = new ReservationService();
	}
	
	public void insertClient(String name, String card_no, String cnp, String address, String phone, String email) {
		clientservice.insertClient(name, card_no, cnp, address, phone, email);
		historyservice.insertHistory(userservice.getUserId(), changeInsertClientMessage);
	}
	
	public void updateClient(String id, String name, String card_no, String cnp, String address, String phone, String email) {
		clientservice.updateClient(id, name, card_no, cnp, address, phone, email);
		historyservice.insertHistory(userservice.getUserId(), changeUpdateClientMessage);
	}
	
	///////
	public void addReservation(String id_client, String destination, String hotel, String no_persons, String details, String tot_price, String final_payment_date) {
		reservservice.addReservation(id_client, destination, hotel, no_persons, details, tot_price, final_payment_date);
		historyservice.insertHistory(userservice.getUserId(), changeAddReservationMessage);
	}
	

	public ArrayList<Reservation> getReservations(String id_client) {
		return reservservice.getReservations(id_client); 
	}
	
	
	public ArrayList<Client> getAllClients() { 
		return ClientDAO.getAllClients();
	}
	
	public ArrayList<Reservation> getMissedPayments() {
		return reservservice.getMissedPayments();
	}
	
	public void makePayment(String id_reserv, String sum) {
		reservservice.makePayment(id_reserv, sum);
	}
	
	public void cancelReservation(String id_reserv) {
		reservservice.cancelReservation(id_reserv);
	}
	
	public void deleteReservation(String id_reserv) {
		reservservice.deleteReservation(id_reserv);
	}
	
	
}
