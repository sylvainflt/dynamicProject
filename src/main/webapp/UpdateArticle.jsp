<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://sylvainfoucault.fr/tld/Navigation" prefix="nav" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page import= "dynamicProject.*" %>
<%@ page import= "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Update Article</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script type="module" src="js/validUpdateArticle.js"></script>
</head>
<body>
	<nav:Navigation accueil="Accueil.jsp" 
		seConnecter="Connexion.jsp" 
		sEnregistrer="Inscription.jsp"/>

	<h2 class="text-center mt-4">Bienvenue administrateur : ${user}</h2>
	
	<h3 class="text-center mt-4">Modification d'article</h3>
		
	<div class="col-5 mt-2 mx-auto">
		
		<form action="CommerceServlet?flag=supprimerImage" method="POST" id="suppImageForm">
			<div class="text-center" id="imagesDiv">
				<%
					Article article = (Article)request.getAttribute("article");
					Iterator<String> itIms = article.getImages().values().iterator();
					Iterator<String> itImsNames = article.getImages().keySet().iterator();
					while(itIms.hasNext() && itImsNames.hasNext()){
						String image = itIms.next();
						String imageName = itImsNames.next();
				%>
					<label for="<%= imageName %>"><img src="data:image/jpg;base64,<%= image %>" width="100" height="100"></img></label>
					<input type="checkbox" class="form-check-input" name="imagesChecks" id="<%= imageName %>" value="<%= imageName %>"> 
				<%
					}
				%>
				<button type="submit" value="Supprimer" class="btn btn-primary py-1" id="suppImagesButton">Supprimer Image</button>
			</div>		
		</form>	
		
		<form action="CommerceServlet?flag=articleModifieEnvoi" method="POST">
			<div class="form-group d-none">
		      	<label>id</label>
		        <input type="text" class="form-control" name="id" value="${article.id}">
		    </div>
			<div class="form-group">
		      	<label>Désignation</label>
		        <input type="text" class="form-control" name="designation" value="${article.designation}" required>
		    </div>
		       <div class="form-group">
		        <label>Prix unitaire</label>
				<input type="text" class="form-control" name="prixUnitaire" value="${article.prixUnitaire}" required>
			</div>
			<div class="form-group">
				<label>Quantité</label>
				<input type="number" class="form-control" name="quantite" value="${article.quantite}" required>
			</div>				
			<div class="form-group">
				<label>Catégorie</label>
				<select class="form-control" id="categorieSelect" name="categorie">		
			      	<c:forEach items="${categories}" var="categorie">			      		
				        <option 
				        	${categorie.id == article.idCategorie ? 'selected="selected"' : ''}
				        	class="text-info" value="${categorie.id}">${categorie.designation}
				        </option>				        				        	
				    </c:forEach>
				  </select>		      
			</div>	
			<div class="form-group">
				<label for="newImageFile1">Image 1</label>
				<input class="form-control" type="file" id="newImageFile1" name="newImageFile1">			      
			</div>
			<div class="form-group">
				<label for="newImageFile2">Image 2</label>
				<input class="form-control" type="file" id="newImageFile2" name="newImageFile2">			      
			</div>
			<div class="form-group">
				<label for="newImageFile3">Image 3</label>
				<input class="form-control" type="file" id="newImageFile3" name="newImageFile3">			      
			</div>
			
			<br/>			    			   
				
		    <button type="submit" value="Valider" class="btn btn-primary mt-1">Valider</button>
		    <a href="CommerceServlet?flag=retour"><button type="button" value="Annuler" class="btn btn-secondary mt-1">Annuler</button></a>
		</form>    
	</div>	

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>