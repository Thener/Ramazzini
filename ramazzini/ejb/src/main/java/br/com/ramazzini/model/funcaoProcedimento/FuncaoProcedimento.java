package br.com.ramazzini.model.funcaoProcedimento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.util.AbstractEntidade;


@Entity
@Table(name = "funcao_procedimento")
public class FuncaoProcedimento extends AbstractEntidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_funcao_procedimento", sequenceName = "seq_funcao_procedimento", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_funcao_procedimento")	
	private Long id;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name="cd_funcao")
    private Funcao funcao;
 
	@ManyToOne
	@NotNull
    @JoinColumn(name="cd_procedimento")
    private Procedimento procedimento;
	
	@Column(name = "no_idade_exigencia")
	private Integer idadeExigencia;
	
	@Column(name = "ic_realiza_admissional")
	@NotNull
	private boolean realizaAdmissional = Boolean.FALSE;
	
	@Column(name = "ic_realiza_periodico")
	@NotNull
	private boolean realizaPeriodico = Boolean.FALSE;
	
	@Column(name = "ic_realiza_demissional")
	@NotNull
	private boolean realizaDemissional = Boolean.FALSE;
	
	@Column(name = "ic_realiza_mudanca_funcao")
	@NotNull
	private boolean realizaMudancaFuncao = Boolean.FALSE;
	
	@Column(name = "ic_realiza_retorno_trabalho")
	@NotNull
	private boolean realizaRetornoTrabalho = Boolean.FALSE;	
	
	@Column(name = "no_retorno_admissional")
	private Integer retornoAdmissional;
	
	@Column(name = "no_retorno_periodico")
	private Integer retornoPeriodico;
	
	@Column(name = "no_retorno_demissional")
	private Integer retornoDemissional;
	
	@Column(name = "no_retorno_mudanca_funcao")
	private Integer retornoMudancaFuncao;
	
	@Column(name = "no_retorno_retorno_trabalho")
	private Integer retornoRetornoTrabalho;	

	public Long getId() {
		return id;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public Integer getIdadeExigencia() {
		return idadeExigencia;
	}

	public void setIdadeExigencia(Integer idadeExigencia) {
		this.idadeExigencia = idadeExigencia;
	}

	public boolean isRealizaAdmissional() {
		return realizaAdmissional;
	}

	public void setRealizaAdmissional(boolean realizaAdmissional) {
		this.realizaAdmissional = realizaAdmissional;
	}

	public boolean isRealizaPeriodico() {
		return realizaPeriodico;
	}

	public void setRealizaPeriodico(boolean realizaPeriodico) {
		this.realizaPeriodico = realizaPeriodico;
	}

	public boolean isRealizaDemissional() {
		return realizaDemissional;
	}

	public void setRealizaDemissional(boolean realizaDemissional) {
		this.realizaDemissional = realizaDemissional;
	}

	public boolean isRealizaMudancaFuncao() {
		return realizaMudancaFuncao;
	}

	public void setRealizaMudancaFuncao(boolean realizaMudancaFuncao) {
		this.realizaMudancaFuncao = realizaMudancaFuncao;
	}

	public boolean isRealizaRetornoTrabalho() {
		return realizaRetornoTrabalho;
	}

	public void setRealizaRetornoTrabalho(boolean realizaRetornoTrabalho) {
		this.realizaRetornoTrabalho = realizaRetornoTrabalho;
	}

	public Integer getRetornoAdmissional() {
		return retornoAdmissional;
	}

	public void setRetornoAdmissional(Integer retornoAdmissional) {
		this.retornoAdmissional = retornoAdmissional;
	}

	public Integer getRetornoPeriodico() {
		return retornoPeriodico;
	}

	public void setRetornoPeriodico(Integer retornoPeriodico) {
		this.retornoPeriodico = retornoPeriodico;
	}

	public Integer getRetornoDemissional() {
		return retornoDemissional;
	}

	public void setRetornoDemissional(Integer retornoDemissional) {
		this.retornoDemissional = retornoDemissional;
	}

	public Integer getRetornoMudancaFuncao() {
		return retornoMudancaFuncao;
	}

	public void setRetornoMudancaFuncao(Integer retornoMudancaFuncao) {
		this.retornoMudancaFuncao = retornoMudancaFuncao;
	}

	public Integer getRetornoRetornoTrabalho() {
		return retornoRetornoTrabalho;
	}

	public void setRetornoRetornoTrabalho(Integer retornoRetornoTrabalho) {
		this.retornoRetornoTrabalho = retornoRetornoTrabalho;
	}
	
	
}
