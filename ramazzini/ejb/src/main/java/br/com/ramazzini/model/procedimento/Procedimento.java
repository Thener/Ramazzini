package br.com.ramazzini.model.procedimento;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimento;
import br.com.ramazzini.model.procedimentoCredenciado.ProcedimentoCredenciado;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_procedimento", sequenceName = "seq_procedimento", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "procedimento", uniqueConstraints = @UniqueConstraint(columnNames = "nm_procedimento"))
public class Procedimento extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_procedimento")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_procedimento")
    private Long id;
    
    @Column(name = "nm_procedimento", length = 100)
    @NotNull
    private String nome;
    
    @Column(name = "nm_abreviado", length = 50)
    private String nomeAbreviado;
    
    @Column(name = "sg_procedimento", length = 10)
    private String sigla;    
    
	@Column(name = "tp_procedimento", length = 5)
    @NotNull 
    private String tipoProcedimento = TipoProcedimento.EXAME_COMPLEMENTAR.getValue();
	
	@Column(name = "tp_exame_clinico", length = 3)
    private String tipoExameClinico;	
    
    @Column(name = "ic_sistema")
    @NotNull    
    private boolean sistema = false;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "procedimento",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<FuncaoProcedimento> funcoesProcedimentos;    
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "procedimento",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<ProcedimentoCredenciado> procedimentosCredenciados;    

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getTipoProcedimento() {
		return tipoProcedimento;
	}

	public void setTipoProcedimento(String tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento;
	}

	public TipoProcedimento getTipoProcedimentoEnum() {
		if (this.tipoProcedimento != null) {
			return TipoProcedimento.parse(this.tipoProcedimento);
		}
		return null;
	}

	public void setTipoProcedimentoEnum(TipoProcedimento tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento.getValue();
	}
	
	public String getTipoExameClinico() {
		return tipoExameClinico;
	}
	
	public void setTipoExameClinico(String tipoExameClinico) {
		this.tipoExameClinico = tipoExameClinico;
	}

	public TipoExameClinico getTipoExameClinicoEnum() {
		return (this.tipoExameClinico != null) ? TipoExameClinico.parse(this.tipoExameClinico) : null;
	}

	public void setTipoExameClinicoEnum(TipoExameClinico tipoExameClinico) {
		setTipoExameClinico(tipoExameClinico.getValue());
	}
	
	public boolean isSistema() {
		return sistema;
	}

	public void setSistema(boolean sistema) {
		this.sistema = sistema;
	}

	public List<FuncaoProcedimento> getFuncoesProcedimentos() {
		return funcoesProcedimentos;
	}

	public void setFuncoesProcedimentos(
			List<FuncaoProcedimento> funcoesProcedimentos) {
		this.funcoesProcedimentos = funcoesProcedimentos;
	}

	public List<ProcedimentoCredenciado> getProcedimentosCredenciados() {
		return procedimentosCredenciados;
	}

	public void setProcedimentosCredenciados(
			List<ProcedimentoCredenciado> procedimentosCredenciados) {
		this.procedimentosCredenciados = procedimentosCredenciados;
	}
    
    

}