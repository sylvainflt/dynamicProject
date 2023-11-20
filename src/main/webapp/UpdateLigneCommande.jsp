<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://sylvainfoucault.fr/tld/Navigation" prefix="nav" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Modif Ligne Commande</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<nav:Navigation accueil="Accueil.jsp" 
		seConnecter="Connexion.jsp" 
		sEnregistrer="Inscription.jsp"/>
		
		<h2 class="text-center mt-4">Bienvenue administrateur : ${user}</h2>
	
		<h3 class="text-center mt-4">Modification d'une ligne de commande (${ligneCommande.idLigneCommande})</h3>
			
		<div class="col-5 mt-2 mx-auto">
		
			<form action="CommerceServlet?flag=modifLigneCommande" method="post">
			  	<p>idArticle : ${ligneCommande.idArticle}</p>
			    <div class="form-group">
			        <label>quantité</label>
					<input type="number" class="form-control w-50" name="quantite" value="${ligneCommande.qtyCommandee}" required>
				</div>
				<button type="submit" value="Valider" class="btn btn-primary mt-1">Valider</button>
		    	<a href="CommerceServlet?flag=retourCommande"><button type="button" value="Annuler" class="btn btn-secondary mt-1">Annuler</button></a>
			</form>	
		</div>
		
		
		
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	
</body>
</html>