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

@WebServlet(name = "MiePrenotazioniCTL", urlPatterns = {"/MiePrenotazioniCTL"})

public class MiePrenotazioniCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public MiePrenotazioniCTL() {
        super();
    } 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			session.setAttribute("prenotazioniMap", UsernameDAO.extractEventData((String) session.getAttribute("Username")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	ServletUtility.forward(JView.MiePrenotazioniView, request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			UsernameDAO.deleteEventData((String) session.getAttribute("Username"), request.getParameter("TITOLO"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			eventiDAO.incrementPosti(request.getParameter("TITOLO"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			session.setAttribute("prenotazioniMap", UsernameDAO.extractEventData((String) session.getAttribute("Username")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	ServletUtility.forward(JView.MiePrenotazioniView, request, response);
	}

}
