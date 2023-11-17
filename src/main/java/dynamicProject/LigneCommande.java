package dynamicProject;

public class LigneCommande {

	private int id;
	private String article;
	private int quantite;
	
	public LigneCommande(int id, String article, int quantite) {
		super();
		this.id = id;
		this.article = article;
		this.quantite = quantite;
	}

	public int getId() {
		return id;
	}

	public String getArticle() {
		return article;
	}

	public int getQuantite() {
		return quantite;
	}
	
	
	
	
}
