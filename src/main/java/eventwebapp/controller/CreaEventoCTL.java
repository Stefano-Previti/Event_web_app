package eventwebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eventwebapp.utility.DataValidator;
import eventwebapp.utility.ServletUtility;
import eventwebapp.model.eventiDAO;
import eventwebapp.model.UtenteDAO;



@WebServlet(name = "CreaEventoCTL", urlPatterns = {"/CreaEventoCTL"})

public class CreaEventoCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreaEventoCTL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletUtility.forward(JView.CreaEventoView, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
        int v=0;
        try {
			if(eventiDAO.titoloExists(request.getParameter("titolo"))) {
		    	  ServletUtility.setErrorTitololExists("Il titolo indicato non è disponibile,si prega di sceglierne uno nuovo.", request);
				  v++;
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
        if(!DataValidator.isValidName(request.getParameter("luogo"))) {
        	ServletUtility.setErrorLuogo("Il luogo deve essere espresso tramite caratteri alfabetici.", request);
			  v++;
        }
        if(!DataValidator.isValidDate(request.getParameter("data"))) {
        	ServletUtility.setErrorDataEvento("Data non valida:la data deve essere successiva alla data odierna ed espressa tramite il formato dd/mm/yyyy.", request);
			  v++;
        }
        if(!DataValidator.isValidTimeFormat(request.getParameter("orario"))) {
        	ServletUtility.setErrorOrario("Orario non valido:l'orario deve essere espresso tramite il formato hh:mm.", request);
			  v++;
        }
        if(!DataValidator.isNonNegativeInteger(request.getParameter("posti"))) {
        	ServletUtility.setErrorPosti("I posti disponibili devono essere espressi tramite un numero intero maggiore di zero.", request);
			  v++;
        }
        if (v==0) {
        	eventiDAO.addEvento(request, session);
    		ServletUtility.forward(JView.CreaEventoView, request, response);
        }else {
    		ServletUtility.forward(JView.CreaEventoView, request, response);
        }
        
	}

}
