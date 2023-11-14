<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://sylvainfoucault.fr/tld/Navigation" prefix="nav" %>      
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="author" content="SylvainFoucault">
	<title>future application connexion</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<!-- <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/quartz/bootstrap.min.css" rel="stylesheet"> -->
	<link rel="stylesheet" href="css/mystyle.css">
</head>
<body>
	<nav:Navigation accueil="Accueil.jsp" 
		seConnecter="Connexion.jsp" 
		sEnregistrer="Inscription.jsp"/>

	<% 
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		if(user == null) user = "";
		if(password == null) password = "";
		if(request.getMethod().equals("POST") &&
				user.equals("admin") &&
				password.equals("admin")){
	%>
		<h2 class="text-center mt-4">Bienvenue <%= user %></h2>
	<%
		}
		else {
	%>
	<div class="container col-5">
		<form action="CommerceServlet?flag=connect" method="POST">
		  <fieldset>
		    
		    	<% 						
		    		String isInvalidLogin = "";
					if("true".equals(request.getAttribute("erreurLogin"))) isInvalidLogin = "is-invalid";
					String isInvalidPassword = "";
					if("true".equals(request.getAttribute("erreurPassword"))) isInvalidPassword = "is-invalid";
				%>
		    	
			    <div class="form-group">
			      <label for="user" class="form-label mt-4">User</label>
			      <input type="text" class="form-control <%= isInvalidLogin %>" id="user" name="user" placeholder="Enter user" autofocus="autofocus" required>		      
			    </div>
			    <div class="form-group">
			      <label for="password" class="form-label mt-2">Password</label>
			      <input type="password" class="form-control <%= isInvalidPassword %>" id="password" name="password" placeholder="Password" autocomplete="off" required>
			    </div>
			    <br/>
			    
			    <% 							
					if("true".equals(request.getAttribute("erreurLogin"))){
				%>
					<small style="color:red">mauvais login</small>
					<br/>
				<%
					}
				    if("true".equals(request.getAttribute("erreurPassword"))){
				%>
					<small style="color:red">mauvais password</small>
					<br/>
				<%
					}
				%>
			
			    <button type="submit" value="Valider" class="btn btn-primary mt-2">Valider</button>
			    <button type="reset" value="Annuler" class="btn btn-secondary mt-2">Annuler</button>
			
		  </fieldset>
		</form>
	</div>	
	<% } %>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>