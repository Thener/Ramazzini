package br.com.ramazzini.service.util;


public class Cliente {
	private String nome = "AMEMT";

	private String logradouro = "R BATISTA DE OLIVEIRA";

	private String numeroLogradouro = "239";

	private String complementoLogradouro = "ANDAR: 7 - SALA: 703 704";

	private String bairro = "CENTRO";

	private String cep = "36.013-300";

	private String cidade = "Juiz de Fora";

	private String unidadeFederativa = "MG";
	
	private String telefone1 = ""; 
	    
    private String telefone2 = "";

	public String getNome() {
		return nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public String getComplementoLogradouro() {
		return complementoLogradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getUnidadeFederativa() {
		return unidadeFederativa;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}  
	
	public String getEnderecoFormatado() {
		StringBuilder sb = new StringBuilder();
		return sb.append(logradouro).append(", ").append(numeroLogradouro).append(", ").append(bairro).toString();
	} 
}
