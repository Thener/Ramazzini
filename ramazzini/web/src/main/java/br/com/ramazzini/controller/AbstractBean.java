package br.com.ramazzini.controller;

import java.io.Serializable;

import br.com.ramazzini.util.ControleAcesso;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	    
    private ControleAcesso controleAcesso = new ControleAcesso();

	public ControleAcesso getControleAcesso() {
		return controleAcesso;
	}

}