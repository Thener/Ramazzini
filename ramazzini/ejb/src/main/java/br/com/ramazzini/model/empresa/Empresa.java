package br.com.ramazzini.model.empresa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.cnae.Cnae;
import br.com.ramazzini.model.embeddable.Endereco;
import br.com.ramazzini.model.empresaServico.EmpresaServico;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.grupo.Grupo;
import br.com.ramazzini.model.lotacao.Lotacao;
import br.com.ramazzini.model.responsavel.Responsavel;
import br.com.ramazzini.model.setor.Setor;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_empresa", sequenceName = "seq_empresa", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "empresa", uniqueConstraints = @UniqueConstraint(columnNames = "nm_empresa"))
public class Empresa extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_empresa")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_empresa")
    private Long id;
    
    @Column(name = "nm_empresa", length = 100)
    @NotNull
    private String nome;
    
    @Column(name = "nm_fantasia", length = 100)
    private String nomeFantasia;
    
	@Column(name = "st_empresa", length = 3)
    @NotNull 
    private String situacaoEmpresa;
	
	@Column(name = "tp_pessoa", length = 3)
    @NotNull 
    private String tipoPessoa;
	
	@Column(name = "tp_pcmso", length = 3)
    @NotNull 
    private String tipoPcmso;
	
	@Column(name = "no_cnpj", length = 18)
	private String cnpj;
	
	@Column(name = "no_cei", length = 20)
	private String cei;
	
	@Column(name = "no_inscricao_estadual", length = 20)
	private String inscricaoEstadual;	
	
	@Column(name = "no_cpf", length = 14)
	private String cpf;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cd_cnae")
	private Cnae cnae;	
	
	@Embedded
    private Endereco endereco = new Endereco(); 

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="logradouro",
		column=@Column(name="nm_logradouro_correspondencia")),
		@AttributeOverride(name="numeroLogradouro",
		column=@Column(name="no_logradouro_correspondencia")),
		@AttributeOverride(name="complementoLogradouro",
		column=@Column(name="no_complemento_correspondencia")),
		@AttributeOverride(name="bairro",
		column=@Column(name="nm_bairro_correspondencia")),
		@AttributeOverride(name="cep",
		column=@Column(name="no_cep_correspondencia")),
		@AttributeOverride(name="cidade",
		column=@Column(name="nm_cidade_correspondencia")),
		@AttributeOverride(name="unidadeFederativa",
		column=@Column(name="sg_uf_correspondencia")),
		@AttributeOverride(name="referenciaEndereco",
		column=@Column(name="te_referencia_endereco_correspondencia"))		
		})	
    private Endereco enderecoCorrespondencia = new Endereco(); 
    
    @Column(name = "em_empresa", length = 200)
    private String email;
    
    @Column(name = "tf_empresa1", length = 20)
    private String telefone1; 
    
    @Column(name = "tf_empresa2", length = 20)
    private String telefone2;    
	
    @Column(name = "nm_pessoa_contato", length = 50)
    private String pessoaContato;
    
    @Column(name = "nm_representante_legal", length = 100)
    private String nomeRepresentanteLegal;
    
    @Column(name = "no_nit_representante_legal", length = 20)
    private String nitRepresentanteLegal;
    
    @Column(name = "te_orientacoes_especificas", columnDefinition="TEXT")
    private String orientacoesEspecificas;    
	
    @Column(name = "te_observacoes_gerais", columnDefinition="TEXT")
    private String observacoesGerais;
    
	@OneToMany(mappedBy="empresa")
	private List<Responsavel> responsaveis; 
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cd_grupo")
	private Grupo grupo;
	
	@OneToMany(mappedBy="empresa")
	private List<Setor> setores;	
	
	@OneToMany(mappedBy="empresa")
	private List<Funcao> funcoes;	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<EmpresaServico> empresasServicos;	
	
	@OneToMany(mappedBy="empresa")
	private List<Lotacao> lotacoes;	
	
	@OneToMany(mappedBy="empresa")
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

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getSituacaoEmpresa() {
		return situacaoEmpresa;
	}

	public void setSituacaoEmpresa(String situacaoEmpresa) {
		this.situacaoEmpresa = situacaoEmpresa;
	}
	
	public SituacaoEmpresa getSituacaoEmpresaEnum() {
		return (this.situacaoEmpresa != null) ? SituacaoEmpresa.parse(this.situacaoEmpresa) : null;
	}

	public void setSituacaoEmpresaEnum(SituacaoEmpresa situacaoEmpresa) {
		setSituacaoEmpresa(situacaoEmpresa.getValue());
	}	

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	public TipoPessoa getTipoPessoaEnum() {
		return (this.tipoPessoa != null) ? TipoPessoa.parse(this.tipoPessoa) : null;
	}

	public void setTipoPessoaEnum(TipoPcmso tipoPessoa) {
		setTipoPessoa(tipoPessoa.getValue());
	}	

	public String getTipoPcmso() {
		return tipoPcmso;
	}

	public void setTipoPcmso(String tipoPcmso) {
		this.tipoPcmso = tipoPcmso;
	}
	
	public TipoPcmso getTipoPcmsoEnum() {
		return (this.tipoPcmso != null) ? TipoPcmso.parse(this.tipoPcmso) : null;
	}

	public void setTipoPcmsoEnum(TipoPcmso tipoPcmso) {
		setTipoPcmso(tipoPcmso.getValue());
	}	

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCei() {
		return cei;
	}

	public void setCei(String cei) {
		this.cei = cei;
	}
	
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Cnae getCnae() {
		return cnae;
	}

	public void setCnae(Cnae cnae) {
		this.cnae = cnae;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getPessoaContato() {
		return pessoaContato;
	}

	public void setPessoaContato(String pessoaContato) {
		this.pessoaContato = pessoaContato;
	}

	public String getNomeRepresentanteLegal() {
		return nomeRepresentanteLegal;
	}

	public void setNomeRepresentanteLegal(String nomeRepresentanteLegal) {
		this.nomeRepresentanteLegal = nomeRepresentanteLegal;
	}

	public String getNitRepresentanteLegal() {
		return nitRepresentanteLegal;
	}

	public void setNitRepresentanteLegal(String nitRepresentanteLegal) {
		this.nitRepresentanteLegal = nitRepresentanteLegal;
	}

	public String getOrientacoesEspecificas() {
		return orientacoesEspecificas;
	}

	public void setOrientacoesEspecificas(String orientacoesEspecificas) {
		this.orientacoesEspecificas = orientacoesEspecificas;
	}

	public String getObservacoesGerais() {
		return observacoesGerais;
	}

	public void setObservacoesGerais(String observacoesGerais) {
		this.observacoesGerais = observacoesGerais;
	}

	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	public List<EmpresaServico> getEmpresasServicos() {
		return empresasServicos;
	}

	public void setEmpresasServicos(List<EmpresaServico> empresasServicos) {
		this.empresasServicos = empresasServicos;
	}

	public List<Lotacao> getLotacoes() {
		return lotacoes;
	}

	public void setLotacoes(List<Lotacao> lotacoes) {
		this.lotacoes = lotacoes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEnderecoCorrespondencia() {
		return enderecoCorrespondencia;
	}

	public void setEnderecoCorrespondencia(Endereco enderecoCorrespondencia) {
		this.enderecoCorrespondencia = enderecoCorrespondencia;
	}    

	
}