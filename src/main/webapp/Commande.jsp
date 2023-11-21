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
	
		<h3 class="text-center mt-4">Contenu d'une commande (${commande.idCommande} : ${commande.dateCommande})</h3>
			
		<div class="col-5 mt-2 mx-auto">
		
			<form action="CommerceServlet?flag=suppLignesCommande" method="post">
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
					      <td>
					      	<a href="CommerceServlet?flag=allerLigneCommande&id=${ ligneCommande.idLigneCommande }" id="ligneCommande${ ligneCommande.idLigneCommande }">
					      		${ ligneCommande.idLigneCommande }
					      	</a>
					      </td>
					      <td>
					      	<a href="#" data-bs-toggle="tooltip" data-bs-placement="right" data-bs-title="">
					      		${ ligneCommande.idArticle }
					      	</a>
					      </td>
					      <td>${ ligneCommande.qtyCommandee }</td>
					      <td class="text-center">
					      	<input class="form-check-input" type="checkbox" name="lignesCommandeIds" value="${ ligneCommande.idLigneCommande }"/>
					      </td>
					    </tr>	
				    </c:forEach>				      			  
				  </tbody>
				</table>
			</form>	
			
			<h4>Ajout d'une ligne de commande</h4>
			
			<form action="CommerceServlet?flag=ajoutLigneCommande" method="post">
			  	
			  	<div class="form-group">
			        
					<select class="form-control" id="articleSelect" name="idArticle">		
			      	<c:forEach items="${articles}" var="article">
				        <option class="text-info" value="${article.id}">${article.designation}</option>
				    </c:forEach>
				  </select>  
				</div>
			    <div class="form-group">
			        <label>quantité</label>
					<input type="number" class="form-control w-50" name="quantite" required>
				</div>
				<div class="text-center">
					<button type="submit" value="Valider" class="btn btn-primary mt-1">Valider</button>
				</div>	
		    	
			</form>	
			
			<a href="CommerceServlet?flag=retourlisteCommandes">
		    	<button type="button" value="Retour" class="btn btn-secondary mt-1">Retour</button>
		    </a>
		</div>
		
		
		
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<script>		
		const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
		const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
	</script>
</body>
</html>