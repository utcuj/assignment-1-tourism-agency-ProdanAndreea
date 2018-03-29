package presentation.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import business.services.UserService;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignIn {

	private JFrame frame;
	private JTextField textFieldPass;
	
	
	private UserService userservice;
	private AgentView agentview;
	private AdminView adminview;
	private JTextField textFieldUsername;
	private JLabel lblMessage;
	
	private final static String errorMessage = "Wrong username or password";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn window = new SignIn();
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
	public SignIn() {
		userservice = new UserService();
		agentview = new AgentView(userservice);
		adminview = new AdminView();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(73, 34, 60, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(73, 89, 88, 14);
		frame.getContentPane().add(lblPassword);
		
		textFieldPass = new JTextField();
		textFieldPass.setBounds(171, 86, 136, 20);
		frame.getContentPane().add(textFieldPass);
		textFieldPass.setColumns(10);
		
		
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(171, 31, 136, 20);
		frame.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		lblMessage = new JLabel("");
		lblMessage.setForeground(new Color(255, 0, 0));
		lblMessage.setBounds(171, 128, 136, 14);
		frame.getContentPane().add(lblMessage);
		
		JButton btnSignIn = new JButton("Sign In");
		textFieldPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
				      signIn();
				   }
			}
		});
		btnSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {							
				signIn();
			}
		});
		
		
		btnSignIn.setBackground(new Color(255, 255, 255));
		btnSignIn.setForeground(new Color(0, 0, 0));
		btnSignIn.setBounds(171, 166, 89, 23);
		frame.getContentPane().add(btnSignIn);
	
	}
	
	
	
	
	public void signIn() {
		String username = textFieldUsername.getText();
		String password = textFieldPass.getText();

		String type = userservice.signIn(username, password);
		if (type != null) { // user found
			// close this window and open the user's specific window					
			if (type.equals("AGENT")) {
				agentview.start(userservice);
				frame.dispose();
				
			} else if (type.equals("ADMIN")) {
				adminview.start();
				frame.dispose();
			}
			
		} else {
			lblMessage.setText(errorMessage);
		}
	}
}
