<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://sylvainfoucault.fr/tld/Navigation" prefix="nav" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Commande</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<nav:Navigation accueil="Accueil.jsp" 
		seConnecter="Connexion.jsp" 
		sEnregistrer="Inscription.jsp"/>
		
		<h2 class="text-center mt-4">Bienvenue administrateur : ${user}</h2>
	
		<h3 class="text-center mt-4">Contenu d'une commande</h3>
			
		<div class="col-5 mt-2 mx-auto">
		
			<form action="CommerceServlet?flag=suppArticlesCommande" method="post">
			  	<table class="table table-hover mt-2">
				  <thead>
				    <tr>
				      <th scope="col">Numéro</th>
				      <th scope="col">Article</th>
				      <th scope="col">Quantité</th>				
				      <th scope="col" class="text-center"><button type="submit" class="btn btn-outline-primary p-0" id="suppButton">Supprimer</button></th>
				    </tr>
				  </thead>
				  <tbody>	
				  	<c:forEach items="${lignesCommande}" var="ligneCommande">				  					  		    
					    <tr>					      
					      <td>${ ligneCommande.id }</td>
					      <td>${ ligneCommande.article }</td>
					      <td>${ ligneCommande.quantite }</td>
					      <td class="text-center">
					      	<input class="form-check-input" type="checkbox" name="lignesCommandeIds" value="${ ligneCommande.id }"/>
					      </td>
					    </tr>	
				    </c:forEach>				      			  
				  </tbody>
				</table>
			</form>	
		</div>
		
		
		
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>