package br.com.ramazzini.model.funcionario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_deficiencia", sequenceName = "seq_deficiencia", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "deficiencia")
public class Deficiencia extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_deficiencia")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_deficiencia")
    private Long id;

    @Column(name = "tp_origem_deficiencia", length = 2)
    private String origemDeficiencia;
    
    @Column(name = "ds_deficiencia", length = 200)
    private String descricaoDeficiencia;
    
    @Column(name = "ds_limitacoes_funcionais", length = 200)
    private String limitacoesFuncionais;
    
    @Column(name = "tp_enquadramento_deficiencia", length = 4)
    private String enquadramentoDeficiencia;
    
    @Column(name = "tp_lim_defic_mental", length = 2)
    private String limitacoesDeficienciaMental;
    
    @Override
	public Long getId() {
		return id;
	}
	
    public String getOrigemDeficiencia() {
		return origemDeficiencia;
	}

	public void setOrigemDeficiencia(String origemDeficiencia) {
		this.origemDeficiencia = origemDeficiencia;
	}

	public OrigemDeficiencia getOrigemDeficienciaEnum() {
		return (this.origemDeficiencia != null) ? OrigemDeficiencia.parse(this.origemDeficiencia) : null;
	}

	public void setOrigemDeficienciaEnum(OrigemDeficiencia origemDeficiencia) {
		setOrigemDeficiencia(origemDeficiencia.getValue());
	}

	public String getDescricaoDeficiencia() {
		return descricaoDeficiencia;
	}

	public void setDescricaoDeficiencia(String descricaoDeficiencia) {
		this.descricaoDeficiencia = descricaoDeficiencia;
	}

	public String getLimitacoesFuncionais() {
		return limitacoesFuncionais;
	}

	public void setLimitacoesFuncionais(String limitacoesFuncionais) {
		this.limitacoesFuncionais = limitacoesFuncionais;
	}

	public String getEnquadramentoDeficiencia() {
		return enquadramentoDeficiencia;
	}

	public void setEnquadramentoDeficiencia(String enquadramentoDeficiencia) {
		this.enquadramentoDeficiencia = enquadramentoDeficiencia;
	}
	
	public EnquadramentoDeficiencia getEnquadramentoDeficienciaEnum() {
		return (this.enquadramentoDeficiencia != null) ? EnquadramentoDeficiencia.parse(this.enquadramentoDeficiencia) : null;
	}

	public void setEnquadramentoDeficienciaEnum(EnquadramentoDeficiencia enquadramentoDeficiencia) {
		setEnquadramentoDeficiencia(enquadramentoDeficiencia.getValue());
	}

	public String getLimitacoesDeficienciaMental() {
		return limitacoesDeficienciaMental;
	}

	public void setLimitacoesDeficienciaMental(String limitacoesDeficienciaMental) {
		this.limitacoesDeficienciaMental = limitacoesDeficienciaMental;
	}
	
	public LimitacoesDeficienciaMental getLimitacoesDeficienciaMentalEnum() {
		return (this.limitacoesDeficienciaMental != null) ? LimitacoesDeficienciaMental.parse(this.limitacoesDeficienciaMental) : null;
	}

	public void setLimitacoesDeficienciaMentalEnum(LimitacoesDeficienciaMental limitacoesDeficienciaMental) {
		setLimitacoesDeficienciaMental(limitacoesDeficienciaMental.getValue());
	}
	
}