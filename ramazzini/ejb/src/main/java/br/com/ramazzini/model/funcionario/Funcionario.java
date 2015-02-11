package br.com.ramazzini.model.funcionario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.lotacao.Lotacao;
import br.com.ramazzini.model.util.AbstractEntidade;
import br.com.ramazzini.util.TimeFactory;

@SequenceGenerator(name = "seq_funcionario", sequenceName = "seq_funcionario", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "funcionario", uniqueConstraints = @UniqueConstraint(columnNames = "nm_funcionario"))
public class Funcionario extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_funcionario")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_funcionario")
    private Long id;
    
    @Column(name = "nm_funcionario", length = 100)
    @NotNull
    private String nome;
    
	@ManyToOne
	@NotNull
	@JoinColumn(name="cd_empresa")
	private Empresa empresa;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cd_funcao")
	private Funcao funcao;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cd_lotacao")
	private Lotacao lotacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_admissao", columnDefinition = "Date")
	private Date dataAdmissao;	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nascimento", columnDefinition = "Date")
	@NotNull
	private Date dataNascimento;	
	
	@Column(name = "no_ctps", length = 20)
	private String ctps;
	
	@Column(name = "no_nit", length = 20)
	private String nit;	
	
	@Column(name = "no_rg", length = 20)
	private String rg;
	
	@Column(name = "sg_sexo", length = 1)
    @NotNull 
    private String sexo;
	
	@Column(name = "st_funcionario", length = 2)
    @NotNull 
    private String situacaoFuncionario = SituacaoFuncionario.ATIVO.getValue();
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="cd_deficiencia")
	private Deficiencia deficiencia;
	
	@Transient
	private Integer idade;
	
	@Transient
	private String idadeTexto;
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Lotacao getLotacao() {
		return lotacao;
	}

	public void setLotacao(Lotacao lotacao) {
		this.lotacao = lotacao;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Sexo getSexoEnum() {
		return (this.sexo != null) ? Sexo.parse(this.sexo) : null;
	}

	public void setSexoEnum(Sexo sexo) {
		setSexo(sexo.getValue());
	}		

	public String getSituacaoFuncionario() {
		return situacaoFuncionario;
	}

	public void setSituacaoFuncionario(String situacaoFuncionario) {
		this.situacaoFuncionario = situacaoFuncionario;
	}

	public SituacaoFuncionario getSituacaoFuncionarioEnum() {
		return (this.situacaoFuncionario != null) ? SituacaoFuncionario.parse(this.situacaoFuncionario) : null;
	}

	public void setSituacaoFuncionarioEnum(SituacaoFuncionario situacaoFuncionario) {
		setSituacaoFuncionario(situacaoFuncionario.getValue());
	}
	
	public Integer getIdade() {
		if (dataNascimento != null) {
			idade = TimeFactory.calcularIdade(dataNascimento);
		}
		return idade;
	}

	public String getIdadeTexto() {
		if (dataNascimento != null) {
			idadeTexto = getIdade() + " ano(s)";
		}
		return idadeTexto;
	}	
	
	public Deficiencia getDeficiencia() {
		return deficiencia;
	}

	public void setDeficiencia(Deficiencia deficiencia) {
		this.deficiencia = deficiencia;
	}
}