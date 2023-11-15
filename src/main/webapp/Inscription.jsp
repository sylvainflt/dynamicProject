<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://sylvainfoucault.fr/tld/Navigation" prefix="nav" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Inscription</title>
	<!-- <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/quartz/bootstrap.min.css" rel="stylesheet"> -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script type="module" src="js/validationsInscription.js"></script>
</head>
<body>
	<nav:Navigation accueil="Accueil.jsp" 
		seConnecter="Connexion.jsp" 
		sEnregistrer="Inscription.jsp"/>

	<div class="container">
		<form action="CommerceServlet?flag=inscription" method="POST">
		  <fieldset>
		  	<div class="d-flex">
			    <div class="col-5">
			    	<% 						
			    		String isInvalidLogin = "";
						if(request.getAttribute("erreurLoginCourt")!=null) isInvalidLogin = "is-invalid";						
					%>
				    <div class="form-group">
				      <label for="lname" class="form-label mt-4">Nom</label>
				      <input type="text" class="form-control <%= isInvalidLogin %>" id="lname" name="lname" required>	
				      <% 							
						if(request.getAttribute("erreurLoginCourt")!=null){
						%>
				      		<div class="invalid-feedback"><%= request.getAttribute("erreurLoginCourt") %></div>
				      	<%
						}
				      %>
				    </div>
				    <div class="form-group">
				      <label for="fname" class="form-label mt-2">Prénom</label>
				      <input type="text" class="form-control" id="fname" name="fname">	
				      <small id="invalidPrenom" class="invalid-feedback hidden">Le prénom doit contenir au moins 3 caractères.</small>	      
				    </div>
				    <div class="form-group">
				      <label for="adress" class="form-label mt-2">Adresse</label>
				      <input type="text" class="form-control" id="adress" name="adress" required>		      
				    </div>
				    <div class="form-group">
				      <label for="tel" class="form-label mt-2">Tél</label>
				      <input type="text" class="form-control" id="tel" name="tel" required>		      
				    </div>
				    <div class="form-group">
				      <label for="age" class="form-label mt-2">Age</label>
				      <input type="text" class="form-control" id="age" name="age" required>		      
				    </div>
				    <div class="form-group">
				      <label class="form-label mt-2">Sexe</label>
				      <div class="form-check">
					      <input type="radio" class="form-check-input" id="sexF" name="sex" value="F" checked>	
					      <label for="sexF">Féminin</label>
					  </div>
				      <div class="form-check">
					      <input type="radio" class="form-check-input" id="sexH" name="sex" value="H">	
					      <label for="sexH">Masculin</label>	
					  </div>      
				    </div>				    
			    </div>
			    
			    <div class="col-5 mx-2">
			    	<div class="form-group">
				      <label for="login" class="form-label mt-4">Login</label>
				      <input type="text" class="form-control" id="login" name="login" required>		      
				    </div>
				    <div class="form-group">
				      <label for="password" class="form-label mt-2">Password</label>
				      <input type="password" class="form-control" id="password" name="password" required>		      
				    </div>
				    <br/>
				    <div class="text-center">
					    <button type="submit" value="Valider" id="valider" class="btn btn-primary mt-4">Valider</button>
			   			<button type="reset" value="Annuler" id="annuler" class="btn btn-secondary mt-4">Annuler</button>
			   		</div>	
			    </div>
			</div>    
		    
		  </fieldset>
		</form>
	</div>	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>