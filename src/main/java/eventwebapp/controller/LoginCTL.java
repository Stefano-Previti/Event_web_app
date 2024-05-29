package eventwebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eventwebapp.controller.JView;
import eventwebapp.model.UtenteDAO;
import eventwebapp.utility.ServletUtility;

@WebServlet(name = "LoginCTL", urlPatterns = {"/LoginCTL"})

public class LoginCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public LoginCTL() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(JView.RegistrationView, request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Imposto l'header Cache-Control per evitare la memorizzazione nella cache del browser
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // Imposto anche l'header Pragma per la compatibilità con alcuni vecchi browser
        response.setHeader("Pragma", "no-cache");
        // Imposto l'header Expires a 0 per indicare al browser di non memorizzare la pagina nella cache
        response.setHeader("Expires", "0");

	try {
		if(UtenteDAO.Login(request.getParameter("username"), request.getParameter("password"))) {
			  HttpSession session = request.getSession(true);
		 	  session.setAttribute("Username", request.getParameter("username"));
			 ServletUtility.forward(JView.CreaEventoView, request, response);

		}else {
			ServletUtility.setErrorLogin("Username o password non validi.", request);
		    ServletUtility.forward(JView.LoginView, request, response);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}


}}
