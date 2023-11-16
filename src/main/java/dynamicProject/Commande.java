package dynamicProject;

public class Commande {

	private int idCommande;
	private String dateCommande;
	private int idUsers;
	
	public Commande(int idCommande, String dateCommande, int idUsers) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.idUsers = idUsers;
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
	
	
	
}
