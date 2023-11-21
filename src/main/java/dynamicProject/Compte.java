package dynamicProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Compte {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCompte;
	private String login;
	private String pwd;
	private String type;
	
	public Compte() {
		super();
	}
	
	public Compte(String login, String pwd, String type) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.type = type;
	}
	
	public Compte(int idCompte, String login, String pwd, String type) {
		super();
		this.idCompte = idCompte;
		this.login = login;
		this.pwd = pwd;
		this.type = type;
	}

	public String getLogin() {
		return login;
	}

	public String getPwd() {
		return pwd;
	}

	public String getType() {
		return type;
	}

	public int getIdCompte() {
		return idCompte;
	}
	
	
	
	
}
