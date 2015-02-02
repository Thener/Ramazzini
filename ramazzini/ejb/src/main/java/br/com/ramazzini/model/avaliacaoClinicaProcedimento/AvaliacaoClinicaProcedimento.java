package br.com.ramazzini.model.avaliacaoClinicaProcedimento;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_avaliacao_clinica_procedimento", sequenceName = "seq_avaliacao_clinica_procedimento", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "avaliacao_clinica_procedimento")
public class AvaliacaoClinicaProcedimento extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_avaliacao_clinica_procedimento")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_avaliacao_clinica_procedimento")
    private Long id;
    	
	@ManyToOne
	@NotNull
	@JoinColumn(name="cd_avaliacao_clinica")
	private AvaliacaoClinica avaliacaoClinica;	
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="cd_procedimento")
	private Procedimento procedimento;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_realizacao", columnDefinition = "Date")
	@NotNull
	private Date dataRealizacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_retorno", columnDefinition = "Date")
	private Date dataRetorno;
	
	@Column(name = "ic_resultado_procedimento", length = 3)
    private String resultadoProcedimento;
	
	@Column(name = "tp_alteracao", length = 3)
    private String tipoAlteracaoProcedimento;
	
	public Long getId() {
		return id;
	}	

	public AvaliacaoClinica getAvaliacaoClinica() {
		return avaliacaoClinica;
	}

	public void setAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica) {
		this.avaliacaoClinica = avaliacaoClinica;
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

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public String getResultadoProcedimento() {
		return resultadoProcedimento;
	}

	public void setResultadoProcedimento(String resultadoProcedimento) {
		this.resultadoProcedimento = resultadoProcedimento;
	}
	
	public ResultadoProcedimento getResultadoProcedimentoEnum() {
		return (this.resultadoProcedimento != null) ? ResultadoProcedimento.parse(this.resultadoProcedimento) : null;
	}

	public void setResultadoProcedimentoEnum(ResultadoProcedimento resultadoProcedimento) {
		setResultadoProcedimento(resultadoProcedimento.getValue());
	}	

	public String getTipoAlteracaoProcedimento() {
		return tipoAlteracaoProcedimento;
	}

	public void setTipoAlteracaoProcedimento(String tipoAlteracaoProcedimento) {
		this.tipoAlteracaoProcedimento = tipoAlteracaoProcedimento;
	}
	
	public TipoAlteracaoProcedimento getTipoAlteracaoProcedimentoEnum() {
		return (this.tipoAlteracaoProcedimento != null) ? TipoAlteracaoProcedimento.parse(this.tipoAlteracaoProcedimento) : null;
	}

	public void setTipoAlteracaoProcedimentoEnum(TipoAlteracaoProcedimento tipoAlteracaoProcedimento) {
		setTipoAlteracaoProcedimento(tipoAlteracaoProcedimento.getValue());
	}	
}