package eventwebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eventwebapp.model.UtenteDAO;
import eventwebapp.utility.DataValidator;
import eventwebapp.utility.ServletUtility;
import eventwebapp.controller.JView;
import eventwebapp.model.UtenteDAO;

@WebServlet(name = "RegistrationCTL", urlPatterns = {"/RegistrationCTL"})
public class RegistrationCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RegistrationCTL() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(JView.LoginView, request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Imposto l'header Cache-Control per evitare la memorizzazione nella cache del browser
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // Imposto anche l'header Pragma per la compatibilità con alcuni vecchi browser
        response.setHeader("Pragma", "no-cache");
        // Imposto l'header Expires a 0 per indicare al browser di non memorizzare la pagina nella cache
        response.setHeader("Expires", "0");
        int t=0;
        if(!DataValidator.isValidName(request.getParameter("nome"))) {
      	  ServletUtility.setErrorName("Il nome deve contenere solo caratteri alfabetici.", request);
      	  t++;
        }
        if(!DataValidator.isValidName(request.getParameter("cognome"))) {
      	  ServletUtility.setErrorSurname("Il cognome deve contenere solo caratteri alfabetici.", request);
      	  t++;
        }
        if(!DataValidator.isValidPassword(request.getParameter("password"))) {
      	  ServletUtility.setErrorPassword("La password deve avere una lunghezza compresa fra 6 e 12 caratteri. "
      	  		+ "Deve contenere almeno un numero e almeno un carattere alfabetico.", request);
      	  
      	  t++;
        }
        if(!DataValidator.isPasswordConfirmed(request.getParameter("password"),request.getParameter("confermaPassword"))) {
      	  ServletUtility.setErrorConfirmedPassword("Il campo 'Password' e il campo 'Conferma Password' non coincidono.", request);
      	  t++;
        }
        if(!DataValidator.isValidDateOfBirth(request.getParameter("datadinascita"))) {
      	  ServletUtility.setErrorDOB("Data non valida:la data di nascita deve seguire il formato dd/mm/yyyy."
      	  		+ "(Si ricorda che da regolamento non sono ammessi utenti al di sotto dei 18 anni)", request);
      	  t++;
        }
        if(!DataValidator.isValid(request.getParameter("username"))) {
      	  ServletUtility.setErrorUsernameFormat("Lo username deve essere espresso tramite caratteri alfabetici e con l'underscore al posto dello spazio bianco.", request);
      	  t++;
        }
        if(!DataValidator.isValidEmail(request.getParameter("email"))) {
        	  ServletUtility.setErrorMail("Il formato della mail non è valido", request);
        	  t++;
          }
        try {
			if(UtenteDAO.usernameExists(request.getParameter("username"))) {
			  ServletUtility.setErrorUsernameExists("L'username inserito non è disponibile,si prega di sceglierne uno nuovo.", request);
			  t++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
			if(UtenteDAO.emailExists(request.getParameter("email"))) {
			  ServletUtility.setErrorEmailExists("La mail inserito non è disponibile,si prega di sceglierne uno nuova.", request);
			  t++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        if (t>0) {
      	  ServletUtility.forward(JView.RegistrationView, request, response);
        }else {
        	  UtenteDAO.addUtente(request);
        	  HttpSession session = request.getSession(true);
        	  session.setAttribute("Username", request.getParameter("username"));
 			 ServletUtility.forward(JView.CreaEventoView, request, response);
        }
          
        
	}

}
