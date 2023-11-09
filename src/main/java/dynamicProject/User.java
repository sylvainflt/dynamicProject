package dynamicProject;

public class User {

	private int id;
	private String lname;
	private String fname;
	private String adress;
	private String tel;
	private Integer age;
	private String sexe;
	private int idCompte;
	
	public User(String lname, String fname, String adress, String tel, Integer age, String sexe) {
		super();
		this.lname = lname;
		this.fname = fname;
		this.adress = adress;
		this.tel = tel;
		this.age = age;
		this.sexe = sexe;
	}

	public int getId() {
		return id;
	}

	public String getLname() {
		return lname;
	}

	public String getFname() {
		return fname;
	}

	public String getAdress() {
		return adress;
	}

	public String getTel() {
		return tel;
	}

	public Integer getAge() {
		return age;
	}

	public String getSexe() {
		return sexe;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}
	
	
	
	
}
