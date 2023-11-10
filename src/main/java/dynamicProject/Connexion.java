package dynamicProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connexion {

	private Connection cn = null;
	
	public Connection myCnx() {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mariadb://localhost/projetCommerce", "root", "");
			System.out.println("connexion réussie");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
	
	public void cloturerConnexion() {
		try {
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String verifierCoordonnees(String login) {
		
		Connection cnt = this.myCnx();
		Statement st;
		String mdp = null;
		
		try {
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select pwd from compte where login like '"+login+"'");
			
			if(rs.next()) {
				mdp = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mdp;
	}
	
	public String getType(String login) {
		
		Connection cnt = this.myCnx();
		Statement st;
		String type = null;
		
		try {
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select type from compte where login like '"+login+"'");
			
			if(rs.next()) {
				type = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return type;
	}
	
	public void inscrire(User user, Compte compte) {
		
		Connection cnt = this.myCnx();
		
		PreparedStatement ps;
		Statement st;
		String idCompte = null;
		
		try {
			
			ps = cnt.prepareStatement("INSERT INTO compte (login, pwd, type) \n"
					+ "VALUES('"+compte.getLogin()+"','"+compte.getPassword()+"','"+compte.getType()+"')");
			ps.execute();	
			
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select idCompte from compte where login like '"+compte.getLogin()+"'");			
			if(rs.next()) {
				idCompte = rs.getString(1);
			}
			
			ps = cnt.prepareStatement("INSERT INTO users (fname, lname, adresse, tel, age, sexe, idCompte) \n"
					+ "VALUES('"+user.getFname()+"','"+user.getLname()+"','"+user.getAdress()+"','"+user.getTel()+"','"+user.getAge()+"','"
							+user.getSexe()+"','"+idCompte+"')");
			ps.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
	}
	
	public void ajouterCategorie(Categorie cat) {
		
		Connection cnt = this.myCnx();		
		PreparedStatement ps;
		
		try {
			
			ps = cnt.prepareStatement("INSERT INTO categorie (designation) \n"
					+ "VALUES('"+cat.getDesignation()+"')");
			ps.execute();							
						
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void ajouterArticle(Article a) {
		
		Connection cnt = this.myCnx();		
		PreparedStatement ps;
		
		try {
			
			ps = cnt.prepareStatement("INSERT INTO article (designation, pu, qty, idCategorie) \n"
					+ "VALUES('"+a.getDesignation()+"','"+a.getPrixUnitaire()+"','"+a.getQuantite()+"','"+a.getIdCategorie()+"')");
			ps.execute();							
						
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public List<Categorie> getCategories(){
		
		Connection cnt = this.myCnx();
		Statement st;
		List<Categorie> categories = new ArrayList<Categorie>();
		
		try {
			
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select idCategorie, designation from categorie");
			
			while(rs.next()) {
				categories.add(new Categorie(rs.getInt(1), rs.getString(2)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;		
		
	}
	
	public List<Article> getListeArticles() {
		Connection cnt = this.myCnx();
		Statement st;
		List<Article> listeArticles = new ArrayList<Article>();
		
		try {
			
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select a.idArticle, a.designation, a.pu, a.qty, a.idCategorie, c.designation "
					+ "from article a, categorie c "
					+ "where a.idCategorie = c.idCategorie");
			
			while(rs.next()) {
				listeArticles.add(new Article(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4),
						rs.getInt(5), rs.getString(6)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeArticles;	
	}
	
	public Article getArticle(int id) {
		
		Connection cnt = this.myCnx();
		Statement st;
		Article a = null;
		
		try {
			
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select a.idArticle, a.designation, a.pu, a.qty, a.idCategorie, c.designation "
					+ "from article a, categorie c "
					+ "where a.idCategorie = c.idCategorie and a.idArticle = "+id);
			
			if(rs.next()) {
				a = new Article(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4),
						rs.getInt(5), rs.getString(6));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;	
		
	}
	
	public void supprimerArticle(String id) {

		Connection cnt = this.myCnx();		
		PreparedStatement ps;
		
		try {
			
			ps = cnt.prepareStatement("DELETE FROM article WHERE idArticle = "+id);
			ps.execute();							
						
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	
	public static void main(String[] args) throws SQLException {
		Connexion cn = new Connexion();
		Connection cnt = cn.myCnx();
		
		Statement st = cnt.createStatement();
		String login = "admin";
		String password = "admin";
		ResultSet rs = st.executeQuery("select * from compte where login like '"+login+"' and pwd like '"+password+"'");
		/*
		while(rs.next()) {
			System.out.println("login : "+rs.getString("login")+" - password : "+rs.getString("pwd"));
		}
		*/
		if(rs.next())
		System.out.println("vous etes loggé");
		
		cn.cloturerConnexion();
	}

	

	
	
}
