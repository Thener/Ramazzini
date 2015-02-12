package br.com.ramazzini.model.limitacoesDeficienciaMental;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.util.AbstractEntidade;
@SequenceGenerator(name = "seq_limitacoes_deficiencia_mental", sequenceName = "seq_limitacoes_deficiencia_mental", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "limitacoes_deficiencia_mental")
public class LimitacoesDeficienciaMental extends AbstractEntidade{
	private static final long serialVersionUID = 1L;

	 @Id
     @Column(name = "cd_limitacoes_deficiencia_mental")
     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_limitacoes_deficiencia_mental")
     private Long id;
	
	 @Column(name = "tp_limitacoes_deficiencia_mental", length = 2)
	 private String limitacoesDeficienciaMental;
	 
	public LimitacoesDeficienciaMentalEnum getLimitacoesDeficienciaMentalEnum() {
		return (this.limitacoesDeficienciaMental != null) ? LimitacoesDeficienciaMentalEnum.parse(this.limitacoesDeficienciaMental) : null;
	}
	
	public String getLimitacoesDeficienciaMental() {
		return limitacoesDeficienciaMental;
	}

	public void setLimitacoesDeficienciaMental(String limitacoesDeficienciaMental) {
		this.limitacoesDeficienciaMental = limitacoesDeficienciaMental;
	}

	@Override
	public Long getId() {
		return id;
	}	 
}
