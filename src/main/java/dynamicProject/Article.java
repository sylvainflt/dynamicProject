package dynamicProject;

public class Article {

	private int id;
	private String designation;
	private float prixUnitaire;
	private int quantite;
	private int idCategorie;
	private String categorie;
	
	public Article(String designation, float prixUnitaire, int quantite, int idCategorie) {
		super();
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
		this.idCategorie = idCategorie;
	}

	public Article(int id, String designation, float prixUnitaire, int quantite, int idCategorie, String categorie) {
		super();
		this.id = id;
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
		this.idCategorie = idCategorie;
		this.categorie = categorie;
	}

	public String getDesignation() {
		return designation;
	}

	public float getPrixUnitaire() {
		return prixUnitaire;
	}

	public int getQuantite() {
		return quantite;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public String getCategorie() {
		return categorie;
	}

	public int getId() {
		return id;
	}
	
	
	
}
