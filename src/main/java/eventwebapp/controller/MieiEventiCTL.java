package eventwebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eventwebapp.model.UsernameDAO;
import eventwebapp.model.eventiDAO;
import eventwebapp.utility.ServletUtility;

@WebServlet(name = "MieiEventiCTL", urlPatterns = {"/MieiEventiCTL"})
public class MieiEventiCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public MieiEventiCTL() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
				
				session.setAttribute("mymap", eventiDAO.retrieveUserEvents((String) session.getAttribute("Username")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		ServletUtility.forward(JView.MieiEventiView, request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        eventiDAO.deleteEventoByTitle(request.getParameter("titol"));
        try {
			UsernameDAO.deleteRowsByUsernameAndTitle((String) session.getAttribute("Username"), request.getParameter("titol"));
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
			
			session.setAttribute("mymap", eventiDAO.retrieveUserEvents((String) session.getAttribute("Username")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	  ServletUtility.forward(JView.MieiEventiView, request, response);
	}

}
