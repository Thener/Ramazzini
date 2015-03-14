package br.com.ramazzini.model.guiaProcedimento;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.model.guia.Guia;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_guia_procedimento", sequenceName = "seq_guia_procedimento", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "guia_procedimento")
public class GuiaProcedimento extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_guia_procedimento")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_guia_procedimento")
    private Long id;
    	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull
	@JoinColumn(name="cd_guia")
	private Guia guia;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull
	@JoinColumn(name="cd_procedimento")
	private Procedimento procedimento;
	
	@Column(name = "qt_procedimento")
	@NotNull
	private Integer quantidade = 1;
	
	@Column(name = "vl_venda" , columnDefinition = "float8")
	@NotNull
	private BigDecimal precoVenda;
	
	@Column(name = "vl_custo" , columnDefinition = "float8")
	private BigDecimal precoCusto;
	
	@Transient
	private Credenciado credenciadoAuxiliar;

	public GuiaProcedimento(){}
	
	public GuiaProcedimento(Procedimento procedimento, 
			Integer quantidade, BigDecimal precoVenda, BigDecimal precoCusto, Credenciado credenciado) {
		
		this.procedimento = procedimento;
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.precoCusto = precoCusto;
		this.credenciadoAuxiliar = credenciado;
	}

	public Long getId() {
		return id;
	}
	
	public Guia getGuia() {
		return guia;
	}

	public void setGuia(Guia guia) {
		this.guia = guia;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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

	public Credenciado getCredenciadoAuxiliar() {
		return credenciadoAuxiliar;
	}

	public void setCredenciadoAuxiliar(Credenciado credenciadoAuxiliar) {
		this.credenciadoAuxiliar = credenciadoAuxiliar;
	}
	
}