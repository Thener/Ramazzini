package br.com.ramazzini.model.avaliacaoClinica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.anamnese.Anamnese;
import br.com.ramazzini.model.avaliacaoClinicaProcedimento.AvaliacaoClinicaProcedimento;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.model.util.AbstractEntidade;
import br.com.ramazzini.util.TimeFactory;

@SequenceGenerator(name = "seq_avaliacao_clinica", sequenceName = "seq_avaliacao_clinica", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "avaliacao_clinica")
public class AvaliacaoClinica extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_avaliacao_clinica")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_avaliacao_clinica")
    private Long id;
    	
	@ManyToOne
	@NotNull
	@JoinColumn(name="cd_funcionario")
	private Funcionario funcionario;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="cd_profissional")
	private Profissional medico;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="cd_procedimento")
	private Procedimento procedimento;	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_realizacao", columnDefinition = "Date")
	@NotNull
	private Date dataRealizacao = TimeFactory.createDataHora();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_liberacao", columnDefinition = "Date")
	private Date dataLiberacao;	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_retorno", columnDefinition = "Date")
	private Date dataRetorno;
	
	@ManyToOne
	@JoinColumn(name="cd_funcao_anterior")
	private Funcao funcaoAnterior;	
	
	@ManyToOne
	@JoinColumn(name="cd_funcao_atual")
	@NotNull
	private Funcao funcaoAtual;	
	
    @Column(name = "te_anamnese", columnDefinition="TEXT")
    private String anamnese;
    
    @Column(name = "te_restricoes", columnDefinition="TEXT")
    private String restricoes;
    
    @Column(name = "te_motivo_inapto", columnDefinition="TEXT")
    private String motivoInapto;  
    
	@Column(name = "ic_alerta_proxima_avaliacao")
	private boolean alertaProximaAvaliacao = Boolean.FALSE; 
	
	@Column(name = "st_avaliacao_clinica", length = 3)
    @NotNull 
    private String situacaoAvaliacaoClinica;
	
	@OneToMany(mappedBy="avaliacaoClinica",
			cascade = CascadeType.ALL, orphanRemoval=true)
	private List<AvaliacaoClinicaProcedimento> procedimentos;	
	
	@OneToMany(mappedBy="avaliacaoClinica")
	private List<Anamnese> anamneses;	

	public Long getId() {
		return id;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Profissional getMedico() {
		return medico;
	}

	public void setMedico(Profissional medico) {
		this.medico = medico;
	}
	
	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public Date getDataLiberacao() {
		return dataLiberacao;
	}

	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public Funcao getFuncaoAnterior() {
		return funcaoAnterior;
	}

	public void setFuncaoAnterior(Funcao funcaoAnterior) {
		this.funcaoAnterior = funcaoAnterior;
	}

	public Funcao getFuncaoAtual() {
		return funcaoAtual;
	}

	public void setFuncaoAtual(Funcao funcaoAtual) {
		this.funcaoAtual = funcaoAtual;
	}

	public String getAnamnese() {
		return anamnese;
	}

	public void setAnamnese(String anamnese) {
		this.anamnese = anamnese;
	}

	public String getRestricoes() {
		return restricoes;
	}

	public void setRestricoes(String restricoes) {
		this.restricoes = restricoes;
	}

	public String getMotivoInapto() {
		return motivoInapto;
	}

	public void setMotivoInapto(String motivoInapto) {
		this.motivoInapto = motivoInapto;
	}

	public boolean isAlertaProximaAvaliacao() {
		return alertaProximaAvaliacao;
	}

	public void setAlertaProximaAvaliacao(boolean alertaProximaAvaliacao) {
		this.alertaProximaAvaliacao = alertaProximaAvaliacao;
	}

	public String getSituacaoAvaliacaoClinica() {
		return situacaoAvaliacaoClinica;
	}

	public void setSituacaoAvaliacaoClinica(String situacao) {
		this.situacaoAvaliacaoClinica = situacao;
	}
	
	public SituacaoAvaliacaoClinica getSituacaoAvaliacaoClinicaEnum() {
		return (this.situacaoAvaliacaoClinica != null) ? SituacaoAvaliacaoClinica.parse(this.situacaoAvaliacaoClinica) : null;
	}

	public void setSituacaoAvaliacaoClinicaEnum(SituacaoAvaliacaoClinica situacaoAvaliacaoClinica) {
		setSituacaoAvaliacaoClinica(situacaoAvaliacaoClinica.getValue());
	}

	public List<AvaliacaoClinicaProcedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<AvaliacaoClinicaProcedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}
	
}