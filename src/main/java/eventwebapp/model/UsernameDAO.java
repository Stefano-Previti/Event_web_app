package eventwebapp.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import eventwebapp.utility.JDBCconnection;

public class UsernameDAO {

	public static void createTableUser(String username) throws Exception {
	    try (Connection conn = JDBCconnection.getConnection()) {
	        String sql = "CREATE TABLE IF NOT EXISTS " + username + " (" +
	                    "titolo VARCHAR(200) PRIMARY KEY," +
	                    "data DATE," +
	                    "luogo VARCHAR(200)" +
	                    ");";

	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	    public static void insertData(String tableName, HttpServletRequest request) throws Exception {
	        try (Connection conn = JDBCconnection.getConnection()) {
	            String insertQuery = "INSERT INTO " + tableName + " (titolo, data, luogo) VALUES (?, ?, ?)";
	            PreparedStatement stmt = conn.prepareStatement(insertQuery);

	            stmt.setString(1, request.getParameter("tit"));

	         // Converto la data da "dd/mm/yyyy" a "yyyy-mm-dd" per il formato di MySQL
	            String inputDate = request.getParameter("date");
	            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date data = new Date(inputDateFormat.parse(inputDate).getTime());
	            String mysqlFormattedDate = mysqlDateFormat.format(data);
	            stmt.setString(2, mysqlFormattedDate);
	            
	            
	            stmt.setString(3, request.getParameter("Luogo"));

	            stmt.executeUpdate();
	        } catch (SQLException | ParseException e) {
	        }
	    }
	    public static boolean doesTitleExist(String tableName, String title) throws Exception {
	        try (Connection conn = JDBCconnection.getConnection()) {
	            String selectQuery = "SELECT COUNT(*) FROM " + tableName + " WHERE titolo = ?";
	            PreparedStatement stmt = conn.prepareStatement(selectQuery);

	            stmt.setString(1, title);

	            try (ResultSet resultSet = stmt.executeQuery()) {
	                if (resultSet.next()) {
	                    int rowCount = resultSet.getInt(1);
	                    return rowCount > 0;
	                }
	            }
	        } catch (SQLException e) {
	            throw new Exception("Error checking title existence: " + e.getMessage());
	        }
	        return false;
	    }
	    public static Map<String, EventBean> extractEventData(String tableName) throws Exception {
	        Map<String, EventBean> eventMap = new LinkedHashMap<>(); 

	        try (Connection conn = JDBCconnection.getConnection()) {
	            String selectQuery = "SELECT * FROM " + tableName + " ORDER BY data ASC";
	            PreparedStatement stmt = conn.prepareStatement(selectQuery);

	            try (ResultSet resultSet = stmt.executeQuery()) {
	                while (resultSet.next()) {
	                    String luogo = resultSet.getString("luogo");
	                    // Converto la data da "yyyy-MM-dd" a "dd/MM/yyyy" per il formato richiesto
	                    Date data = resultSet.getDate("data");
	                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                    String formattedData = outputDateFormat.format(data);

	                    EventBean eventBean = new EventBean();
	                    eventBean.setData(formattedData);
	                    eventBean.setLuogo(luogo);
	                    eventMap.put(resultSet.getString("titolo"), eventBean);
	                }
	            }
	        } catch (SQLException e) {
	        }

	        return eventMap;
	    }
	    public static Map<String, EventBean> extractEventDataByTitle(String tableName,String title) throws Exception {
	        Map<String, EventBean> eventMap = new LinkedHashMap<>(); 

	        try (Connection conn = JDBCconnection.getConnection()) {
	            String selectQuery = "SELECT * FROM " + tableName + " WHERE titolo=?";
	            PreparedStatement stmt = conn.prepareStatement(selectQuery);
	 	        stmt.setString(1, title);

	            try (ResultSet resultSet = stmt.executeQuery()) {
	                while (resultSet.next()) {
	                    String luogo = resultSet.getString("luogo");
	                    // Converto la data da "yyyy-MM-dd" a "dd/MM/yyyy" per il formato richiesto
	                    Date data = resultSet.getDate("data");
	                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                    String formattedData = outputDateFormat.format(data);

	                    EventBean eventBean = new EventBean();
	                    eventBean.setData(formattedData);
	                    eventBean.setLuogo(luogo);
	                    eventMap.put(resultSet.getString("titolo"), eventBean);
	                }
	            }
	        } catch (SQLException e) {
	        }

	        return eventMap;
	    }
	    public static void deleteEventData(String tableName, String titolo) throws Exception {
	        try (Connection conn = JDBCconnection.getConnection()) {
	            String deleteQuery = "DELETE FROM " + tableName + " WHERE titolo = ?";
	            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

	            stmt.setString(1, titolo);

	            stmt.executeUpdate();
	        } catch (SQLException e) {
	        }
	    }
	    public static void deleteRowsByUsernameAndTitle(String username, String title) throws Exception {
	    	try (Connection conn = JDBCconnection.getConnection()) {
	            
	            
	            // Estraggo gli username diversi
	            String getUsersQuery = "SELECT DISTINCT username FROM utente WHERE username <> ?";
	            PreparedStatement getUsersStmt = conn.prepareStatement(getUsersQuery);
	            getUsersStmt.setString(1, username);
	            
	            List<String> usersList = new ArrayList<>();
	            try (ResultSet usersResultSet = getUsersStmt.executeQuery()) {
	                while (usersResultSet.next()) {
	                    usersList.add(usersResultSet.getString("username"));
	                }
	            }
	            
	            // Per ogni username, eseguo l'eliminazione se la tabella esiste
	            for (String otherUsername : usersList) {
	            	// Controllo se la tabella con lo username esiste
		            String tableCheckQuery = "SHOW TABLES LIKE ?";
		            PreparedStatement tableCheckStmt = conn.prepareStatement(tableCheckQuery);
		            tableCheckStmt.setString(1, otherUsername);
		            
		            try (ResultSet resultSet = tableCheckStmt.executeQuery()) {
		                if (resultSet.next()) {
		                    // Se la tabella esiste, elimino la riga con il titolo corrispondente
		                    String deleteQuery = "DELETE FROM " + otherUsername + " WHERE titolo = ?";
		                    PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
		                    deleteStmt.setString(1, title);
		                    deleteStmt.executeUpdate();
		                }
		            }
	            }
	        } catch (SQLException e) {
	        }
	    }
	}
	







