package br.com.ramazzini.model.endereco;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {
	@Column(name = "nm_logradouro")
	@Size(max = 100)
	private String logradouro;

	@Column(name = "no_logradouro")
	@Size(max = 20)
	private String numeroLogradouro;

	@Column(name = "no_complemento")
	@Size(max = 20)
	private String complementoLogradouro;

	@Column(name = "nm_bairro")
	@Size(max = 50)
	private String bairro;

	@Column(name = "no_cep")
	@Size(max = 10)
	private String cep;

	@Column(name = "nm_cidade")
	@Size(max = 100)
	private String cidade;

	@Column(name = "sg_uf")
	@Size(max = 2)
	private String unidadeFederativa;

	@Column(name = "te_referencia_endereco")
	@Size(max = 200)
	private String referenciaEndereco;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public String getComplementoLogradouro() {
		return complementoLogradouro;
	}

	public void setComplementoLogradouro(String complementoLogradouro) {
		this.complementoLogradouro = complementoLogradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUnidadeFederativa() {
		return unidadeFederativa;
	}

	public void setUnidadeFederativa(String unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
	}

	public String getReferenciaEndereco() {
		return referenciaEndereco;
	}

	public void setReferenciaEndereco(String referenciaEndereco) {
		this.referenciaEndereco = referenciaEndereco;
	}
}
