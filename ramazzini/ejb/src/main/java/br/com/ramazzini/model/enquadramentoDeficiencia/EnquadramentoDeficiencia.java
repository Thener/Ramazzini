package br.com.ramazzini.model.enquadramentoDeficiencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_enquadramento_deficiencia", sequenceName = "seq_enquadramento_deficiencia", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "enquadramento_deficiencia")
public class EnquadramentoDeficiencia extends AbstractEntidade{
	private static final long serialVersionUID = 1L;

	 @Id
     @Column(name = "cd_enquadramento_deficiencia")
     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_enquadramento_deficiencia")
     private Long id;
	
	 @Column(name = "tp_enquadramento_deficiencia", length = 4)
	 private String enquadramentoDeficiencia;
	 
	public EnquadramentoDeficienciaEnum getEnquadramentoDeficienciaEnum() {
		return (this.enquadramentoDeficiencia != null) ? EnquadramentoDeficienciaEnum.parse(this.enquadramentoDeficiencia) : null;
	}
	
	public String getEnquadramentoDeficiencia() {
		return enquadramentoDeficiencia;
	}

	public void setEnquadramentoDeficiencia(String enquadramentoDeficiencia) {
		this.enquadramentoDeficiencia = enquadramentoDeficiencia;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	 
	
}
