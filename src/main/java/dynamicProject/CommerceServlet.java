package dynamicProject;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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
			
		} else {
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	}

	private void doModifierArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		Article a = co.getArticle(id);
		
		request.setAttribute("article", a);
		
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
			
		} else {
			this.doGet(request, response);
		}
	}

	private void doSupprimerCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] cochesCategories = request.getParameterValues("categoriesIds");
		
		if(cochesCategories != null && cochesCategories.length > 0) {
			for ( String coche : cochesCategories ) {
				co.supprimerCategorie(coche);
			}
			
			List<Categorie> categories = co.getCategories();
		    request.getSession().setAttribute("categories", categories);
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
		
		try {
			validationDesignationArticle(designation);
		} catch (Exception e) {
			erreurs.put(designation, e.getMessage());
		}
		
		if(erreurs.isEmpty()) {
			resultat = "Article modifié.";			
			Article a = new Article(id, designation, prixUnitaire, quantite, categorie, "");
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
		
		try {
			validationDesignationArticle(designation);
		} catch (Exception e) {
			erreurs.put(designation, e.getMessage());
		}
		
		if(erreurs.isEmpty()) {
			resultat = "Article ajoutée.";			
			Article a = new Article(designation, prixUnitaire, quantite, categorie);
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

	private void validationDesignationArticle(String designation) throws Exception {
		if(designation != null && designation.trim().length() < 3) {
			throw new Exception("Le champ doit être d'au moins 3 caractères");
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
					request.getRequestDispatcher("/connexionUser.jsp").forward(request, response);
				}
				
			}else {
				request.setAttribute("erreurPassword", "true");
				request.getRequestDispatcher("/Connexion.jsp").forward(request, response);
			}
		}
		
	}
	
	

}
