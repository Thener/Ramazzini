package br.com.ramazzini.controller.util;

import java.io.Serializable;

import br.com.ramazzini.util.BreadCrumb;
import br.com.ramazzini.util.ControleAcesso;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	    
    private ControleAcesso controleAcesso = new ControleAcesso();
    
    private BreadCrumb breadCrumb = BreadCrumb.getInstance();
    
    private boolean somenteLeitura = Boolean.FALSE;
    
	public ControleAcesso getControleAcesso() {
		return controleAcesso;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}

	public BreadCrumb getBreadCrumb() {
		return breadCrumb;
	}

	public void setBreadCrumb(BreadCrumb breadCrumb) {
		this.breadCrumb = breadCrumb;
	}	
}