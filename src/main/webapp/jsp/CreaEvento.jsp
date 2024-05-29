<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="eventwebapp.controller.JView" %>
 <%@ page import="eventwebapp.utility.ServletUtility" %>

<!DOCTYPE html>
<html>
<head>
    <title>Crea evento</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .container {
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        h1, p {
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"],
        select {
            width: 90%;
            padding: 10px;
            border-radius: 3px;
            border: 1px solid #ccc;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
       
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        p {
            color: red;
            margin-top: 10px;
        }

        p a {
            color: #4caf50;
            text-decoration: none;
        }

        p a:hover {
            text-decoration: underline;
        }

        #descrizione {
            width: 100%;
            height: 100px;
            padding: 5px;
            margin-bottom: 10px;
            border-radius: 3px;
            border: 1px solid #ccc;
            white-space: pre-wrap;
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
</head>
<body>
    <div class="navbar">
        <a class="active" href="<%=JView.CreaEventoCTL%>">Crea evento</a>
        <a href="<%=JView.MieiEventiCTL%>">Miei eventi</a>
        <a href="<%=JView.AltriEventiCTL%>">Altri eventi</a>
        <a href="<%=JView.MiePrenotazioniCTL%>">Mie prenotazioni</a>
        <a class="logout" href="<%=JView.LogoutCTL%>">Logout</a>
    </div>

    <div class="container">
        <h1>Crea nuovo evento</h1>
        <form action="<%=JView.CreaEventoCTL%>" method="POST">
            <label for="titolo">Titolo:</label>
            <input type="text" id="titolo" name="titolo" required>
              
            <label for="luogo">Luogo:</label>
            <input type="text" id="luogo" name="luogo" required>
            
            <label for="data">Data:</label>
            <input type="text" id="data" name="data" required>

            <label for="orario">Orario:</label>
            <input type="text" id="orario" name="orario" required>
            
            <label for="posti">Posti disponibili:</label>
            <input type="text" id="posti" name="posti" required>

            <label for="descrizione">Descrizione:</label>
            <textarea id="descrizione" name="descrizione" wrap="soft" required></textarea>
            <input type="submit" value="Crea" class="submit-button">
        </form>
       <p class="error-message"><%=ServletUtility.getErrorTitoloExists(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorLuogo(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorDataEvento(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorOrario(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorPosti(request) %></p>
    </div>
</body>
</html>
