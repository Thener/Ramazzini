package br.com.ramazzini.model.funcao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.cbo.Cbo;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimento;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.riscoOcupacional.RiscoOcupacional;
import br.com.ramazzini.model.setor.Setor;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_funcao", sequenceName = "seq_funcao", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "funcao", uniqueConstraints = @UniqueConstraint(columnNames = "nm_funcao"))
public class Funcao extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_funcao")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_funcao")
    private Long id;
    
    @Column(name = "nm_funcao")
    @NotNull
    @Size(min = 1, max = 50)
    private String nome;
    
	@Column(name = "ic_ativa")
	@NotNull
	private boolean ativa = true;    
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="cd_empresa")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name="cd_setor")
	private Setor setor;	
	
	@ManyToOne
	@JoinColumn(name="cd_cbo")
	private Cbo cbo;
	
	@ManyToMany
	@JoinTable(
	name="funcao_risco_ocupacional",
	joinColumns={@JoinColumn(name="cd_funcao")},
	inverseJoinColumns={@JoinColumn(name="cd_risco_ocupacional")})
	private List<RiscoOcupacional> riscosOcupacionais = new ArrayList<RiscoOcupacional>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "funcao",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<FuncaoProcedimento> funcoesProcedimentos;
	
	@OneToMany(mappedBy="funcao")
	private List<Funcionario> funcionarios;		

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Cbo getCbo() {
		return cbo;
	}

	public void setCbo(Cbo cbo) {
		this.cbo = cbo;
	}

	public List<RiscoOcupacional> getRiscosOcupacionais() {
		return riscosOcupacionais;
	}

	public void setRiscosOcupacionais(List<RiscoOcupacional> riscosOcupacionais) {
		this.riscosOcupacionais = riscosOcupacionais;
	}

	public List<FuncaoProcedimento> getFuncoesProcedimentos() {
		return funcoesProcedimentos;
	}

	public void setFuncoesProcedimentos(
			List<FuncaoProcedimento> funcoesProcedimentos) {
		this.funcoesProcedimentos = funcoesProcedimentos;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}	

}