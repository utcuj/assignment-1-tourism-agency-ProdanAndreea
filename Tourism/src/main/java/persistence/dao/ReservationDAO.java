package persistence.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.model.Reservation;
import persistence.connection.ConnectionFactory;

public class ReservationDAO {
	
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO reservation (id_client, destination, hotel, no_persons, details, tot_price, final_payment_day)" + 
														" VALUES (?,?,?,?,?,?,?)";  			
	private final static String findReservationsStatementString = "SELECT * FROM reservation where id_client = ?";
	private final static String findMissedPaymentsString = "SELECT * FROM reservation WHERE final_payment_day < CURDATE()";
	private final static String cancelReservationStatement = "UPDATE reservation SET canceled = ? WHERE id_reserv = ?";
	private final static String deleteResrvationStatement = "DELETE FROM reservation WHERE id_reserv = ?";
	private final static String updatePaymentString = "UPDATE reservation SET tot_price = ? WHERE id_reserv = ?";
	private final static String findReservationByIdString = "SELECT * FROM reservation WHERE id_reserv = ?";
	
	public static int insert(Reservation reservation) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, reservation.getIdClient());
			insertStatement.setString(2, reservation.getDestination());
			insertStatement.setString(3, reservation.getHotel());
			insertStatement.setInt(4, reservation.getNoPersons());
			insertStatement.setString(5, reservation.getDetails());
			insertStatement.setFloat(6, reservation.getPrice());
			insertStatement.setDate(7, reservation.getPaymentDate());
			
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ReservationDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	
	public static ArrayList<Reservation> getReservations(int id_client) { 	
		ArrayList<Reservation> toReturn = new ArrayList<Reservation>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findReservationsStatementString);
			findStatement.setInt(1, id_client); 
			rs = findStatement.executeQuery();
			while (rs.next()) { 
				int id_reserv = rs.getInt("id_reserv");
				String destination = rs.getString("destination");
				String hotel = rs.getString("hotel");
				int no_persons = rs.getInt("no_persons");
				String details = rs.getString("details");
				float tot_price = rs.getFloat("tot_price");
				Date final_payment_day = rs.getDate("final_payment_day");  
				boolean canceled = rs.getBoolean("canceled");

				toReturn.add(new Reservation(id_reserv, destination, hotel, no_persons, details, tot_price, final_payment_day, canceled));
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

	
	public static ArrayList<Reservation> getMissedPayments() {
		ArrayList<Reservation> toReturn = new ArrayList<Reservation>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findMissedPaymentsString);
			rs = findStatement.executeQuery();
			while (rs.next()) { 
				int id_client = rs.getInt("id_client");
				String destination = rs.getString("destination");
				String hotel = rs.getString("hotel");
				float tot_price = rs.getFloat("tot_price");
				Date final_payment_day = rs.getDate("final_payment_day");
		
				toReturn.add(new Reservation(id_client, destination, hotel, tot_price, final_payment_day));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ReservationDAO:getMissedPayments " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;	
	}
	
	
	public static void deleteReservation(int id_reserv) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(deleteResrvationStatement);
			insertStatement.setInt(1, id_reserv);
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ReservationDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	
	public static void cancelReservation(int id_reserv) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(cancelReservationStatement);
			insertStatement.setBoolean(1, true);
			insertStatement.setInt(2, id_reserv);		
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ReservationDAO:cancelReservation " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	
	public static void updatePayment(int id_reserv, float sum) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(updatePaymentString);
			insertStatement.setFloat(1, sum);
			insertStatement.setInt(2, id_reserv);		
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ReservationDAO:cancelReservation " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	
	public static Reservation findReservationById(int id_reserv) {
		Reservation toReturn = null;
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findReservationByIdString);
			findStatement.setInt(1, id_reserv);
			rs = findStatement.executeQuery();
			while (rs.next()) { 
				int id_client = rs.getInt("id_client");
				String destination = rs.getString("destination");
				String hotel = rs.getString("hotel");
				int no_persons = rs.getInt("no_persons");
				String details = rs.getString("details");
				float tot_price = rs.getFloat("tot_price");
				Date final_payment_day = rs.getDate("final_payment_day");

				toReturn = new Reservation(id_client, destination, hotel, no_persons, details, tot_price, final_payment_day);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ReservationDAO:findReservationById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;	
	}


}
