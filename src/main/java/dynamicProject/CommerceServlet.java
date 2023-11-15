package dynamicProject;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Servlet implementation class CommerceServlet
 */
@WebServlet("/CommerceServlet")
public class CommerceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Connexion co = new Connexion();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommerceServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("flag").equals("modifierArticle")) {
			
			this.doModifierArticle(request, response);
			
		} else if(request.getParameter("flag").equals("retour")) {
			
			request.getRequestDispatcher("/connexionAdmin.jsp").forward(request, response);
			
		} else if(request.getParameter("flag").equals("allerAModifUser")) {
			
			request.getRequestDispatcher("/UpdateUser.jsp").forward(request, response);
			
		} else if(request.getParameter("flag").equals("retourUser")) {
			
			request.getRequestDispatcher("/connexionUser.jsp").forward(request, response);
			
		} else {
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	}

	private void doModifierArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		Article a = co.getArticle(id);
		
		
		
		try {
			
			List<Image> listeImages = co.getImages(id);
					
			String imgDataBase64 = null;
			
			Iterator<Image> imageIt = listeImages.iterator();
					
			while(imageIt.hasNext()) {
				
				Image im = imageIt.next();
				
				if(im != null) {
		            Blob blob = im.getImgAsBlob(); //blob of image from db                     
					            
		            if(blob != null) {
			            imgDataBase64 = new String(Base64.getEncoder().encode(blob.getBytes(1,(int)blob.length())));
			            
			            a.getImages().put(im.getName(), imgDataBase64);
		            }
				}
            }
            
			request.setAttribute("article", a);
			//request.setAttribute("image", imgDataBase64);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		request.getRequestDispatcher("/UpdateArticle.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("flag").equals("connect")) {
			
			this.doConnexion(request, response);
			
		} else if (request.getParameter("flag").equals("inscription")){
			
			this.doInscription(request, response);
			
		} else if (request.getParameter("flag").equals("ajoutCategorie")){
			
			this.doAjoutCategorie(request, response);
			
		} else if (request.getParameter("flag").equals("ajoutArticle")){
			
			this.doAjoutArticle(request, response);
			
		} else if (request.getParameter("flag").equals("suppressionArticles")){
			
			this.doSupprimerArticles(request, response);
			
		} else if (request.getParameter("flag").equals("articleModifieEnvoi")){
			
			this.doArticleModifieEnvoi(request, response);
			
		} else if (request.getParameter("flag").equals("suppressionCategories")){
			
			this.doSupprimerCategories(request, response);
			
		} else if (request.getParameter("flag").equals("selectCategorie")){
			
			this.doSelectCategories(request, response);
			
		} else if (request.getParameter("flag").equals("supprimerImage")){
			
			this.doSupprimerImage(request, response);
			
		} else if (request.getParameter("flag").equals("modifUser")){
			
			this.doModifierUser(request, response);
			
		} else {
			this.doGet(request, response);
		}
	}

	private void doModifierUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String lname = request.getParameter("nom");
		String fname = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		String tel = request.getParameter("tel");
		int age = Integer.parseInt(request.getParameter("age"));
		String sexe = request.getParameter("sex");
		
		User user = new User(lname, fname, adresse, tel, age, sexe);
		
		co.updateUser(user);
		
		request.getSession().setAttribute("user", user);
		
		request.getRequestDispatcher("/connexionUser.jsp").forward(request, response);
		
	}

	private void doSupprimerImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] imagesChecks = request.getParameterValues("imagesChecks");
		
		if(imagesChecks != null && imagesChecks.length > 0) {
			for ( String imageCheck : imagesChecks ) {
				co.supprimerImage(imageCheck);
			}
		}
		
		List<Article> listeArticles = co.getListeArticles();
	    
	    request.getSession().setAttribute("listeArticles", listeArticles);
		
		request.getRequestDispatcher("/connexionAdmin.jsp").forward(request, response);
	}

	private void doSelectCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int categorieId = Integer.parseInt(request.getParameter("listeCatSelect")); 
		
		List<Article> listeArticles;
		
		if(categorieId != 0) {
			listeArticles = co.getListeArticles(categorieId);
		} else {
			listeArticles = co.getListeArticles();
		}
	    
	    request.getSession().setAttribute("listeArticles", listeArticles);
		
		request.getRequestDispatcher("/connexionAdmin.jsp").forward(request, response);
		
	}

	private void doSupprimerCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] cochesCategories = request.getParameterValues("categoriesIds");
		
		if(cochesCategories != null && cochesCategories.length > 0) {
			for ( String coche : cochesCategories ) {
				co.supprimerCategorie(coche);
			}
			
			List<Categorie> categories = co.getCategories();
		    request.getSession().setAttribute("categories", categories);
		    request.setAttribute("resultat", "Catégorie(s) supprimée(s).");
		}
		
		request.getRequestDispatcher("/connexionAdmin.jsp").forward(request, response);
		
	}

	private void doArticleModifieEnvoi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();
				
		int id = Integer.parseInt(request.getParameter("id"));
		String designation = request.getParameter("designation");
		float prixUnitaire = Float.parseFloat(request.getParameter("prixUnitaire"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		int categorie = Integer.parseInt(request.getParameter("categorie"));
				
		String newImageFile1 = request.getParameter("newImageFile1");
		String newImageFile2 = request.getParameter("newImageFile2");
		String newImageFile3 = request.getParameter("newImageFile3");
		
		try {
			validationDesignationArticle(designation);
		} catch (Exception e) {
			erreurs.put(designation, e.getMessage());
		}
		
		if(erreurs.isEmpty()) {
			
			resultat = "Article modifié.";	
			
			Article a = new Article(id, designation, prixUnitaire, quantite, categorie, "");
			
			if(newImageFile1 != null && newImageFile1 != "") a.getImages().put(newImageFile1, "");
			if(newImageFile2 != null && newImageFile2 != "") a.getImages().put(newImageFile2, "");
			if(newImageFile3 != null && newImageFile3 != "") a.getImages().put(newImageFile3, "");
				
			co.modifierArticle(a);
			
		}else {
			resultat = "Échec de la modification.";
		}
		
		request.setAttribute("erreurs", erreurs);
		request.setAttribute("resultat", resultat);
		
		List<Article> listeArticles = co.getListeArticles();
	    request.getSession().setAttribute("listeArticles", listeArticles);
		
		request.getRequestDispatcher("/connexionAdmin.jsp").forward(request, response);
		
	}

	private void doSupprimerArticles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] cochesArticles = request.getParameterValues("articlesIds");
		
		if(cochesArticles != null && cochesArticles.length > 0) {
			for ( String coche : cochesArticles ) {
				co.supprimerArticle(coche);
			}
			
			List<Article> listeArticles = co.getListeArticles();
		    request.getSession().setAttribute("listeArticles", listeArticles);
		    request.setAttribute("resultat", "Article(s) supprimé(s).");
		}
		
		request.getRequestDispatcher("/connexionAdmin.jsp").forward(request, response);
		
	}

	private void doAjoutArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();
				
		String designation = request.getParameter("designation");
		float prixUnitaire = Float.parseFloat(request.getParameter("prixUnitaire"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		
		String imageFile1 = request.getParameter("imageFile1");
		String imageFile2 = request.getParameter("imageFile2");
		String imageFile3 = request.getParameter("imageFile3");
		
		try {
			validationDesignationArticle(designation);
			validationPhotoPresente(imageFile1, imageFile2, imageFile3);
		} catch (Exception e) {
			erreurs.put(designation, e.getMessage());
		}
		
		if(erreurs.isEmpty()) {
			resultat = "Article ajoutée.";			
			Article a = new Article(designation, prixUnitaire, quantite, categorie);
			
			if(imageFile1 != null && imageFile1 != "") a.getImages().put(imageFile1, "");
			if(imageFile2 != null && imageFile2 != "") a.getImages().put(imageFile2, "");
			if(imageFile3 != null && imageFile3 != "") a.getImages().put(imageFile3, "");
			
			co.ajouterArticle(a);
			
		}else {
			resultat = "Échec de l'ajout";
		}
		
		request.setAttribute("erreurs", erreurs);
		request.setAttribute("resultat", resultat);
		
		List<Article> listeArticles = co.getListeArticles();
	    request.getSession().setAttribute("listeArticles", listeArticles);
		
		request.getRequestDispatcher("/connexionAdmin.jsp").forward(request, response);
		
	}

	private void validationPhotoPresente(String imageFile1, String imageFile2, String imageFile3) throws Exception {

		if((imageFile1 == null || imageFile1 == "")
				&& (imageFile2 == null || imageFile2 == "") 
				&& (imageFile3 == null || imageFile3 == "")) {
			throw new Exception("Veuillez ajouter au moins une image.");
		}
		
	}

	private void validationDesignationArticle(String designation) throws Exception {
		if(designation != null && designation.trim().length() < 3) {
			throw new Exception("Le champ doit être d'au moins 3 caractères.");
		}
	}

	private void doAjoutCategorie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// sauvegarde du résultat de validation
		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();
				
		String designation = request.getParameter("categorie");
		
		try {
			validationDesignationCategorie(designation);
		} catch (Exception e) {
			erreurs.put(designation, e.getMessage());
		}
		
		if(erreurs.isEmpty()) {
			resultat = "Catégorie ajoutée.";			
			Categorie cat = new Categorie(designation);
			co.ajouterCategorie(cat);
			
		}else {
			resultat = "Échec de l'ajout";
		}
		
		request.setAttribute("erreurs", erreurs);
		request.setAttribute("resultat", resultat);
		
		List<Categorie> categories = co.getCategories();
	    request.getSession().setAttribute("categories", categories);
	    
		request.getRequestDispatcher("/connexionAdmin.jsp").forward(request, response);
		 
	}

	// Vérification du nom/prénom
	private void validationDesignationCategorie(String dc) throws Exception {
		if(dc != null && dc.trim().length() < 3) {
			throw new Exception("Le champ doit être d'au moins 3 caractères");
		}
	}
	
	private void doInscription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String lname = request.getParameter("lname");
			
			this.validationNom(lname);
			
			String fname = request.getParameter("fname");
			String adress = request.getParameter("adress");
			String tel = request.getParameter("tel");
			Integer age = Integer.valueOf(request.getParameter("age"));
			String sex = request.getParameter("sex");
			
			User user = new User(lname, fname, adress, tel, age, sex);
			
			String login = request.getParameter("login");
			String password = request.getParameter("password");
		
			Compte compte = new Compte(login, password, "s");
			
			co.inscrire(user, compte);
			
			request.getRequestDispatcher("/Connexion.jsp").forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			request.setAttribute("erreurLoginCourt", e.getMessage());
			request.getRequestDispatcher("/Inscription.jsp").forward(request, response);
		}
		
		
	}

	private void validationNom(String nom) throws Exception {
		if(nom != null && nom.trim().length() < 3) {
			throw new Exception("Le nom doit être d'au moins 3 caractères.");
		}
	}
	
	private void doConnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("erreurLogin", "false");
		request.setAttribute("erreurPassword", "false");
		
		String login = request.getParameter("user");
		String password = request.getParameter("password");
		
		String pwdBDD = co.verifierCoordonnees(login);
		System.out.println(pwdBDD);
		
		if(pwdBDD == null) {
			request.setAttribute("erreurLogin", "true");
			request.getRequestDispatcher("/Connexion.jsp").forward(request, response);
		}else {
			if(pwdBDD.equals(password)) {
				
				if(co.getType(login).equals("a")) {
					
					request.getSession().setAttribute("user", login);
					
					List<Categorie> categories = co.getCategories();
				    request.getSession().setAttribute("categories", categories);
				    
				    List<Article> listeArticles = co.getListeArticles();
				    
				    request.getSession().setAttribute("listeArticles", listeArticles);
					
					request.getRequestDispatcher("/connexionAdmin.jsp").forward(request, response);
				}else {
					
					request.getSession().setAttribute("user", co.getUser(login));
					
					request.getRequestDispatcher("/connexionUser.jsp").forward(request, response);
				}
				
			}else {
				request.setAttribute("erreurPassword", "true");
				request.getRequestDispatcher("/Connexion.jsp").forward(request, response);
			}
		}
		
	}
	
	

}
