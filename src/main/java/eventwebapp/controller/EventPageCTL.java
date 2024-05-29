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

@WebServlet(name = "EventPageCTL", urlPatterns = {"/EventPageCTL"})

public class EventPageCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EventPageCTL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			UsernameDAO.insertData((String) session.getAttribute("Username"), request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			eventiDAO.decrementPosti(request.getParameter("tit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			
			session.setAttribute("eventBean", eventiDAO.retrieveEventByTitle(request.getParameter("tit")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	ServletUtility.forward(JView.EventPageView, request, response);
	}

}
