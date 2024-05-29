package eventwebapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import eventwebapp.utility.JDBCconnection;


public class UtenteDAO {
	public static boolean usernameExists(String username) throws Exception {
	    String sql = "SELECT COUNT(*) FROM utente WHERE username = ?";
	    
	    try (Connection conn = JDBCconnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, username);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	public static boolean emailExists(String email) throws Exception {
	    String sql = "SELECT COUNT(*) FROM utente WHERE email = ?";
	    
	    try (Connection conn = JDBCconnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, email);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	public static void addUtente(HttpServletRequest request ) {
	    try {
	      Connection conn = JDBCconnection.getConnection();
          PreparedStatement stmt = conn.prepareStatement("INSERT INTO utente (`nome`, `cognome`, `Data_di_nascita`, `email`, `username`, `password`) VALUES (?, ?, ?, ?, ?, ?)");
	      stmt.setString(1,request.getParameter("nome") );
          stmt.setString(2 ,request.getParameter("cognome") );
	      stmt.setString(3 , request.getParameter("datadinascita"));
	      stmt.setString(4 , request.getParameter("email") );
	      stmt.setString(5 , request.getParameter("username") );
	      stmt.setString(6 ,request.getParameter("password") );
	      stmt.executeUpdate();
	       
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	  }
	public static boolean Login(String username, String password) throws Exception {
	    String sql = "SELECT COUNT(*) FROM utente WHERE username = ? AND password = ?";
	    
	    try (Connection conn = JDBCconnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, username);
	        stmt.setString(2, password);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}

}
