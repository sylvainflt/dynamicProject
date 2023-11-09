package dynamicProject;

public class Article {

	private int id;
	private String designation;
	private float prixUnitaire;
	private int quantite;
	private int idCategorie;
	
	public Article(String designation, float prixUnitaire, int quantite, int idCategorie) {
		super();
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
		this.idCategorie = idCategorie;
	}

	public Article(int id, String designation, float prixUnitaire, int quantite, int idCategorie) {
		super();
		this.id = id;
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
		this.idCategorie = idCategorie;
	}
	
	
	
}
