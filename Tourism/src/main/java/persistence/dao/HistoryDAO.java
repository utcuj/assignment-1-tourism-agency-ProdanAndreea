package persistence.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.model.History;
import persistence.connection.ConnectionFactory;

public class HistoryDAO {

	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO history (id_user, `change`)" + " VALUES (?,?)";  			
	private final static String findByIDStatementString = "SELECT * FROM history where id_user = ?";
	private final static String findAgentActivitiesStatement = "SELECT `change`, `date` FROM history WHERE DATE(`date`) BETWEEN ? and ? AND id_user = ?";
	

	 
	public static int insert(History history) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, history.getIdUser());
			insertStatement.setString(2, history.getChange());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "HistoryDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}


	
	public static History findByID(int id) {	
		History toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findByIDStatementString);
			findStatement.setInt(1, id); 
			rs = findStatement.executeQuery();
			if (rs.next()) {
				int id_history = rs.getInt("id_history");
				int id_user = rs.getInt("id_user");
				String change = rs.getString("change");
				Timestamp date = rs.getTimestamp("date");
				toReturn = new History(id_history, id_user, change, date);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"HistoryDAO:findByID " + "(id " + id + ") " + e.getMessage());
		} finally { // orice s-ar intampla inchide la final tabelul cu rezultate, statement-ul si conexiunea
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	
	public static ArrayList<History> findActivities(int id_user, Date from, Date to) {	
		ArrayList<History> toReturn = new ArrayList<History>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAgentActivitiesStatement);
			findStatement.setDate(1, from);
			findStatement.setDate(2, to);
			findStatement.setInt(3, id_user); 
			rs = findStatement.executeQuery();
			while (rs.next()) { 
				String change = rs.getString("change");
				Timestamp date = rs.getTimestamp("date");
				
				toReturn.add(new History(change, date));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"HistoryDAO:findActivities " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
}
