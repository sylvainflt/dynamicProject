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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			
		} else {
			this.doGet(request, response);
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
	    request.setAttribute("categories", categories);
	    
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
					
					List<Categorie> categories = co.getCategories();
				    request.setAttribute("categories", categories);
					
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
