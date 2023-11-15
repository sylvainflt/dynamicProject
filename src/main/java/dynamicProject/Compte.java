package dynamicProject;

public class Compte {

	private int id;
	private String login;
	private String password;
	private String type;
	
	public Compte(String login, String password, String type) {
		super();
		this.login = login;
		this.password = password;
		this.type = type;
	}
	
	public Compte(int id, String login, String password, String type) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.type = type;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getType() {
		return type;
	}

	public int getId() {
		return id;
	}
	
	
	
	
}
