package br.com.ramazzini.controller;

import java.io.Serializable;

import br.com.ramazzini.util.ControleAcesso;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	    
    private ControleAcesso controleAcesso = new ControleAcesso();
    
	private boolean acaoInclusao = Boolean.FALSE;
	private boolean acaoAlteracao = Boolean.FALSE;
	private boolean acaoVisualizacao = Boolean.FALSE;    

	public ControleAcesso getControleAcesso() {
		return controleAcesso;
	}

	public boolean isAcaoInclusao() {
		return acaoInclusao;
	}

	public void setAcaoInclusao(boolean acaoInclusao) {
		this.acaoInclusao = acaoInclusao;
		this.acaoAlteracao = !acaoInclusao;
		this.acaoVisualizacao = !acaoInclusao;
	}

	public boolean isAcaoAlteracao() {
		return acaoAlteracao;
	}

	public void setAcaoAlteracao(boolean acaoAlteracao) {
		this.acaoAlteracao = acaoAlteracao;
		this.acaoInclusao = !acaoAlteracao;
		this.acaoVisualizacao = !acaoAlteracao;
	}

	public boolean isAcaoVisualizacao() {
		return acaoVisualizacao;
	}

	public void setAcaoVisualizacao(boolean acaoVisualizacao) {
		this.acaoVisualizacao = acaoVisualizacao;
		this.acaoInclusao = !acaoVisualizacao;
		this.acaoAlteracao = !acaoVisualizacao;
	}	
}