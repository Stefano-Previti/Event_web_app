<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="eventwebapp.controller.JView" %>
 <%@ page import="eventwebapp.utility.ServletUtility" %>
 <%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="eventwebapp.model.EventBean" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mie prenotazioni</title>
</head>
<style>
 body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f2f2f2;
        }
     
  .container {
            text-align:center;
            max-width: 350px;
            margin: 0 auto;
            padding: 20px;
        }

        .rectangle {
            background-color: #ffffff;
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 20px;
            position: relative; 
        }

        form {
            display: inline-block;
            margin-right: 70px;
          
        }

        label {
            display: block;
            margin-bottom: 5px;
        }



        input[type="submit"] {
            width: 160px;
            padding: 10px;
            background-color: #4caf50;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
.elimina {
        color: red;
        border: none;
        padding: 5px 10px;
       
    }

    /* Stile per la barra di navigazione */
        .navbar {
            overflow: hidden;
            background-color:#bbbbbb;
        }

        .navbar a {
            float: left;
            font-size: 16px;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;                                         
        }

   .navbar a:hover {
    text-decoration: underline; 
}

        .active {
            background-color: #4CAF50;
            color: white;
        }
        .navbar .logout {
        float: right;
        margin-right: 0;
    }
      .logout {
        background-color: red;
    color: white;
      }
      .submit-button {
    margin-left:75px;
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.submit-button:hover {
    background-color: #45a049;
}
    /* Stile per la barra di ricerca */
.search-form {
     display: flex;
    align-items: center; /* Allinea verticalmente al centro */
    justify-content: left;
    margin-top: 10px;
    margin-bottom: 15px;
}

.search-form input[type="text"],
    .search-form input[type="submit"] {
        height: 40px;
    }
    </style>
</head>
<body>
    <div class="navbar">
        <a href="<%=JView.CreaEventoCTL%>">Crea evento</a>
        <a  href="<%=JView.MieiEventiCTL%>">Miei eventi</a>
        <a href="<%=JView.AltriEventiCTL%>">Altri eventi</a>
        <a class="active"href="<%=JView.MiePrenotazioniCTL%>">Mie prenotazioni</a>
        <a class="logout" href="<%=JView.LogoutCTL%>">Logout</a>
    </div>
     <div class="container">
        <h1>Elenco prenotazioni effettuate</h1>
         <div class="search-form">
            <form action="<%=JView.SearchCTL%>" method="post">
                <input type="text" name="searchTerm" placeholder="Cerca evento..."style="height: 44px; width: 170px;">
                <input type="submit" value="Cerca" class="submit-button" style="height: 40px; width: 80px;margin-left: 0;">
            </form>
        </div>
        <% Map<String, EventBean> prenotazioniMap = (Map<String, EventBean>) session.getAttribute("prenotazioniMap"); %>
        <% if (prenotazioniMap != null && !prenotazioniMap.isEmpty()) { %>
            <% for (Map.Entry<String, EventBean> entry : prenotazioniMap.entrySet()) { %>
                <% String titolo = entry.getKey(); %>
                <% EventBean eventBean = entry.getValue(); %>
                <div class="rectangle">
                    <p>Titolo: <%= titolo %></p>
                    <p>Data: <%= eventBean.getData() %></p>
                    <p>Luogo: <%= eventBean.getLuogo() %></p>
                          <form action="<%=JView.MiePrenotazioniCTL%>"" method="post" class="elimina">
                            <input type="hidden" name="TITOLO" value="<%= titolo %>">
                          <input type="submit" value="Annulla prenotazione" class="submit-button" style="background-color: red;">
                        </form>
                </div>
            <% } %>
        <% } else { %>
            <p>Nessuna prenotazione effettuata</p>
        <% } %>
    </div>
</body>
</html>