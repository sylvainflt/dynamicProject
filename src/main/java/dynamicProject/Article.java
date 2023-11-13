package dynamicProject;

public class Article {

	private int id;
	private String designation;
	private float prixUnitaire;
	private int quantite;
	private int idCategorie;
	private String categorie;
	private String imageFile;
	
	public Article(String designation, float prixUnitaire, int quantite, int idCategorie, String imageFile) {
		super();
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
		this.idCategorie = idCategorie;
		this.imageFile = imageFile;
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

	public Article(int id, String designation, float prixUnitaire, int quantite, int idCategorie, String categorie, String imageFile) {
		super();
		this.id = id;
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
		this.idCategorie = idCategorie;
		this.categorie = categorie;
		this.imageFile = imageFile;
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

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
	
	
	
}
