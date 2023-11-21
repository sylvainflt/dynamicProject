package dynamicProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LigneCommande {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLigneCommande;
	private int idCommande;
	private int idArticle;
	private int qtyCommandee;

	public LigneCommande() {
		super();
	}
	
	public LigneCommande(int idLigneCommande, int idCommande, int idArticle, int qtyCommandee) {
		super();
		this.idLigneCommande = idLigneCommande;
		this.idCommande = idCommande;
		this.idArticle = idArticle;
		this.qtyCommandee = qtyCommandee;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public int getIdLigneCommande() {
		return idLigneCommande;
	}

	public int getIdCommande() {
		return idCommande;
	}

	public int getQtyCommandee() {
		return qtyCommandee;
	}

	public void setQtyCommandee(int qtyCommandee) {
		this.qtyCommandee = qtyCommandee;
	}

	public void setIdLigneCommande(int idLigneCommande) {
		this.idLigneCommande = idLigneCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	
	
	
	
}
