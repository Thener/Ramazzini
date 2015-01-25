package br.com.ramazzini.model.agenda;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_agenda", sequenceName = "seq_agenda", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "agenda")
public class Agenda extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_agenda")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_agenda")
    private Long id;
    
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cd_profissional")
	private Profissional profissional;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cd_procedimento")
	private Procedimento procedimento;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cd_funcionario")
	private Funcionario funcionario;	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_agenda", columnDefinition = "Date")
	private Date dataAgenda;	
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hr_agenda", columnDefinition = "timestamp without time zone")
	@NotNull
	private Date horaAgenda;	
	
	@Column(name = "st_marcacao_agenda", length = 2)
    @NotNull 
    private String situacaoMarcacaoAgenda;	
	
    @Column(name = "te_observacoes", length = 200)
    private String observacoes;

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataAgenda() {
		return dataAgenda;
	}

	public void setDataAgenda(Date dataAgenda) {
		this.dataAgenda = dataAgenda;
	}

	public Date getHoraAgenda() {
		return horaAgenda;
	}

	public void setHoraAgenda(Date horaAgenda) {
		this.horaAgenda = horaAgenda;
	}

	public String getSituacaoMarcacaoAgenda() {
		return situacaoMarcacaoAgenda;
	}

	public void setSituacaoMarcacaoAgenda(String situacaoMarcacaoAgenda) {
		this.situacaoMarcacaoAgenda = situacaoMarcacaoAgenda;
	}
	
	public SituacaoMarcacaoAgenda getSituacaoMarcacaoAgendaEnum() {
		return (this.situacaoMarcacaoAgenda != null) ? SituacaoMarcacaoAgenda.parse(this.situacaoMarcacaoAgenda) : null;
	}

	public void setSituacaoMarcacaoAgendaEnum(SituacaoMarcacaoAgenda situacaoMarcacaoAgenda) {
		setSituacaoMarcacaoAgenda(situacaoMarcacaoAgenda.getValue());
	}	

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Long getId() {
		return id;
	}
	
}