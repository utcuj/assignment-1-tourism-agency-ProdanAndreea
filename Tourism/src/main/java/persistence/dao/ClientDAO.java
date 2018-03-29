package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.model.Client;
import persistence.connection.ConnectionFactory;

public class ClientDAO {

	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO client (name, card_no, cnp, address, phone, email)" + " VALUES (?,?,?,?,?,?)";  			
	private final static String findByNameStatementString = "SELECT * FROM client where name = ?";
	private final static String updateStatementString = "UPDATE client SET name=?, card_no=?, cnp=?, address=?, phone=?, email=? WHERE id_client=?";
	private final static String getAllStatementString  = "SELECT * FROM client";

	/*  adauga in DB un nou obiect Student, student:
	 *  creeaza conexiunea
	 *  prepara statement-ul
	 *  executa statement-ul
	 *  ia cheia generata la inserare si returneaz-o
	 */  
	public static int insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, client.getName());
			insertStatement.setString(2, client.getCardNo());
			insertStatement.setString(3, client.GetCnp());
			insertStatement.setString(4, client.getAddress());
			insertStatement.setString(5, client.getPhone());
			insertStatement.setString(6, client.getEmail());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	
	public static Client findByName(String name) {	
		Client toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findByNameStatementString);
			//System.out.println(id);
			findStatement.setString(1, name); 
			rs = findStatement.executeQuery();
			if (rs.next()) {
				int id_client = rs.getInt("id_client");
				String card_no = rs.getString("card_no");
				String cnp = rs.getString("cnp");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				toReturn = new Client(id_client, name, card_no, cnp, address, phone, email);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findByName " + e.getMessage());
		} finally { // orice s-ar intampla inchide la final tabelul cu rezultate, statement-ul si conexiunea
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	
	public static void update(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updatetStatement = null;
	
		try {
			updatetStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			updatetStatement.setString(1, client.getName());
			updatetStatement.setString(2, client.getCardNo());
			updatetStatement.setString(3, client.GetCnp());
			updatetStatement.setString(4, client.getAddress());
			updatetStatement.setString(5, client.getPhone());
			updatetStatement.setString(6, client.getEmail());
			updatetStatement.setInt(7, client.getId());
			updatetStatement.executeUpdate();

			updatetStatement.getGeneratedKeys();
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updatetStatement);
			ConnectionFactory.close(dbConnection);
		}	
	}
	
	public static ArrayList<Client> getAllClients() { 	
		ArrayList<Client> toReturn = new ArrayList<Client>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(getAllStatementString);
			rs = findStatement.executeQuery();
			while (rs.next()) { 
				int id_client = rs.getInt("id_client");
				String card_no = rs.getString("card_no");
				String cnp = rs.getString("cnp");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String name = rs.getString("name");
				
				toReturn.add(new Client(id_client, name, card_no, cnp, address, phone, email));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ReservationDAO:getReservations " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;	
	}

	
	
	
}
