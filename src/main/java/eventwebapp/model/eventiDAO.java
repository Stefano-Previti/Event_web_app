package eventwebapp.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import eventwebapp.utility.JDBCconnection;

public class eventiDAO {
	public static boolean titoloExists(String titolo) throws Exception {
	    String sql = "SELECT COUNT(*) FROM eventi WHERE titolo = ?";
	    
	    try (Connection conn = JDBCconnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, titolo);
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
	public static void addEvento(HttpServletRequest request,HttpSession session ) {
	    try {
	      Connection conn = JDBCconnection.getConnection();
          PreparedStatement stmt = conn.prepareStatement("INSERT INTO eventi (`titolo`, `descrizione`, `data`, `ora`, `luogo`, `username`,`posti_disponibili`) VALUES (?, ?, ?, ?, ?, ?,?)");
	      stmt.setString(1,request.getParameter("titolo") );
          stmt.setString(2 ,request.getParameter("descrizione") );
       // Converto la data da "dd/mm/yyyy" a "yyyy-mm-dd" per il formato di MySQL
          String inputDate = request.getParameter("data");
          SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
          SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
          Date data = new Date(inputDateFormat.parse(inputDate).getTime());
          String mysqlFormattedDate = mysqlDateFormat.format(data);
          stmt.setString(3, mysqlFormattedDate);

          // Converto l'orario da "hh:mm" a "hh:mm:ss" per il formato di MySQL
          String inputTime = request.getParameter("orario");
          SimpleDateFormat inputTimeFormat = new SimpleDateFormat("hh:mm");
          SimpleDateFormat mysqlTimeFormat = new SimpleDateFormat("HH:mm:ss");
          Time ora = new Time(inputTimeFormat.parse(inputTime).getTime());
          String mysqlFormattedTime = mysqlTimeFormat.format(ora);
          stmt.setString(4, mysqlFormattedTime);
          
	      stmt.setString(5 , request.getParameter("luogo") );
	      stmt.setString(6 ,(String) session.getAttribute("Username"));
	      stmt.setInt(7 ,Integer.parseInt(request.getParameter("posti")) );
	      stmt.executeUpdate();
	       
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	  }
	public static Map<String, EventBean> retrieveUserEvents(String username) {
	    Map<String, EventBean> eventsMap = new LinkedHashMap<>();

	    try {
	        Connection conn = JDBCconnection.getConnection();
	        String query = "SELECT * FROM eventi WHERE username = ? ORDER BY data ASC, ora ASC";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, username);

	        ResultSet resultSet = stmt.executeQuery();
	        while (resultSet.next()) {
	            EventBean event = new EventBean();

	            event.setDescrizione(resultSet.getString("descrizione"));

	            // Converto la data da "yyyy-MM-dd" a "dd/MM/yyyy" per il formato richiesto
	            Date data = resultSet.getDate("data");
	            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String formattedData = outputDateFormat.format(data);
	            event.setData(formattedData);

	            // Converto l'orario da "HH:mm:ss" a "HH:mm" per il formato richiesto
	            Time ora = resultSet.getTime("ora");
	            SimpleDateFormat outputTimeFormat = new SimpleDateFormat("HH:mm");
	            String formattedOra = outputTimeFormat.format(ora);
	            event.setOra(formattedOra);

	            event.setLuogo(resultSet.getString("luogo"));
	            event.setPostiDisponibili(resultSet.getInt("posti_disponibili"));

	            eventsMap.put(resultSet.getString("titolo"), event);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return eventsMap;
	}
	public static Map<String, EventBean> retrieveUserEventByTitle(String username,String title) {
	    Map<String, EventBean> eventsMap = new LinkedHashMap<>();

	    try {
	        Connection conn = JDBCconnection.getConnection();
	        String query = "SELECT * FROM eventi WHERE titolo = ?";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, title);

	        ResultSet resultSet = stmt.executeQuery();
	        while (resultSet.next()) {
	            EventBean event = new EventBean();

	            event.setDescrizione(resultSet.getString("descrizione"));

	            // Converto la data da "yyyy-MM-dd" a "dd/MM/yyyy" per il formato richiesto
	            Date data = resultSet.getDate("data");
	            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String formattedData = outputDateFormat.format(data);
	            event.setData(formattedData);

	            // Converto l'orario da "HH:mm:ss" a "HH:mm" per il formato richiesto
	            Time ora = resultSet.getTime("ora");
	            SimpleDateFormat outputTimeFormat = new SimpleDateFormat("HH:mm");
	            String formattedOra = outputTimeFormat.format(ora);
	            event.setOra(formattedOra);

	            event.setLuogo(resultSet.getString("luogo"));
	            event.setPostiDisponibili(resultSet.getInt("posti_disponibili"));

	            eventsMap.put(resultSet.getString("titolo"), event);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return eventsMap;
	}
	public static Map<String, EventBean> retrieveAllOtherUserEvents(String username) {
	    Map<String, EventBean> eventsMap = new LinkedHashMap<>();

	    try {
	        Connection conn = JDBCconnection.getConnection();
	        String query = "SELECT * FROM eventi WHERE username != ? ORDER BY data ASC";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, username);
	        ResultSet resultSet = stmt.executeQuery();
	        while (resultSet.next()) {
	            EventBean event = new EventBean();

	            // Converto la data da "yyyy-MM-dd" a "dd/MM/yyyy" per il formato richiesto
	            Date data = resultSet.getDate("data");
	            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String formattedData = outputDateFormat.format(data);
	            event.setData(formattedData);
	            event.setLuogo(resultSet.getString("luogo"));
	            eventsMap.put(resultSet.getString("titolo"), event);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return eventsMap;
	}
	public static Map<String, EventBean> retrieveOtherUserEventByTitle(String username,String title) {
	    Map<String, EventBean> eventsMap = new LinkedHashMap<>();

	    try {
	        Connection conn = JDBCconnection.getConnection();
	        String query = "SELECT * FROM eventi WHERE username != ? AND titolo=?";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, username);
	        stmt.setString(2, title);

	        ResultSet resultSet = stmt.executeQuery();
	        while (resultSet.next()) {
	            EventBean event = new EventBean();

	            // Converto la data da "yyyy-MM-dd" a "dd/MM/yyyy" per il formato richiesto
	            Date data = resultSet.getDate("data");
	            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String formattedData = outputDateFormat.format(data);
	            event.setData(formattedData);
	            event.setLuogo(resultSet.getString("luogo"));
	            eventsMap.put(resultSet.getString("titolo"), event);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return eventsMap;
	}
	public static void deleteEventoByTitle(String titolo) {
	    try {
	        Connection conn = JDBCconnection.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("DELETE FROM eventi WHERE titolo = ?");
	        stmt.setString(1, titolo);
	        stmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public static EventBean retrieveEventByTitle(String titolo) {
	    EventBean eventBean = null;

	    try {
	        Connection conn = JDBCconnection.getConnection();
	        String query = "SELECT * FROM eventi WHERE titolo = ?";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, titolo);
	        ResultSet resultSet = stmt.executeQuery();

	        if (resultSet.next()) {
	            eventBean = new EventBean();
	            eventBean.setTitolo(titolo);
	            eventBean.setDescrizione(resultSet.getString("descrizione"));

	            // Converto la data da "yyyy-MM-dd" a "dd/MM/yyyy" per il formato richiesto
	            Date data = resultSet.getDate("data");
	            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String formattedData = outputDateFormat.format(data);
	            eventBean.setData(formattedData);

	            // Converto l'ora da "HH:mm:ss" a "HH:mm" per il formato richiesto
	            Time ora = resultSet.getTime("ora");
	            SimpleDateFormat outputTimeFormat = new SimpleDateFormat("HH:mm");
	            String formattedOra = outputTimeFormat.format(ora);
	            eventBean.setOra(formattedOra);

	            eventBean.setLuogo(resultSet.getString("luogo"));
	            eventBean.setPostiDisponibili(resultSet.getInt("posti_disponibili"));
	            eventBean.setUsername(resultSet.getString("username"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return eventBean;
	}
	 public static void decrementPosti(String title) throws Exception {
	        try (Connection conn = JDBCconnection.getConnection()) {
	            String updateQuery = "UPDATE eventi SET posti_disponibili = posti_disponibili - 1 WHERE titolo = ?";
	            PreparedStatement stmt = conn.prepareStatement(updateQuery);

	            stmt.setString(1, title);
                stmt.executeUpdate();

	        } catch (SQLException e) {
	        }
	    }
	 public static void incrementPosti(String title) throws Exception {
	        try (Connection conn = JDBCconnection.getConnection()) {
	            String updateQuery = "UPDATE eventi SET posti_disponibili = posti_disponibili + 1 WHERE titolo = ?";
	            PreparedStatement stmt = conn.prepareStatement(updateQuery);

	            stmt.setString(1, title);
             stmt.executeUpdate();

	        } catch (SQLException e) {
	        }
	    }

}
