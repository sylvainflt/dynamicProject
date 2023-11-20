package dynamicProject;

public class Commande {

	private int idCommande;
	private String dateCommande;
	private int idUsers;
	private String user; 
	
	public Commande(int idCommande, String dateCommande, int idUsers, String user) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.idUsers = idUsers;
		this.user = user;
	}

	public int getIdCommande() {
		return idCommande;
	}

	public String getDateCommande() {
		return dateCommande;
	}

	public int getIdUsers() {
		return idUsers;
	}

	public String getUser() {
		return user;
	}
	
	
	
}
