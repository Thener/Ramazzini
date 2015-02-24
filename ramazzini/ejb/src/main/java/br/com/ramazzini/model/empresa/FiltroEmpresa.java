package br.com.ramazzini.model.empresa;

import java.io.Serializable;

public class FiltroEmpresa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int primeiroRegistro;
	
	private int quantidadeRegistros;
	
	private String propriedadeOrdenacao;
	
	private boolean ascendente;
	
	private String nome;

	public int getPrimeiroRegistro() {
		return primeiroRegistro;
	}

	public void setPrimeiroRegistro(int primeiroRegistro) {
		this.primeiroRegistro = primeiroRegistro;
	}

	public int getQuantidadeRegistros() {
		return quantidadeRegistros;
	}

	public void setQuantidadeRegistros(int quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
	}

	public String getPropriedadeOrdenacao() {
		return propriedadeOrdenacao;
	}

	public void setPropriedadeOrdenacao(String propriedadeOrdenacao) {
		this.propriedadeOrdenacao = propriedadeOrdenacao;
	}

	public boolean isAscendente() {
		return ascendente;
	}

	public void setAscendente(boolean ascendente) {
		this.ascendente = ascendente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}