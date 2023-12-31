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
	<title>connexion administrateur</title>
	<!--  <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/quartz/bootstrap.min.css" rel="stylesheet"> -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script type="module" src="js/validConnexionAdmin.js"></script>
</head>
<body>
	<nav:Navigation accueil="Accueil.jsp" 
		seConnecter="Connexion.jsp" 
		sEnregistrer="Inscription.jsp"/>

	<h2 class="text-center mt-4">Bienvenue administrateur : ${user}</h2>
		
	<div class="col-5 mt-4 mx-auto">
		<ul class="nav nav-tabs" role="tablist">
		  <li class="nav-item" role="presentation">
		    <a class="nav-link active" data-bs-toggle="tab" href="#listeArticles" aria-selected="true" role="tab" tabindex="-1">Articles</a>
		  </li>
		  <li class="nav-item" role="presentation">
		    <a class="nav-link" data-bs-toggle="tab" href="#article" aria-selected="false" role="tab">Ajout Article</a>
		  </li>	  
		  <li class="nav-item" role="presentation">
		    <a class="nav-link" data-bs-toggle="tab" href="#categorie" aria-selected="false" role="tab">Categorie</a>
		  </li>
		</ul>
		<div id="myTabContent" class="tab-content">
		  <div class="tab-pane fade active show" id="listeArticles" role="tabpanel">
		  
		  	<form action="CommerceServlet?flag=selectCategorie" method="post">
		  		<div class="form-group">
			      <label for="listeCatSelect" class="form-label mt-2">Catégorie : </label>
			      <select class="rounded" id="listeCatSelect" name="listeCatSelect">		
			      	<option class="text-info" value="0">Toutes</option>
			      	<c:forEach items="${categories}" var="categorie">
				        <option class="text-info" value="${categorie.id}">${categorie.designation}</option>
				    </c:forEach>
				  </select>  
				  <button type="submit" value="Filtrer" class="btn btn-primary m-0 p-0">Filtrer</button>
			    </div>			    
		  	</form>
		  
		  	<form action="CommerceServlet?flag=suppressionArticles" method="post">
			  	<table class="table table-hover mt-2">
				  <thead>
				    <tr>
				      <th scope="col">Image</th>
				      <th scope="col">Désignation</th>
				      <th scope="col">Prix unitaire</th>
				      <th scope="col">Quantité</th>
				      <th scope="col">Catégorie</th>
				      <th scope="col" class="text-center"><button type="submit" class="btn btn-outline-primary p-0" id="suppButton">Supprimer</button></th>
				    </tr>
				  </thead>
				  <tbody>	
				  	
				  	<%
				  		List<Article> listeArticles = (List<Article>) request.getSession().getAttribute("listeArticles");
				  		Iterator<Article> itArticle = listeArticles.iterator();
				  		
				  		while(itArticle.hasNext()){
				  			Article article = itArticle.next();
				  	%>	    
					    <tr>
					      <td>
					      	<div class="text-center">
					      		<%
									Iterator<String> itIms = article.getImages().values().iterator();
									while(itIms.hasNext()){
										String image = itIms.next();
								%>
									<img src="data:image/jpg;base64,<%= image %>" width="20" height="20"></img>
								<%
									}
								%>	
							</div>	
					      </td>	
					      <td>
					      	<a href="CommerceServlet?flag=modifierArticle&id=<%= article.getId() %>" id="article<%= article.getId() %>">
					      		<%= article.getDesignation() %>
					      	</a>
					      </td>
					      <td><%=article.getPrixUnitaire()%></td>
					      <td><%=article.getQuantite()%></td>
					      <td><%=article.getCategorie()%></td>
					      <td class="text-center">
					      	<input class="form-check-input" type="checkbox" name="articlesIds" value="<%=article.getId()%>"/>
					      </td>
					    </tr>	
				    <%
				  		}
				    %>	   			   
				  </tbody>
				</table>
			</form>	
		  </div>
			
		  <div class="tab-pane fade" id="article" role="tabpanel">
		    
		    <form action="CommerceServlet?flag=ajoutArticleHibernate" method="POST">
			  <fieldset>
			   
			    <div class="form-group">
			      <label for="designation" class="form-label mt-2">Désignation</label>
			      <input type="text" class="form-control" id="designation" name="designation" autofocus="autofocus" required>		      
			    </div>
			    <div class="form-group">
			      <label for="prixUnitaire" class="form-label mt-1">Prix unitaire</label>
			      <input type="text" class="form-control" id="prixUnitaire" name="prixUnitaire" required>		      
			    </div>
			    <div class="form-group">
			      <label for="quantite" class="form-label mt-1">Quantité</label>
			      <input type="number" class="form-control" id="quantite" name="quantite" required>		      
			    </div>
			    <div class="form-group">
			      <label for="categorieSelect" class="form-label mt-1">Catégorie</label>
			      <select class="form-control" id="categorieSelect" name="categorie">		
			      	<c:forEach items="${categories}" var="categorie">
				        <option class="text-info" value="${categorie.id}">${categorie.designation}</option>
				    </c:forEach>
				  </select>  
			    </div>
			    <div class="form-group">
					<label for="imageFile1">Image 1</label>
					<input class="form-control" type="file" id="imageFile1" name="imageFile1">			      
				</div>
				<div class="form-group">
					<label for="imageFile2">Image 2</label>
					<input class="form-control" type="file" id="imageFile2" name="imageFile2">			      
				</div>
				<div class="form-group">
					<label for="imageFile3">Image 3</label>
					<input class="form-control" type="file" id="imageFile3" name="imageFile3">			      
				</div>
			    <br/>			    			   
			
			    <button type="submit" value="Valider" id="ajoutArticleButton" class="btn btn-primary mt-1">Valider</button>
			    <button type="reset" value="Annuler" class="btn btn-secondary mt-1">Annuler</button>
				
			  </fieldset>
			</form>
		    
		  </div>
		  
		  <div class="tab-pane fade" id="categorie" role="tabpanel">
		    
		    <form action="CommerceServlet?flag=suppressionCategories" method="post">
			    <table class="table table-hover mt-2">
				  <thead>
				    <tr>
				      <th scope="col">Désignation</th>
				      <th scope="col" class="text-center"><button type="submit" class="btn btn-outline-primary p-0" id="suppCatButton">Supprimer</button></th>
				    </tr>
				  </thead>
				  <tbody>	
				  	<c:forEach items="${categories}" var="categorie">		    
					    <tr>
					      <td>${categorie.designation}</a></td>				     
					      <td class="text-center">
					      	<input class="form-check-input" type="checkbox" name="categoriesIds" value="${categorie.id}"/>
					      </td>
					    </tr>	
				    </c:forEach>		   			   
				  </tbody>
				</table>
			</form>	
		    
		    <form action="CommerceServlet?flag=ajoutCategorie" method="POST">
			  <fieldset>
			   
			    <div class="form-group">
			      <label for="categorie" class="form-label mt-2">Ajout catégorie</label>
			      <input type="text" class="form-control" id="categorie" name="categorie" autofocus="autofocus" required>		      
			    </div>
			    
			    <br/>			    			   
			
			    <button type="submit" value="Valider" class="btn btn-primary mt-1">Valider</button>
			    <button type="reset" value="Annuler" class="btn btn-secondary mt-1">Annuler</button>			    			    			    
				
			  </fieldset>
			</form>
			
		  </div>	  
		</div>
		<p class="mt-4">${ resultat }</p>
		<p class="mt-2">${ !empty erreurs ? erreurs : "" }</p>
	</div>	
	
	<div class="modal" id="modalUpdateArticle">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Modifier Article</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="closeModal()">
	          <span aria-hidden="true"></span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<div class="form-group">
		      	<label>Désignation</label>
		        <input type="text" value="${article.designation}">
		    </div>
	        <div class="form-group">
		        <label>Prix unitaire</label>
				<input type="text" value="${article.prixUnitaire}">
			</div>
			<div class="form-group">
				<label>Quantité</label>
				<input type="text" value="${article.quantite}">
			</div>				
			<div class="form-group">
				<label>Catégorie</label>
				<input type="text" value="${article.categorie}">			      
			</div>	    
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary">Save changes</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="closeModal()">Close</button>
	      </div>
	    </div>
	  </div>	  
	</div>
	
	<script>
		function updateArticle(event){
			//event.preventDefault()
			modalUpdateArticle.style.display = "block"
		}
		function closeModal(){
			modalUpdateArticle.style.display = "none"
		}
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
</body>
</html>