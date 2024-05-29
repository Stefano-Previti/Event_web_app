<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="eventwebapp.controller.JView" %>
<%@ page import="eventwebapp.utility.ServletUtility" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="eventwebapp.model.EventBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Altri eventi</title>
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

    /* Stile per il form */
    .event-form {
        margin-top: 10px;
    }

    /* Stile per il tasto "Event Page" */
   .submit-button {
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
        align-items: left;
        justify-content: left;;
        margin-top: 10px; /* Aggiunto margine superiore */
        margin-bottom:15px;
    }

    .search-form input[type="text"],
    .search-form input[type="submit"] {
        height: 40px;
    }
</style>
<body>
    <div class="navbar">
        <a href="<%=JView.CreaEventoCTL%>">Crea evento</a>
        <a href="<%=JView.MieiEventiCTL%>">Miei eventi</a>
        <a class="active" href="<%=JView.AltriEventiCTL%>">Altri eventi</a>
        <a href="<%=JView.MiePrenotazioniCTL%>">Mie prenotazioni</a>
        <a class="logout" href="<%=JView.LogoutCTL%>">Logout</a>
    </div>
    <div class="container">
        <h1>Elenco eventi disponibili</h1>
       <div class="search-form">
            <form action="<%=JView.Search1CTL%>" method="post">
                <input type="text" name="searchTerm" placeholder="Cerca evento...">
                <input type="submit" value="Cerca" class="submit-button" style="height: 40px;">
            </form>
        </div>
        <% Map<String, EventBean> eventsMap = (Map<String, EventBean>) session.getAttribute("completemap"); %>
        <% if (eventsMap != null && !eventsMap.isEmpty()) { %>
            <% for (Map.Entry<String, EventBean> entry : eventsMap.entrySet()) { %>
                <% String titolo = entry.getKey(); %>
                <% EventBean eventBean = entry.getValue(); %>
                <div class="rectangle">
                    <p>Titolo: <%= titolo %></p>
                    <p>Data: <%= eventBean.getData() %></p>
                    <p>Luogo: <%= eventBean.getLuogo() %></p>
                    <form class="event-form" action="<%=JView.AltriEventiCTL%>" method="post">
                        <input type="hidden" name="title" value="<%= titolo %>">
                        <input type="submit" value="Event Page"class="submit-button" style="background-color:light-green;">
                    </form>
                </div>
            <% } %>
        <% } else { %>
            <p>Nessun evento disponibile</p>
        <% } %>
    </div>
</body>
</html>
