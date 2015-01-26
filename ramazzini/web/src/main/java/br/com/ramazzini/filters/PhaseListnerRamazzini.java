package br.com.ramazzini.filters;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.primefaces.model.menu.DefaultMenuItem;

import br.com.ramazzini.util.BreadCrumb;
import br.com.ramazzini.util.ControleAcesso;

public class PhaseListnerRamazzini implements PhaseListener {
	
	
	private BreadCrumb breadCrumb = BreadCrumb.getInstance();
	
	private ControleAcesso controleAcesso = new ControleAcesso();
	
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
			addMenuItem();	   
			
	}

	private void addMenuItem() {
		// recupera a lista de menu item da sessao
		// adiciona a correnete
		//salva lista atualizada na sessão 
		DefaultMenuItem item = new DefaultMenuItem();
		String URL = getControleAcesso().getUriRequisicao();
		if (URL.equals("/index.xhtml") || URL.equals("/index.jsf") || getTela(URL).equals("home.jsf")){
			breadCrumb.clear();
			breadCrumb.addItemInicial();
		} else {			
			item.setValue(getTela(URL));
			item.setUrl(URL);
			breadCrumb.addItem(item);
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;		
	}
	
	public ControleAcesso getControleAcesso() {
		return controleAcesso;
	}
	
	private static String getTela(String uri) {
		String partes[] = uri.split("/");
		return partes[ partes.length - 1 ];
	}
}
