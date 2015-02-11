package br.com.ramazzini.model.origemDeficiencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.util.AbstractEntidade;
@SequenceGenerator(name = "seq_origem_def", sequenceName = "seq_origem_def", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "origem_deficiencia")
public class OrigemDeficiencia extends AbstractEntidade{
	private static final long serialVersionUID = 1L;

	 @Id
     @Column(name = "cd_origem_deficiencia")
     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_origem_def")
     private Long id;
	
	 @Column(name = "tp_origem_deficiencia", length = 2)
	 private String origemDeficiencia;
	 
	public String getOrigemDeficiencia() {
		return origemDeficiencia;
	}

	public void setOrigemDeficiencia(String origemDeficiencia) {
		this.origemDeficiencia = origemDeficiencia;
	}
	
	public OrigemDeficienciaEnum getOrigemDeficienciaEnum() {
		return (this.origemDeficiencia != null) ? OrigemDeficienciaEnum.parse(this.origemDeficiencia) : null;
	}
	
	@Override
	public Long getId() {
		return id;
	}	 
}
