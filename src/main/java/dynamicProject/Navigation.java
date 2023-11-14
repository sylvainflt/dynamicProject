package dynamicProject;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class Navigation extends TagSupport {

	private String accueil;
	private String seConnecter;
	private String sEnregistrer;
	
	public String getAccueil() {
		return accueil;
	}
	public void setAccueil(String accueil) {
		this.accueil = accueil;
	}
	public String getSeConnecter() {
		return seConnecter;
	}
	public void setSeConnecter(String seConnecter) {
		this.seConnecter = seConnecter;
	}
	public String getsEnregistrer() {
		return sEnregistrer;
	}
	public void setsEnregistrer(String sEnregistrer) {
		this.sEnregistrer = sEnregistrer;
	}
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print( "<nav class=\"navbar navbar-expand-lg bg-primary\" data-bs-theme=\"dark\">" );
			out.print( "<div class=\"container-fluid\">" );
			out.print( "<a class=\"navbar-brand\" href=\""+accueil+"\">Accueil</a>" );
			out.print( "<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarColor01\" aria-controls=\"navbarColor01\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">" );
			out.print( "<span class=\"navbar-toggler-icon\"></span>" );
			out.print( "</button>" );
			out.print( "<div class=\"collapse navbar-collapse\" id=\"navbarColor01\">" );
			out.print( "<ul class=\"navbar-nav me-auto\">" );
			out.print( "<li class=\"nav-item\">" );
			out.print( "<a class=\"nav-link active\" href=\""+seConnecter+"\">Se Connecter" );
			out.print( "<span class=\"visually-hidden\">(current)</span>" );
			out.print( "</a>" );
			out.print( "</li>" );
			out.print( "<li class=\"nav-item\">" );
			out.print( "<a class=\"nav-link\" href=\""+sEnregistrer+"\">S'enregistrer</a>" );
			out.print( "</li>" );
			out.print( "<li class=\"nav-item dropdown\">" );
			out.print( "<a class=\"nav-link dropdown-toggle\" data-bs-toggle=\"dropdown\" href=\"\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Actions</a>" );
			out.print( "<div class=\"dropdown-menu\">" );
			out.print( "<a class=\"dropdown-item\" href=\""+seConnecter+"\">Se Connecter</a>" );
			out.print( "<a class=\"dropdown-item\" href=\""+sEnregistrer+"\">S'enregistrer</a>" );
			out.print( "<div class=\"dropdown-divider\"></div>" );
			out.print( "<a class=\"dropdown-item\" href=\""+accueil+"\">Accueil</a>" );
			out.print( "</div>" );
			out.print( "</li>" );
			out.print( "</ul>" );
			out.print( "<form class=\"d-flex\">" );
			out.print( "<input class=\"form-control me-sm-2\" type=\"search\" placeholder=\"Search\">" );
			out.print( "<button class=\"btn btn-secondary my-2 my-sm-0\" type=\"submit\">Search</button>" );
			out.print( "</form>" );
			out.print( "</div>" );
			out.print( "</div>" );			
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 2;
	}
	
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.println("</nav>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 4;
	}
	
	@Override
	public void release() {
		this.accueil = "";
		this.seConnecter = "";
		this.sEnregistrer = "";
	}
	
	
	
}
