package dynamicProject;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Connexion {

	private Connection cn = null;
	private Configuration configuration = new Configuration().configure();
	private SessionFactory sf = configuration.buildSessionFactory();
	private Session session;
	private Transaction tr;
	
	private static final String imagesPath = "C:/Users/59013-15-09/Downloads/";
	//private static final String imagesPath = "/home/sylvain/Images/";
	
	public Connection myCnx() {
		
		try {
			//Class.forName("org.mariadb.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			//cn = DriverManager.getConnection("jdbc:mariadb://localhost/commerce", "root", "");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/commerce", "root", "");
			//System.out.println("connexion réussie");
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
		
		this.cloturerConnexion();
		
		return mdp;
	}
	
	public Users getUser(String login) {
		
		Connection cnt = this.myCnx();
		Statement st;
		Users user = null;
		
		try {
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select u.idUsers, u.lname, u.fname, u.adresse, u.tel, u.age, u.sexe "
					+ "from users u, compte c "
					+ "where u.idCompte = c.idCompte "
					+ "and c.login like '"+login+"'");
			
			if(rs.next()) {
				user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getInt(6), rs.getString(7));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.cloturerConnexion();
		
		return user;
	}
	
	public Compte getCompte(String login) {
		
		Connection cnt = this.myCnx();
		Statement st;
		Compte compte = null;
		
		try {
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select c.idCompte, c.login, c.pwd, c.type "
					+ "from compte c "
					+ "where c.login like '"+login+"'");
			
			if(rs.next()) {
				compte = new Compte(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.cloturerConnexion();
		
		return compte;
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
		
		this.cloturerConnexion();
		
		return type;
	}
	
	public void inscrire(Users user, Compte compte) {
		
		Connection cnt = this.myCnx();
		
		PreparedStatement ps;
		Statement st;
		String idCompte = null;
		
		try {
			
			ps = cnt.prepareStatement("INSERT INTO compte (login, pwd, type) \n"
					+ "VALUES('"+compte.getLogin()+"','"+compte.getPwd()+"','"+compte.getType()+"')");
			ps.execute();	
			
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select idCompte from compte where login like '"+compte.getLogin()+"'");			
			if(rs.next()) {
				idCompte = rs.getString(1);
			}
			
			ps = cnt.prepareStatement("INSERT INTO users (fname, lname, adresse, tel, age, sexe, idCompte) \n"
					+ "VALUES('"+user.getFname()+"','"+user.getLname()+"','"+user.getAdresse()+"','"+user.getTel()+"','"+user.getAge()+"','"
							+user.getSexe()+"','"+idCompte+"')");
			ps.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		this.cloturerConnexion();
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
		this.cloturerConnexion();
	}
	
	public void ajouterArticle(Article a) {
		
		Connection cnt = this.myCnx();		
		PreparedStatement ps;
		Statement st;
		int idArticle = 0;
		
		try {
			
			ps = cnt.prepareStatement("INSERT INTO article (designation, pu, qty, idCategorie) \n"
					+ "VALUES('"+a.getDesignation()+"','"+a.getPrixUnitaire()+"','"+a.getQuantite()+"','"+a.getIdCategorie()+"')");
			ps.execute();
			
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select max(idArticle) from article");			
			if(rs.next()) {
				idArticle = rs.getInt(1);
			}
			
			Iterator<String> imageFilesIt = a.getImages().keySet().iterator();
			while(imageFilesIt.hasNext()) {	
				
				String imageFile = imageFilesIt.next();
				
				ps = cnt.prepareStatement("INSERT INTO image (name, img, idArticle) \n"
						+ "VALUES('"+imageFile+"', LOAD_FILE('"+imagesPath+imageFile+"'),"+idArticle+")");
				ps.execute();
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		this.cloturerConnexion();
	}
	
	public void ajouterArticleHibernate(Article1 a) {

		sf = configuration.buildSessionFactory();
		session = sf.openSession();
		tr = session.beginTransaction();
		
		session.persist(a);
		tr.commit();
		
		session.close();
		sf.close();
		
	}
	
	public void modifierArticle(Article a) {
		
		Connection cnt = this.myCnx();		
		PreparedStatement ps;
		
		try {			
			
			ps = cnt.prepareStatement("UPDATE article SET designation = '"+a.getDesignation()
								+"', pu = '"+a.getPrixUnitaire()+"', qty = '"+a.getQuantite()
								+"', idCategorie = '"+a.getIdCategorie()					
								+"' WHERE idArticle = "+a.getId());			
			ps.execute();		
			
			// s'il y a des fichiers images saisis
			if(!a.getImages().isEmpty()) {
				
				// on supprime d'abord les images existantes en base
				ps = cnt.prepareStatement("DELETE FROM image "
						+" WHERE idArticle = "+a.getId());			
				ps.execute();
				
				// puis on enregistre les nouvelles
				
				Iterator<String> imageFilesIt = a.getImages().keySet().iterator();
				while(imageFilesIt.hasNext()) {	
					
					String imageFile = imageFilesIt.next();
					
					ps = cnt.prepareStatement("INSERT INTO image (name, img, idArticle) \n"
							+ "VALUES('"+imageFile+"', LOAD_FILE('"+imagesPath+imageFile+"'),"+a.getId()+")");
					ps.execute();
				}
				
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		this.cloturerConnexion();
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
		
		this.cloturerConnexion();
		
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
					+ "where a.idCategorie = c.idCategorie "
					+ "order by a.idArticle");
			
			Article article = null;
					
			while(rs.next()) {
				
				article = new Article(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4),
						rs.getInt(5), rs.getString(6));
				
				
				List<Image> listeImages = getImages(rs.getInt(1));
				
				String imgDataBase64 = null;
						
				Iterator<Image> imageIt = listeImages.iterator();
				
				while(imageIt.hasNext()) {
					
					Image im = imageIt.next();
					
					if(im != null) {
			            Blob blob = im.getImgAsBlob(); //blob of image from db                     
						            
			            if(blob != null) {
				            imgDataBase64 = new String(Base64.getEncoder().encode(blob.getBytes(1,(int)blob.length())));
				            
				            article.getImages().put(im.getName(), imgDataBase64);
			            }
					}
	            }
				listeArticles.add(article);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.cloturerConnexion();
		return listeArticles;	
	}
	
	public List<Article> getListeArticles(int categorieId) {
		
		Connection cnt = this.myCnx();
		Statement st;
		List<Article> listeArticles = new ArrayList<Article>();
		
		try {
			
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select a.idArticle, a.designation, a.pu, a.qty, a.idCategorie, c.designation "
					+ "from article a, categorie c "
					+ "where a.idCategorie = c.idCategorie "
					+ "and a.idCategorie = "+categorieId);
			
			Article article = null;
					
			while(rs.next()) {
				
				article = new Article(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4),
						rs.getInt(5), rs.getString(6));
				
				
				List<Image> listeImages = getImages(rs.getInt(1));
				
				String imgDataBase64 = null;
						
				Iterator<Image> imageIt = listeImages.iterator();
				
				while(imageIt.hasNext()) {
					
					Image im = imageIt.next();
					
					if(im != null) {
			            Blob blob = im.getImgAsBlob(); //blob of image from db                     
						            
			            if(blob != null) {
				            imgDataBase64 = new String(Base64.getEncoder().encode(blob.getBytes(1,(int)blob.length())));
				            
				            article.getImages().put(im.getName(), imgDataBase64);
			            }
					}
	            }
				
				listeArticles.add(article);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.cloturerConnexion();
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
		
		this.cloturerConnexion();
		return a;	
		
	}
	
	public List<Image> getImages(int id) {
		
		Connection cnt = this.myCnx();
		Statement st;
		List<Image> listeImages = new ArrayList<Image>();
		
		try {
			
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select i.name, i.img, i.idArticle "
					+ "from image i"
					+ " where i.idArticle = "+id);
			
			while(rs.next()) {
				listeImages.add(new Image(rs.getString(1), rs.getBlob(2), rs.getInt(3)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.cloturerConnexion();
		return listeImages;	
		
	}
	
	public void supprimerArticle(String id) {

		Connection cnt = this.myCnx();		
		PreparedStatement ps;
		
		try {
			
			ps = cnt.prepareStatement("DELETE FROM image WHERE idArticle = "+id);
			ps.execute();
			
			ps = cnt.prepareStatement("DELETE FROM article WHERE idArticle = "+id);
			ps.execute();							
						
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		this.cloturerConnexion();
	}

	public void supprimerCategorie(String id) {
		
		Connection cnt = this.myCnx();		
		PreparedStatement ps;
		
		try {
			
			ps = cnt.prepareStatement("DELETE FROM categorie WHERE idCategorie = "+id);
			ps.execute();							
						
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		this.cloturerConnexion();
	}

	public void supprimerImage(String imageCheck) {

		Connection cnt = this.myCnx();		
		PreparedStatement ps;
		
		try {
			
			ps = cnt.prepareStatement("DELETE FROM image WHERE name like '"+imageCheck+"'");
			ps.execute();							
					
			cnt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		this.cloturerConnexion();
	}

	public void updateUser(Users user) {

		Connection cnt = this.myCnx();		
		PreparedStatement ps;
		
		try {			
			System.out.println("user.getId() "+user.getIdUsers());
			ps = cnt.prepareStatement("UPDATE users SET lname = '"+user.getLname()
								+"', fname = '"+user.getFname()
								+"', adresse = '"+user.getAdresse()
								+"', tel = '"+user.getTel()	
								+"', age = '"+user.getAge()	
								+"', sexe = '"+user.getSexe()	
								+"' WHERE idUsers = "+user.getIdUsers());			
			ps.execute();								
						
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		this.cloturerConnexion();
		
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

	public List<Commande> getListeCommandes() {
		Connection cnt = this.myCnx();
		Statement st;
		List<Commande> listeCommandes = new ArrayList<Commande>();
		
		try {
			
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select c.idCommande, c.dateCommande, c.idUsers, u.lname "
					+ "from commande c, users u "
					+ "where c.idUsers = u.idUsers");
			
			Commande commande = null;
					
			while(rs.next()) {
				
				commande = new Commande(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));												
				listeCommandes.add(commande);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.cloturerConnexion();
		return listeCommandes;
	}

	public List<LigneCommande> getLignesCommande(int id) {
		
		Connection cnt = this.myCnx();
		Statement st;
		List<LigneCommande> lignesCommande = new ArrayList<LigneCommande>();
		
		try {
			
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select lc.idLigneCommande, lc.idCommande, lc.idArticle, lc.qtyCommandee "
					+ "from ligneCommande lc "
					+ "where lc.idCommande = "+id);
			
			LigneCommande ligneCommande = null;
					
			while(rs.next()) {
				
				ligneCommande = new LigneCommande(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));												
				lignesCommande.add(ligneCommande);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.cloturerConnexion();
		return lignesCommande;
		
	}

	public LigneCommande getLigneCommande(int id) {
		
		session = sf.openSession();
		tr = session.beginTransaction();
		
		LigneCommande lc = session.get(LigneCommande.class, id);		
		
		tr.commit();
		session.close();
		
		return lc;
		
	}

	public void saveLigneCommande(int idLigneCommande, int quantite) {

		
		session = sf.openSession();
		tr = session.beginTransaction();
		
		LigneCommande lc = session.get(LigneCommande.class, idLigneCommande);
		lc.setQtyCommandee(quantite);
		session.persist(lc);
		
		tr.commit();
		session.close();
		
	}

	public List<Users> getListeUsers() {
		
		session = sf.openSession();
		tr = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Users.class);
		List<Users> lu = (List<Users>) criteria.list();		
		
		tr.commit();
		session.close();
		
		return lu;
		
	}

	public Commande getCommande(int id) {
		
		Connection cnt = this.myCnx();
		Statement st;
		Commande commande = null;
		
		try {
			
			st = cnt.createStatement();
			ResultSet rs = st.executeQuery("select c.idCommande, c.dateCommande, c.idUsers, u.lname "
					+ "from commande c, users u "
					+ "where c.idUsers = u.idUsers "
					+ "and c.idCommande = "+id);						
					
			if(rs.next()) {
				
				commande = new Commande(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));												
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.cloturerConnexion();
		return commande;
		
	}

	public void nouvelleLigneCommande(LigneCommande lc) {

		session = sf.openSession();
		tr = session.beginTransaction();
		
		session.persist(lc);		
		
		tr.commit();
		session.close();
		
	}

	public void supprimerLigneCommande(String coche) {

		session = sf.openSession();
		tr = session.beginTransaction();
		
		LigneCommande lc = session.get(LigneCommande.class, Integer.parseInt(coche));		
		session.delete(lc);
		
		tr.commit();
		session.close();
		
	}

	public void supprimerUser(String coche) {

		session = sf.openSession();
		tr = session.beginTransaction();
		
		Users u = session.get(Users.class, Integer.parseInt(coche));
		
		Compte c = session.get(Compte.class, u.getIdCompte());
		
		session.delete(u);
				
		session.delete(c);
		
		tr.commit();
		session.close();
		
	}

	

	

	

	
	
	
	
}
