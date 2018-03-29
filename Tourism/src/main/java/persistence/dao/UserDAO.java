package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.model.User;
import persistence.connection.ConnectionFactory;

public class UserDAO {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO user (username, password, name, type)" + " VALUES (?,?,?,?)";  			
	private final static String findStatementString = "SELECT * FROM user where username = ? AND password = ?";
	private final static String getAgentsStatementString = "SELECT * FROM user where type = 'AGENT'";
	 
	public static int insert(User user) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, user.getUsername());
			insertStatement.setString(2, user.getPassword());
			insertStatement.setString(3, user.getName());
			insertStatement.setString(4, user.getType());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	
	public static User findUser(String username, String password) {	
		User toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setString(1, username);
			findStatement.setString(2, password);
			rs = findStatement.executeQuery();
			if (rs.next()) { // move the cursor to the first row
				int id_user = rs.getInt("id_user");
				String name = rs.getString("name");
				toReturn = new User(id_user, username, password, name);

				String type = rs.getString("type"); 
				toReturn.setType(type);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"UserDAO:findUser " + e.getMessage());
		} finally { // orice s-ar intampla inchide la final tabelul cu rezultate, statement-ul si conexiunea
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static ArrayList<User> getAgents() {
		ArrayList<User> toReturn = new ArrayList<User>();
	
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(getAgentsStatementString);
			rs = findStatement.executeQuery();
			while (rs.next()) { 
				int id_user = rs.getInt("id_user");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");

				toReturn.add(new User(id_user, username, password, name));
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
