package dynamicProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsers;
	private String lname;
	private String fname;
	private String adresse;
	private String tel;
	private Integer age;
	private String sexe;
	private int idCompte;
	
	public Users() {
		super();
	}
	
	public Users(String lname, String fname, String adresse, String tel, Integer age, String sexe) {
		super();
		this.lname = lname;
		this.fname = fname;
		this.adresse = adresse;
		this.tel = tel;
		this.age = age;
		this.sexe = sexe;
	}
	
	public Users(int idUsers, String lname, String fname, String adresse, String tel, Integer age, String sexe) {
		super();
		this.idUsers = idUsers;
		this.lname = lname;
		this.fname = fname;
		this.adresse = adresse;
		this.tel = tel;
		this.age = age;
		this.sexe = sexe;
	}
	
	public Users(int idUsers, String lname, String fname, String adresse, String tel, Integer age, String sexe,
			int idCompte) {
		super();
		this.idUsers = idUsers;
		this.lname = lname;
		this.fname = fname;
		this.adresse = adresse;
		this.tel = tel;
		this.age = age;
		this.sexe = sexe;
		this.idCompte = idCompte;
	}

	public int getIdUsers() {
		return idUsers;
	}

	public String getLname() {
		return lname;
	}

	public String getFname() {
		return fname;
	}

	public String getAdresse() {
		return adresse;
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

	public int getIdCompte() {
		return idCompte;
	}
	
	
	
	
}
