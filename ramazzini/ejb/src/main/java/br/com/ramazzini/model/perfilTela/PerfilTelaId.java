package br.com.ramazzini.model.perfilTela;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PerfilTelaId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "cd_perfil")
	private Integer perfil;
	
	@Column(name = "cd_tela")
	private Integer tela;

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public Integer getTela() {
		return tela;
	}

	public void setTela(Integer tela) {
		this.tela = tela;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((tela == null) ? 0 : tela.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilTelaId other = (PerfilTelaId) obj;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (tela == null) {
			if (other.tela != null)
				return false;
		} else if (!tela.equals(other.tela))
			return false;
		return true;
	}


		
}