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

@WebServlet(name = "AltriEventiCTL", urlPatterns = {"/AltriEventiCTL"})

public class AltriEventiCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AltriEventiCTL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
				
				session.setAttribute("completemap", eventiDAO.retrieveAllOtherUserEvents((String) session.getAttribute("Username")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		ServletUtility.forward(JView.AltriEventiView, request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			UsernameDAO.createTableUser((String) session.getAttribute("Username"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			
			session.setAttribute("eventBean", eventiDAO.retrieveEventByTitle(request.getParameter("title")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	ServletUtility.forward(JView.EventPageView, request, response);
	
	}

}
