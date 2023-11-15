<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://sylvainfoucault.fr/tld/Navigation" prefix="nav" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>connexion utilisateur</title>
	<!-- <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/quartz/bootstrap.min.css" rel="stylesheet"> -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<nav:Navigation accueil="Accueil.jsp" 
		seConnecter="Connexion.jsp" 
		sEnregistrer="Inscription.jsp"/>

	<% 
		String userName = request.getParameter("user");
		if(userName == null) userName = "";
		
	%>
		<h2 class="text-center mt-4">Bienvenue utilisateur : <%= userName %></h2>
		
		<div class="col-5 mt-4 mx-auto">
			<p>Nom : ${user.lname}</p>		      
			<p>Prénom : ${user.fname}</p>		      
			<p>Adresse : ${user.adress}</p>	      
			<p>Téléphone : ${user.tel}</p>
			<p>Age : ${user.age} ans</p>     
			<p>Sexe : ${user.sexe}</p>
			    
		    <br/>			    			   
		
		    <a href="CommerceServlet?flag=allerAModifUser">
		    	<button type="button" value="Modifier" id="allerModifUserButton" class="btn btn-primary mt-1">Modifier</button>
		    </a>
			   							 
		</div>	
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
</body>
</html>