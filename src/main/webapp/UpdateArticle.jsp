<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Update Article</title>
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

	<h2 class="text-center mt-4">Bienvenue administrateur : ${user}</h2>
		
	<div class="col-5 mt-4 mx-auto">
		<form action="CommerceServlet?flag=articleModifieEnvoi" method="POST">
			<div class="form-group d-none">
		      	<label>id</label>
		        <input type="text" class="form-control" name="id" value="${article.id}">
		    </div>
			<div class="form-group">
		      	<label>Désignation</label>
		        <input type="text" class="form-control" name="designation" value="${article.designation}">
		    </div>
		       <div class="form-group">
		        <label>Prix unitaire</label>
				<input type="text" class="form-control" name="prixUnitaire" value="${article.prixUnitaire}">
			</div>
			<div class="form-group">
				<label>Quantité</label>
				<input type="text" class="form-control" name="quantite" value="${article.quantite}">
			</div>				
			<div class="form-group">
				<label>Catégorie</label>
				<select class="form-control" id="categorieSelect" name="categorie">		
			      	<c:forEach items="${categories}" var="categorie">
				        <option class="text-info" value="${categorie.id}">${categorie.designation}</option>
				    </c:forEach>
				  </select>		      
			</div>	
			
			<br/>			    			   
				
		    <button type="submit" value="Valider" class="btn btn-primary mt-1">Valider</button>
		    <button type="reset" value="Annuler" class="btn btn-secondary mt-1">Annuler</button>
		</form>    
	</div>	


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>