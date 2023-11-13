package dynamicProject;

import java.sql.Blob;

public class Image {

	private String name;
	private Blob imgAsBlob;
	private int idArticle;
	
	public Image(String name, Blob imgAsBlob, int idArticle) {
		super();
		this.name = name;
		this.imgAsBlob = imgAsBlob;
		this.idArticle = idArticle;
	}

	public String getName() {
		return name;
	}

	public Blob getImgAsBlob() {
		return imgAsBlob;
	}

	public int getIdArticle() {
		return idArticle;
	}
	
	
}
