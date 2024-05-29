<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="eventwebapp.controller.JView" %>
<%@ page import="eventwebapp.utility.ServletUtility" %>
<%@ page import="eventwebapp.model.EventBean" %>
<%@ page import="eventwebapp.model.UsernameDAO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EventPage</title>
</head>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    background-color: #f2f2f2;
}

.navbar {
    overflow: hidden;
    background-color: #bbbbbb;
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

.container {
    background-color: #fff;
    border-radius: 5px;
    padding: 20px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    max-width: 400px;
    width: 100%;
    margin: 50px auto;
    text-align: left;
}
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
</style>
<body>
<div class="navbar">
    <a href="<%=JView.CreaEventoCTL%>">Crea evento</a>
    <a href="<%=JView.MieiEventiCTL%>">Miei eventi</a>
    <a href="<%=JView.AltriEventiCTL%>">Altri eventi</a>
    <a href="<%=JView.MiePrenotazioniCTL%>">Mie prenotazioni</a>
    <a class="logout" href="<%=JView.LogoutCTL%>">Logout</a>
</div>
<div class="container">
    <h1>Event Page</h1>
    <% EventBean eventBean = (EventBean) session.getAttribute("eventBean"); %>
    <% if (eventBean != null) { %>
        <p>Titolo: <%= eventBean.getTitolo() %></p>
        <p>Creatore: <%= eventBean.getUsername() %></p>
        <p>Data: <%= eventBean.getData() %></p>
        <p>Ora: <%= eventBean.getOra() %></p>
        <p>Luogo: <%= eventBean.getLuogo() %></p>
        <p>Posti disponibili: <%= eventBean.getPostiDisponibili() %></p>
        <p>Descrizione: <%= eventBean.getDescrizione() %></p>
             
          <%
        String titolo = eventBean.getTitolo();
        String tableName = (String) session.getAttribute("Username"); // Nome della tabella
        boolean titleExists = UsernameDAO.doesTitleExist(tableName, titolo);
        int postiDisponibili = eventBean.getPostiDisponibili();
        %>

        <% if (titleExists) { %>
            <p style="color: green;">Prenotazione effettuata</p>
        <% } else if (!titleExists) { %>
            <% if (postiDisponibili > 0) { %>
                <form action="<%= JView.EventPageCTL %>" method="POST">
                    <input type="hidden" name="tit" value="<%= titolo %>">
                    <input type="hidden" name="date" value="<%= eventBean.getData() %>">
                    <input type="hidden" name="Luogo" value="<%= eventBean.getLuogo() %>">
                    <input type="submit" value="Prenotati" class="submit-button">
                </form>
            <% } else { %>
                <p style="color: red;">Posti esauriti</p>
            <% } %>
        <% } else { %>
            <p>Nessun dettaglio disponibile per questo evento</p>
        <% } %>
    <% } %>
</div>
</body>
</html>
