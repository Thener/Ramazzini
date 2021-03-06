package br.com.ramazzini.model.procedimentoCredenciado;

import java.io.Serializable;
import java.math.BigDecimal;

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
import javax.validation.constraints.NotNull;

import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.util.AbstractEntidade;


@Entity
@Table(name = "procedimento_credenciado")
public class ProcedimentoCredenciado extends AbstractEntidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_procedimento_credenciado", sequenceName = "seq_procedimento_credenciado", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_procedimento_credenciado")	
	private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @NotNull
    @JoinColumn(name="cd_procedimento")
    private Procedimento procedimento;
 
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull
    @JoinColumn(name="cd_credenciado")
    private Credenciado credenciado;

	@Column(name = "vl_venda" , columnDefinition = "float8")
	private BigDecimal precoVenda;
	
	@Column(name = "vl_custo" , columnDefinition = "float8")
	private BigDecimal precoCusto;
	
	public ProcedimentoCredenciado() {
	}

	public ProcedimentoCredenciado(Procedimento procedimento,
			Credenciado credenciado) {
		this.procedimento = procedimento;
		this.credenciado = credenciado;
	}

	public Long getId() {
		return id;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public Credenciado getCredenciado() {
		return credenciado;
	}

	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}		
	
}
