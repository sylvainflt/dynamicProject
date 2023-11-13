<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="Accueil.jsp">Accueil</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarColor01">
	      <ul class="navbar-nav me-auto">
	        <li class="nav-item">
	          <a class="nav-link active" href="Connexion.jsp">Se Connecter
	            <span class="visually-hidden">(current)</span>
	          </a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="Inscription.jsp">S'enregistrer</a>
	        </li>
	       	
	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="" role="button" aria-haspopup="true" aria-expanded="false">Actions</a>
	          <div class="dropdown-menu">
	            <a class="dropdown-item" href="Connexion.jsp">Se Connecter</a>
	            <a class="dropdown-item" href="Inscription.jsp">S'enregistrer</a>
	            <div class="dropdown-divider"></div>
	            <a class="dropdown-item" href="Accueil.jsp">Accueil</a>
	          </div>
	        </li>
	      </ul>
	      <form class="d-flex">
	        <input class="form-control me-sm-2" type="search" placeholder="Search">
	        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
	      </form>
	    </div>
	  </div>
	</nav>

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
</body>
</html>