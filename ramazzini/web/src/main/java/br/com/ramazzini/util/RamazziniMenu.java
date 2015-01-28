package br.com.ramazzini.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;

public class RamazziniMenu extends DefaultMenuModel{

	private static final long serialVersionUID = 1L;
	
	public void addMenuItem() {	
		removerExcesso();
		DefaultMenuItem item = new DefaultMenuItem();
		String URI = getUriRequisicao();
		removerMenuItemIgual(URI);
		if (URI.equals("/index.xhtml") || URI.equals("/index.jsf")
				|| getNome(URI).equals("Home")
				|| getNome(URI).equals("Index")
				|| URI.equals("/pages/home/home.jsf")) {
			getElements().clear();
			addElement(new DefaultMenuItem("Home", "ui-icon-home", "/pages/home/home.jsf"));
		} else {			
			item.setValue(getNome(URI));
			item.setUrl(URI);
			addElement(item);
		}		
	}
	
	private String getUriRequisicao() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request.getRequestURI().substring(14);
	}
	
	private static String getNome(String uri) {
		StringBuilder sb = new StringBuilder();
		String partes[] = uri.split("[/|.]");
		if (partes != null && partes.length >= 2 && partes[ partes.length - 2 ] != null) { 
			String partes2[] = partes[ partes.length - 2 ].split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
			if (partes2 != null) {
				for (String splitCamelPalavra : partes2) {
					sb.append(splitCamelPalavra);
					sb.append(" ");
				}
			}
			sb.setCharAt(0, Character.toUpperCase(sb.toString().charAt(0)));
		}
		return sb.toString().trim();
	}

	private void removerExcesso() {
		int tamanho = getElements().size();			
		if (tamanho == 8){
			getElements().remove(1);
		}
	}
	
	private void removerMenuItemIgual(String URI) {		
		for(int i = 0; i < getElements().size(); i++){
			DefaultMenuItem menuItem = (DefaultMenuItem)getElements().get(i);
			if (menuItem.getUrl().equals(URI)){
				getElements().remove(getElements().get(i));
				--i;
			}
		}  
	}
}
