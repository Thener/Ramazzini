package br.com.ramazzini.model.deficiencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.enquadramentoDeficiencia.EnquadramentoDeficiencia;
import br.com.ramazzini.model.limitacoesDeficienciaMental.LimitacoesDeficienciaMental;
import br.com.ramazzini.model.origemDeficiencia.OrigemDeficiencia;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
	name="deficiencia_origem",
	joinColumns={@JoinColumn(name="cd_deficiencia")},
	inverseJoinColumns={@JoinColumn(name="cd_origem_deficiencia")})
    private Set<OrigemDeficiencia> origensDeficiencia;	
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
	name="deficiencia_enquadramento",
	joinColumns={@JoinColumn(name="cd_deficiencia")},
	inverseJoinColumns={@JoinColumn(name="cd_enquadramento_deficiencia")})
    private Set<EnquadramentoDeficiencia> enquadramentoDeficiencia;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
	name="deficiencia_limitacoes",
	joinColumns={@JoinColumn(name="cd_deficiencia")},
	inverseJoinColumns={@JoinColumn(name="cd_limitacoes_deficiencia_mental")})
    private Set<LimitacoesDeficienciaMental> limitacoesDeficienciaMental;
    
    @Column(name = "ds_deficiencia", length = 200)
    private String descricaoDeficiencia;
    
    @Column(name = "ds_limitacoes_funcionais", length = 200)
    private String limitacoesFuncionais;
    
    @Override
	public Long getId() {
		return id;
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

	public Set<OrigemDeficiencia> getOrigensDeficiencia() {
		return origensDeficiencia;
	}

	public void setOrigensDeficiencia(Set<OrigemDeficiencia> origensDeficiencia) {
		this.origensDeficiencia = origensDeficiencia;
	}

	public Set<EnquadramentoDeficiencia> getEnquadramentoDeficiencia() {
		return enquadramentoDeficiencia;
	}

	public void setEnquadramentoDeficiencia(
			Set<EnquadramentoDeficiencia> enquadramentoDeficiencia) {
		this.enquadramentoDeficiencia = enquadramentoDeficiencia;
	}

	public Set<LimitacoesDeficienciaMental> getLimitacoesDeficienciaMental() {
		return limitacoesDeficienciaMental;
	}

	public void setLimitacoesDeficienciaMental(
			Set<LimitacoesDeficienciaMental> limitacoesDeficienciaMental) {
		this.limitacoesDeficienciaMental = limitacoesDeficienciaMental;
	}
}