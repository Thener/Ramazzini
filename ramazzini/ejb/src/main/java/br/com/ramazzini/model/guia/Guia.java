package br.com.ramazzini.model.guia;

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

import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.guiaProcedimento.GuiaProcedimento;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_guia", sequenceName = "seq_guia", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "guia")
public class Guia extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_guia")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_guia")
    private Long id;
    
	@ManyToOne
	@NotNull
	@JoinColumn(name="cd_funcionario")
	private Funcionario funcionario;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="cd_credenciado")
	private Credenciado credenciado;
	
	@Column(name = "st_guia", length = 2)
    @NotNull 
    private String situacaoGuia = SituacaoGuia.EMITIDA.getValue();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_emissao", columnDefinition = "Date")
	@NotNull 
	private Date dataEmissao;
	
	@OneToMany(mappedBy="guia",
			cascade = CascadeType.ALL, orphanRemoval=true)
	private List<GuiaProcedimento> procedimentos;	

	public Long getId() {
		return id;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Credenciado getCredenciado() {
		return credenciado;
	}

	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}

	public String getSituacaoGuia() {
		return situacaoGuia;
	}

	public void setSituacaoGuia(String situacaoGuia) {
		this.situacaoGuia = situacaoGuia;
	}
	
	public SituacaoGuia getSituacaoGuiaEnum() {
		return (this.situacaoGuia != null) ? SituacaoGuia.parse(this.situacaoGuia) : null;
	}

	public void setSituacaoGuiaEnum(SituacaoGuia situacaoGuia) {
		setSituacaoGuia(situacaoGuia.getValue());
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public List<GuiaProcedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<GuiaProcedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}
	
	
}