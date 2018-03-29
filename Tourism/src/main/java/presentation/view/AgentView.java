package presentation.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import business.controller.AgentController;
import business.model.Client;
import business.model.Reservation;
import business.services.UserService;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;

public class AgentView {

	private JFrame frame;
	private JTable table_1;
	private JTextField textFieldName;
	private JTextField textFieldCardNo;
	private JTextField textFieldPNC;
	private JTextField textFieldAddr;
	private JTextField textFieldPhone;
	private JTextField textFieldEmail;
	private JTextField textFieldId;
	private JTable table_2;
	private JTable table_3;
	private JTextField textFieldName_3;
	private JTextField textFieldSum;
	
	private DefaultTableModel table1Model;
	private String col_table1[]= {"id", "name", "card no.", "cnp", "address", "phone", "email"};
	
	private JButton btnSearch;
	private DefaultTableModel table2Model;
	private String col_table2[]= {"id", "destination", "hotel", "no. pers.", "details", "price", "final payment", "canceled"};
	
	private DefaultTableModel table3Model; 
	private String col_table3[]= {"id", "destination", "hotel", "price", "final payment"};
	
	private AgentController agentcontroller;
	
	private JButton btnShowClients;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_3;
	
	DefaultTableModel clientGrafic = new DefaultTableModel();
	private JTextField textFieldId_1; 
	private JButton btnMissedPayments;
	private JButton btnDelete;
	
