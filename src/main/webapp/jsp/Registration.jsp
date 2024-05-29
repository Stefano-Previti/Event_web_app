<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="eventwebapp.controller.JView" %>
 <%@ page import="eventwebapp.utility.ServletUtility" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrazione</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
        }

        .container {
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            max-height: 800px;
            overflow-y: auto;
            overflow-x: hidden;
            
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
    <div class="container">
        <h1>Registrazione</h1>
        <form action="<%=JView.RegistrationCTL%>" method="POST">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" required>
            
            <label for="cognome">Cognome:</label>
            <input type="text" id="cognome" name="cognome" required>

            <label for="data di nascita">Data di nascita:</label>
            <input type="text" id="datdinascita" name="datadinascita" required>

            <label for="email">E-mail:</label>
            <input type="text" id="email" name="email" required>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="confermaPassword">Conferma password:</label>
            <input type="password" id="confermaPassword" name="confermaPassword" required>
  
            <input type="submit" value="Registrati" class="submit-button">
        </form>
       <p>Hai già un account? <a href="<%=JView.RegistrationCTL%>"> Accedi</a></p> 
        <p class="error-message"> <%=ServletUtility.getErrorName(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorSurname(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorDOB(request) %></p>
        <p class="error-message"> <%=ServletUtility.getErrorEmailExists(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorMail(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorUsernameFormat(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorUsernameExists(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorPassword(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorConfirmedPassword(request)%></p> 
    </div>
</body>
</html>