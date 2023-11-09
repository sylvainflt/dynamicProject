package dynamicProject;

public class Categorie {

	private int id;
	private String designation;
	
	public Categorie(String designation) {
		super();
		this.designation = designation;
	}	
	
	public Categorie(int id, String designation) {
		super();
		this.id = id;
		this.designation = designation;
	}

	public String getDesignation() {
		return designation;
	}

	public int getId() {
		return id;
	}
	
	
	
}
