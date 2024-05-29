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

@WebServlet(name = "SearchCTL", urlPatterns = {"/SearchCTL"})

public class SearchCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public SearchCTL() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
				
				session.setAttribute("mymap", eventiDAO.retrieveUserEventByTitle((String) session.getAttribute("Username"),request.getParameter("searchTerm")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		ServletUtility.forward(JView.MieiEventiView, request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			session.setAttribute("prenotazioniMap", UsernameDAO.extractEventDataByTitle((String) session.getAttribute("Username"),request.getParameter("searchTerm")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	ServletUtility.forward(JView.MiePrenotazioniView, request, response);
		
	}

}
