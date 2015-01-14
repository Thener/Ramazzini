package br.com.ramazzini.model.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.ramazzini.model.empresa.UnidadeFederativa;

@Embeddable
public class Endereco {
	
	@Column(name = "nm_logradouro", length = 100)
	private String logradouro;

	@Column(name = "no_logradouro", length = 20)
	private String numeroLogradouro;

	@Column(name = "no_complemento", length = 20)
	private String complementoLogradouro;

	@Column(name = "nm_bairro", length = 50)
	private String bairro;

	@Column(name = "no_cep", length = 10)
	private String cep;

	@Column(name = "nm_cidade", length = 100)
	private String cidade;

	@Column(name = "sg_uf", length = 2)
	private String unidadeFederativa;

	@Column(name = "te_referencia_endereco", length = 200)
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
	
	public UnidadeFederativa getUnidadeFederativaEnum() {
		return (this.unidadeFederativa != null) ? UnidadeFederativa.parse(this.unidadeFederativa) : null;
	}

	public void setUnidadeFederativaEnum(UnidadeFederativa unidadeFederativa) {
		setUnidadeFederativa(unidadeFederativa.getValue());
	}	

	public String getReferenciaEndereco() {
		return referenciaEndereco;
	}

	public void setReferenciaEndereco(String referenciaEndereco) {
		this.referenciaEndereco = referenciaEndereco;
	}
}
