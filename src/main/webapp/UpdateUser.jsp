<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://sylvainfoucault.fr/tld/Navigation" prefix="nav" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>modification utilisateur</title>
	<!-- <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/quartz/bootstrap.min.css" rel="stylesheet"> -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<nav:Navigation accueil="Accueil.jsp" 
		seConnecter="Connexion.jsp" 
		sEnregistrer="Inscription.jsp"/>

		<h2 class="text-center mt-4">Bienvenue utilisateur : ${user.fname}</h2>
		
		<div class="col-5 mt-4 mx-auto">
			<form action="CommerceServlet?flag=modifUser" method="POST">
			  <fieldset>
			   
			    <div class="form-group">
			      <label for="nom" class="form-label mt-2">Nom</label>
			      <input type="text" class="form-control" id="nom" name="nom" autofocus="autofocus" value="${user.lname}" required>		      
			    </div>
			    <div class="form-group">
			      <label for="prenom" class="form-label mt-1">Prénom</label>
			      <input type="text" class="form-control" id="prenom" name="prenom" value="${user.fname}" required>		      
			    </div>
			    <div class="form-group">
			      <label for="adresse" class="form-label mt-1">Adresse</label>
			      <input type="text" class="form-control" id="adresse" name="adresse" value="${user.adress}" required>		      
			    </div>		   
			    <div class="form-group">
			      <label for="tel" class="form-label mt-2">Tél</label>
			      <input type="text" class="form-control" id="tel" name="tel" value="${user.tel}" required>		      
			    </div>
			    <div class="form-group">
			      <label for="age" class="form-label mt-1">Age</label>
			      <input type="number" class="form-control" id="age" name="age" value="${user.age}" required>		      
			    </div>
			    <div class="form-group">			    
			      <label class="form-label mt-2">Sexe</label>
			      <div class="form-check">
				      <input type="radio" class="form-check-input" id="sexF" name="sex" value="F" ${ user.sexe == "F" ? "checked" : "" }>	
				      <label for="sexF">Féminin</label>
				  </div>
			      <div class="form-check">
				      <input type="radio" class="form-check-input" id="sexH" name="sex" value="M" ${ user.sexe == "M" ? "checked" : "" }>	
				      <label for="sexH">Masculin</label>	
				  </div>      
			    </div>
			    
			    <br/>			    			   
			
			    <button type="submit" value="Valider" id="modifUserButton" class="btn btn-primary mt-1">Modifier</button>
			    <a href="CommerceServlet?flag=retourUser">
			    	<button type="button" value="Annuler" class="btn btn-secondary mt-1">Annuler</button>
			    </a>	
				
			  </fieldset>
			</form>
		</div>	
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
</body>
</html>