package br.com.ramazzini.model.empresaServico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmpresaServicoId implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Column(name = "cd_empresa_servico")
    private Long id;
	
	public EmpresaServicoId(){}
	
	public EmpresaServicoId(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		EmpresaServicoId other = (EmpresaServicoId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}