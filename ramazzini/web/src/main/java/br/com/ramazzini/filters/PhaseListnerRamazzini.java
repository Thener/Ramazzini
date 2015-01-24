package br.com.ramazzini.filters;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import org.primefaces.model.menu.DefaultMenuItem;

import br.com.ramazzini.service.entidade.EmpresaService;
import br.com.ramazzini.util.BreadCrumb;
import br.com.ramazzini.util.ControleAcesso;

public class PhaseListnerRamazzini implements PhaseListener {
	
	@Inject
	private BreadCrumb breadCrumb;
	
	@Inject
    private EmpresaService empresaService;
	
	
	private ControleAcesso controleAcesso = new ControleAcesso();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		if (breadCrumb != null){
			DefaultMenuItem item = new DefaultMenuItem();
	        String URL = getControleAcesso().getUriRequisicao();
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

	public void setControleAcesso(ControleAcesso controleAcesso) {
		this.controleAcesso = controleAcesso;
	}
	
	private static String getTela(String uri) {
		String partes[] = uri.split("/");
		return partes[ partes.length - 1 ];
	}
}
