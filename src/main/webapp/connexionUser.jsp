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

		<h2 class="text-center mt-4">Bienvenue utilisateur : ${user.fname}</h2>
		
		<div class="col-6 mt-4 mx-auto d-flex gap-4">
			<div class="card border-primary mb-3" style="max-width: 20rem;">
			  <div class="card-header">Informations utilisateur</div>
			  <div class="card-body">      			
				<p><span  class="text-primary">Nom : </span>${user.lname}</p>		      
				<p><span class="text-primary-emphasis">Prénom : </span>${user.fname}</p>		      
				<p><span  class="text-primary">Adresse : </span>${user.adress}</p>	      
				<p><span class="text-primary-emphasis">Téléphone : </span>${user.tel}</p>
				<p><span  class="text-primary">Age : </span>${user.age} ans</p>     
				<p><span class="text-primary-emphasis">Sexe : </span>${user.sexe}</p>			    			    	    			   		
			    <a href="CommerceServlet?flag=allerAModifUser">
			    	<button type="button" value="Modifier" id="allerModifUserButton" class="btn btn-primary mt-1">Modifier</button>
			    </a>
			  </div>  
			</div>	
			<div class="card border-info mb-3" style="max-width: 20rem;">
			  <div class="card-header">Compte utilisateur</div>
			  <div class="card-body">
				<p><span class="text-info">Utilisateur : </span>${compte.login}</p>		      
				<p><span  class="text-dark">Mot de passe : </span>${compte.password}</p>
				<a href="CommerceServlet?flag=allerAModifMDP">
			    	<button type="button" value="Modifier" id="allerModifMDPButton" class="btn btn-info mt-1">Modifier</button>
			    </a>
			  </div>
			</div>					 
		</div>	
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
</body>
</html>