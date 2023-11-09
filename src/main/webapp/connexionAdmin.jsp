<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>connexion administrateur</title>
	<!--  <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/quartz/bootstrap.min.css" rel="stylesheet"> -->
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
	<h2 class="text-center mt-4">Bienvenue administrateur : <%= user %></h2>
		
	<div class="col-5 mt-4 mx-auto">
		<ul class="nav nav-tabs" role="tablist">
		  <li class="nav-item" role="presentation">
		    <a class="nav-link active" data-bs-toggle="tab" href="#categorie" aria-selected="true" role="tab">Categorie</a>
		  </li>
		  <li class="nav-item" role="presentation">
		    <a class="nav-link" data-bs-toggle="tab" href="#article" aria-selected="false" role="tab" tabindex="-1">Article</a>
		  </li>	  
		</ul>
		<div id="myTabContent" class="tab-content">
		  <div class="tab-pane fade active show" id="categorie" role="tabpanel">
		    
		    <form action="CommerceServlet?flag=ajoutCategorie" method="POST">
			  <fieldset>
			   
			    <div class="form-group">
			      <label for="categorie" class="form-label mt-4">Catégorie</label>
			      <input type="text" class="form-control" id="categorie" name="categorie" autofocus="autofocus" required>		      
			    </div>
			    
			    <br/>			    			   
			
			    <button type="submit" value="Valider" class="btn btn-primary mt-2">Valider</button>
			    <button type="reset" value="Annuler" class="btn btn-secondary mt-2">Annuler</button>
			    			    
			    <p class="mt-4">${ resultat }</p>
				
			  </fieldset>
			</form>
			
		  </div>
		  <div class="tab-pane fade" id="article" role="tabpanel">
		    
		    <form action="CommerceServlet?flag=ajoutArticle" method="POST">
			  <fieldset>
			   
			    <div class="form-group">
			      <label for="designation" class="form-label mt-4">Désignation</label>
			      <input type="text" class="form-control" id="designation" name="designation" autofocus="autofocus" required>		      
			    </div>
			    <div class="form-group">
			      <label for="prixUnitaire" class="form-label mt-2">Prix unitaire</label>
			      <input type="text" class="form-control" id="prixUnitaire" name="prixUnitaire" required>		      
			    </div>
			    <div class="form-group">
			      <label for="quantite" class="form-label mt-2">Quantité</label>
			      <input type="text" class="form-control" id="quantite" name="quantite" required>		      
			    </div>
			    <div class="form-group">
			      <label for="categorie" class="form-label mt-2">Catégorie</label>
			      <select class="form-control" id="categorie" name="categorie">		
			      	<c:forEach items="${categories}" var="categorie">
				        <option value="${categorie.id}">${categorie.designation}</option>
				    </c:forEach>
				  </select>  
			    </div>
			    
			    <br/>			    			   
			
			    <button type="submit" value="Valider" class="btn btn-primary mt-2">Valider</button>
			    <button type="reset" value="Annuler" class="btn btn-secondary mt-2">Annuler</button>
				
			  </fieldset>
			</form>
		    
		  </div>	  
		</div>
	</div>	
	
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
</body>
</html>