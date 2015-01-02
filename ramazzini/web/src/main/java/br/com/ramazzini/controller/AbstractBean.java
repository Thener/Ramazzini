package br.com.ramazzini.controller;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ramazzini.util.ControleAcesso;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	    
    @Inject
    private ControleAcesso controleAcesso;

	public ControleAcesso getControleAcesso() {
		return controleAcesso;
	}    
    

}