	/**
	 * Launch the application.
	 */
	public void start(UserService userservice) {
		//agentcontroller = new AgentController(userservice);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgentView window = new AgentView(userservice);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AgentView(UserService userservice) {
		agentcontroller = new AgentController(userservice);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1043, 533);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(703, 57, 66, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblCardNo = new JLabel("Card No.");
		lblCardNo.setBounds(701, 91, 68, 14);
		frame.getContentPane().add(lblCardNo);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(779, 54, 162, 20);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldCardNo = new JTextField();
		textFieldCardNo.setBounds(779, 88, 162, 20);
		frame.getContentPane().add(textFieldCardNo);
		textFieldCardNo.setColumns(10);
		
		JLabel lblPnc = new JLabel("PNC");
		lblPnc.setBounds(701, 116, 68, 20);
		frame.getContentPane().add(lblPnc);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(701, 147, 68, 20);
		frame.getContentPane().add(lblAddress);
		
		textFieldPNC = new JTextField();
		textFieldPNC.setBounds(779, 116, 162, 20);
		frame.getContentPane().add(textFieldPNC);
		textFieldPNC.setColumns(10);
		
		textFieldAddr = new JTextField();
		textFieldAddr.setBounds(779, 147, 162, 20);
		frame.getContentPane().add(textFieldAddr);
		textFieldAddr.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(701, 178, 68, 14);
		frame.getContentPane().add(lblPhone);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(779, 178, 162, 20);
		frame.getContentPane().add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(701, 217, 68, 14);
		frame.getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(779, 209, 162, 20);
		frame.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		/// ADD ///
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addClient();
			}
		});
		btnAdd.setBounds(779, 248, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateClient();
			}
		});
		btnUpdate.setBounds(878, 248, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JLabel lblId = new JLabel("Id Client");
		lblId.setBounds(706, 304, 68, 14);
		frame.getContentPane().add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(784, 301, 162, 20);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		/// CLIENTS ////////////////////////////////////////////////////////////////
		table1Model = new DefaultTableModel(col_table1, 0);
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getRowData();
			}
		});
		table_1.setBounds(10, 32, 471, 99);
		frame.getContentPane().add(table_1);
		scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(10, 32, 641, 99);
		frame.getContentPane().add(scrollPane_1);
		
		
		/// RESERVATIONS ////
		table2Model = new DefaultTableModel(col_table2, 0);
		table_2 = new JTable();
		table_2.setBounds(11, 163, 470, 96);
		frame.getContentPane().add(table_2);
		scrollPane_2 = new JScrollPane(table_2);
		scrollPane_2.setBounds(11, 163, 640, 96);
		frame.getContentPane().add(scrollPane_2);
		
		
		btnSearch = new JButton("Search"); 
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getClientReservations();
			}
		});	
		btnSearch.setBounds(784, 332, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		/// MISSED PAYMENT ///
		table3Model = new DefaultTableModel(col_table3, 0);
		table_3 = new JTable();
		table_3.setBounds(10, 301, 471, 110);
		frame.getContentPane().add(table_3);
		scrollPane_3 = new JScrollPane(table_3);
		scrollPane_3.setBounds(10, 301, 641, 110);
		frame.getContentPane().add(scrollPane_3);
		
		JLabel lblReservations = new JLabel("Reservations");
		lblReservations.setBounds(10, 142, 97, 14);
		frame.getContentPane().add(lblReservations);
		
		JLabel lblMissedPayment = new JLabel("Missed payments");
		lblMissedPayment.setBounds(10, 270, 97, 20);
		frame.getContentPane().add(lblMissedPayment);
		
		JLabel lblClients = new JLabel("Clients");
		lblClients.setBounds(10, 7, 46, 14);
		frame.getContentPane().add(lblClients);
		
		JButton btnCancelReservation = new JButton("Cancel Reservation");
		btnCancelReservation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cancelReserv();
			}
		});
		btnCancelReservation.setBounds(885, 451, 132, 23);
		frame.getContentPane().add(btnCancelReservation);
		
		JLabel lblId_3 = new JLabel("Id Res.");
		lblId_3.setBounds(708, 393, 66, 14);
		frame.getContentPane().add(lblId_3);
		
		textFieldName_3 = new JTextField();
		textFieldName_3.setBounds(784, 390, 162, 20);
		frame.getContentPane().add(textFieldName_3);
		textFieldName_3.setColumns(10);
		
		JLabel lblSum = new JLabel("Sum");
		lblSum.setBounds(708, 423, 66, 14);
		frame.getContentPane().add(lblSum);
		
		textFieldSum = new JTextField();
		textFieldSum.setBounds(784, 420, 162, 20);
		frame.getContentPane().add(textFieldSum);
		textFieldSum.setColumns(10);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makePayment();
			}
		});
		btnPay.setBounds(784, 451, 83, 23);
		frame.getContentPane().add(btnPay);
		
		btnShowClients = new JButton("Show Clients");
		showAllClients();
		btnShowClients.setBounds(10, 432, 124, 23);
		frame.getContentPane().add(btnShowClients);
		
		textFieldId_1 = new JTextField();
		textFieldId_1.setBounds(779, 23, 162, 20);
		frame.getContentPane().add(textFieldId_1);
		textFieldId_1.setColumns(10);
		
		JLabel lblId_1 = new JLabel("Id");
		lblId_1.setBounds(703, 32, 66, 14);
		frame.getContentPane().add(lblId_1);
		
		btnMissedPayments = new JButton("Missed Payments");
		btnMissedPayments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getMissedPayments();
				
			}
		});
		btnMissedPayments.setBounds(163, 432, 149, 23);
		frame.getContentPane().add(btnMissedPayments);
		
		btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteReserv();
				getClientReservations();
			}
		});
		btnDelete.setBounds(680, 451, 89, 23);
		frame.getContentPane().add(btnDelete);
	}
	
	private void getClientReservations() {
		String id_client = textFieldId.getText();
		
		table2Model.setRowCount(0);	 // delete the old rows	
		table_2.setModel(table2Model);

		ArrayList<Reservation> reserv = agentcontroller.getReservations(id_client);
		
		for (int i = 0; i < reserv.size(); i++){
			   String id_reserv = Integer.toString(reserv.get(i).getIdReserv());
			   String destination = reserv.get(i).getDestination();
			   String hotel = reserv.get(i).getHotel();
			   String no_persons = Integer.toString(reserv.get(i).getNoPersons());
			   String details = reserv.get(i).getDetails();
			   String tot_price = Float.toString(reserv.get(i).getPrice());
			   String final_payment_date = reserv.get(i).getPaymentDate().toString();	
			   String canceled = reserv.get(i).getCanceled();
			   
			   Object[] data = {id_reserv, destination, hotel, no_persons, details, tot_price, final_payment_date, canceled};

			   table2Model.addRow(data);					   
		}
	}
	
	private void showAllClients() {
		btnShowClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {  
	
				table_1.setModel(table1Model);

				ArrayList<Client> clients = agentcontroller.getAllClients();
				// id_client, name, card_no, cnp, address, phone, email
				for (int i = 0; i < clients.size(); i++){
					   String id = Integer.toString(clients.get(i).getId());
					   String name = clients.get(i).getName();
					   String card_no = clients.get(i).getCardNo();
					   String cnp = clients.get(i).GetCnp();
					   String address = clients.get(i).getAddress();
					   String phone = clients.get(i).getPhone();
					   String email = clients.get(i).getEmail();
					   
					   Object[] data = {id, name, card_no, cnp, address, phone, email};  
					
					   table1Model.addRow(data);					   
				}
			}
		});	
	}
	
	
	private void addClient() {
		String name = textFieldName.getText();
		String card_no = textFieldCardNo.getText();  
		String cnp = textFieldPNC.getText();
		String address = textFieldAddr.getText();
		String phone = textFieldPhone.getText();
		String email = textFieldEmail.getText();
		
		agentcontroller.insertClient(name, card_no, cnp, address, phone, email);
		
		showAllClients();
	}
	
	
	private void getRowData() {
		// get the model from the jtable
	    DefaultTableModel model = (DefaultTableModel)table_1.getModel();

	    // get the selected row index
	    int selectedRowIndex = table_1.getSelectedRow();
	       
	    // set the selected row data into jtextfields
	    textFieldId_1.setText(model.getValueAt(selectedRowIndex, 0).toString());
	    textFieldName.setText(model.getValueAt(selectedRowIndex, 1).toString());
	    textFieldCardNo.setText(model.getValueAt(selectedRowIndex, 2).toString());
	    textFieldPNC.setText(model.getValueAt(selectedRowIndex, 3).toString());
	    textFieldAddr.setText(model.getValueAt(selectedRowIndex, 4).toString());		
	    textFieldPhone.setText(model.getValueAt(selectedRowIndex, 5).toString());
	    textFieldEmail.setText(model.getValueAt(selectedRowIndex, 6).toString()); 	
	}
	
	
	private void updateClient() {
		String id = textFieldId_1.getText();
	    String name = textFieldName.getText();
		String card_no = textFieldCardNo.getText();  
		String cnp = textFieldPNC.getText();
		String address = textFieldAddr.getText();
		String phone = textFieldPhone.getText();
		String email = textFieldEmail.getText();
	       
	    agentcontroller.updateClient(id, name, card_no, cnp, address, phone, email);  
	    
	    showAllClients();
	}

	private void getMissedPayments() {
		table_3.setModel(table3Model);

		ArrayList<Reservation> reserv = agentcontroller.getMissedPayments();  
		// id, destination, hotel, tot, f_payment_date  
		for (int i = 0; i < reserv.size(); i++){
			   String id = Integer.toString(reserv.get(i).getIdClient());
			   String destination = reserv.get(i).getDestination();
			   String hotel = reserv.get(i).getHotel();	   
			   String tot = Float.toString(reserv.get(i).getPrice());
			   String f_payment_date = reserv.get(i).getPaymentDate().toString(); 
			   
			   Object[] data = {id, destination, hotel, tot, f_payment_date};  
			
			   table3Model.addRow(data);			
		}
	}
	
	
	private void makePayment() {
		String id_reserv = textFieldName_3.getText();
		String sum = textFieldSum.getText();
		
		agentcontroller.makePayment(id_reserv, sum);	
	}


	private void cancelReserv() {
		String id_reserv = textFieldName_3.getText();
		
		agentcontroller.cancelReservation(id_reserv);
	}

	
	private void deleteReserv() {
		String id_reserv = textFieldName_3.getText();
		agentcontroller.deleteReservation(id_reserv);	
	}


}
