<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>connexion utilisateur</title>
	<!-- <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/quartz/bootstrap.min.css" rel="stylesheet"> -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
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
		if(user == null) user = "";
		
	%>
		<h2 class="text-center mt-4">Bienvenue utilisateur : <%= user %></h2>
</body>
</html>