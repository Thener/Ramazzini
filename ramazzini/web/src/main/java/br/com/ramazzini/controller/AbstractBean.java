package br.com.ramazzini.controller;

import java.io.Serializable;

import br.com.ramazzini.util.ControleAcesso;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	    
    private ControleAcesso controleAcesso = new ControleAcesso();
    
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
}