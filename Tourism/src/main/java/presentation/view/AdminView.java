package presentation.view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import business.controller.AdminController;
import business.model.History;
import business.model.User;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminView {

	private JFrame frame;
	private JTable table;
	private JTable table_2;
	private JTextField textFieldId;
	private JTextField textFieldFrom;
	private JTextField textFieldTo;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_2;
	
	private AdminController admincontroller;
	private DefaultTableModel tableModel;
	private String col_table[]= {"id_user", "username", "password", "name"};
	private DefaultTableModel tableModel_2;
	private String col_table_2[]= {"change", "date"};


	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView window = new AdminView();
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
	public AdminView() {
		admincontroller = new AdminController();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 827, 378);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 21, 488, 295);
		frame.getContentPane().add(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 21, 488, 122);
		frame.getContentPane().add(scrollPane);
		tableModel = new DefaultTableModel(col_table, 0);
		
		JLabel lblAgents = new JLabel("Agents");
		lblAgents.setBounds(10, 0, 46, 14);
		frame.getContentPane().add(lblAgents);
		
		JLabel lblId = new JLabel("User Id");
		lblId.setBounds(551, 186, 58, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(551, 217, 58, 14);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(551, 250, 58, 14);
		frame.getContentPane().add(lblTo);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(607, 183, 173, 20);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldFrom = new JTextField();
		textFieldFrom.setBounds(607, 217, 173, 20);
		frame.getContentPane().add(textFieldFrom);
		textFieldFrom.setColumns(10);
		
		textFieldTo = new JTextField();
		textFieldTo.setBounds(607, 247, 173, 20);
		frame.getContentPane().add(textFieldTo);
		textFieldTo.setColumns(10);
		
		JButton btnShow = new JButton("Show");
		btnShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getHistory();
			}
		});
		btnShow.setBounds(607, 294, 89, 23);
		frame.getContentPane().add(btnShow);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getAgents();
			}
		});
		btnShowAll.setBounds(607, 120, 104, 23);
		frame.getContentPane().add(btnShowAll);
		
		table_2 = new JTable();
		table_2.setBounds(10, 176, 486, 153);
		frame.getContentPane().add(table_2);
		scrollPane_2 = new JScrollPane(table_2);
		scrollPane_2.setBounds(10, 176, 486, 153);
		frame.getContentPane().add(scrollPane_2);
		tableModel_2 = new DefaultTableModel(col_table_2, 0);
		
		JLabel lblHistory = new JLabel("History");
		lblHistory.setBounds(10, 151, 46, 14);
		frame.getContentPane().add(lblHistory);
	}
	
	
	private void getAgents() {
		ArrayList<User> agents = admincontroller.getAgents();

		tableModel.setRowCount(0);	 // delete the old rows	
		table.setModel(tableModel);

		for (int i = 0; i < agents.size(); i++){
			   String id_user = Integer.toString(agents.get(i).getId());
			   String username = agents.get(i).getUsername();
			   String password = agents.get(i).getPassword();
			   String name = agents.get(i).getName();
			   
			   Object[] data = {id_user, username, password, name};
			   
			   tableModel.addRow(data);					   
		}		
	}
	
	
	private void getHistory() {
		String id_user = textFieldId.getText();
		String from = textFieldFrom.getText();
		String to = textFieldTo.getText();
		
		ArrayList<History> hist = admincontroller.findActivities(id_user, from, to);
		
		tableModel_2.setRowCount(0);	 // delete the old rows	
		table_2.setModel(tableModel_2);

		for (int i = 0; i < hist.size(); i++) {
			   String change = hist.get(i).getChange();
			   String date = hist.get(i).getDate().toString();
			   
			   Object[] data = {change, date};
			   
			   tableModel_2.addRow(data);					   
		}		
		
		
	}
}
