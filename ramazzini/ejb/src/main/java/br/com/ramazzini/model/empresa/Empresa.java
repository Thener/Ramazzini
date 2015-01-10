package br.com.ramazzini.model.empresa;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.cnae.Cnae;
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
    
    @Column(name = "nm_empresa")
    @NotNull
    @Size(min = 1, max = 100)
    private String nome;
    
    @Column(name = "nm_fantasia")
    @Size(max = 100)
    private String nomeFantasia;
    
	@Column(name = "sg_situacao_empresa", length = 3)
    @NotNull 
    private String situacaoEmpresa;
	
	@Column(name = "sg_tipo_pessoa", length = 3)
    @NotNull 
    private String tipoPessoa;
	
	@Column(name = "sg_tipo_pcmso", length = 3)
    @NotNull 
    private String tipoPcmso;
	
	@Column(name = "no_cnpj", length = 18)
	private String cnpj;
	
	@Column(name = "no_cei", length = 20)
	private String cei;
	
	@Column(name = "no_cpf", length = 14)
	private String cpf;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cd_cnae")
	private Cnae cnae;	
	
    @Column(name = "nm_logradouro")
    @Size(max = 100)	
	private String logradouro;
	
    @Column(name = "no_logradouro")
    @Size(max = 20)    
	private String numeroLogradouro;
	
    @Column(name = "no_complemento")
    @Size(max = 20)
	private String complementoLogradouro;
	
    @Column(name = "nm_bairro")
    @Size(max = 50)    
	private String bairro;
	
    @Column(name = "no_cep")
    @Size(max = 10)    
	private String cep;
	
    @Column(name = "nm_cidade")
    @Size(max = 100)
	private String cidade;
	
    @Column(name = "sg_uf")
    @Size(max = 2)    
	private String unidadeFederativa;
    
    @Column(name = "nm_logradouro_correspondencia")
    @Size(max = 100)	
	private String logradouroCorrespondencia;
	
    @Column(name = "no_logradouro_correspondencia")
    @Size(max = 20)    
	private String numeroLogradouroCorrespondencia;
	
    @Column(name = "no_complemento_correspondencia")
    @Size(max = 20)
	private String complementoLogradouroCorrespondencia;
	
    @Column(name = "nm_bairro_correspondencia")
    @Size(max = 50)    
	private String bairroCorrespondencia;
	
    @Column(name = "no_cep_correspondencia")
    @Size(max = 10)    
	private String cepCorrespondencia;
	
    @Column(name = "nm_cidade_correspondencia")
    @Size(max = 100)
	private String cidadeCorrespondencia;
	
    @Column(name = "sg_uf_correspondencia")
    @Size(max = 2)
	private String unidadeFederativaCorrespondencia;    
    
    @Column(name = "em_empresa")
    @Size(max = 200)
    private String email;
    
    @Column(name = "tf_empresa1")
    @Size(max = 20)
    private String telefone1; 
    
    @Column(name = "tf_empresa2")
    @Size(max = 20)
    private String telefone2;    
	
    @Column(name = "nm_pessoa_contato")
    @Size(max = 50)
    private String pessoaContato;
    
    @Column(name = "nm_representante_legal")
    @Size(max = 100)
    private String nomeRepresentanteLegal;
    
    @Column(name = "no_nit_representante_legal")
    @Size(max = 20)
    private String nitRepresentanteLegal;
    
    @Column(name = "te_orientacoes_especificas", columnDefinition="TEXT")
    private String orientacoesEspecificas;    
	
    @Column(name = "te_observacoes_gerais", columnDefinition="TEXT")
    private String observacoesGerais;
    
	@OneToMany(mappedBy="empresa")
	private List<Responsavel> responsaveis; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cd_grupo")
	private Grupo grupo;
	
	@OneToMany(mappedBy="empresa")
	private List<Setor> setores;	
	
	@OneToMany(mappedBy="empresa")
	private List<Funcao> funcoes;	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Collection<EmpresaServico> empresasServicos;	
	
	@OneToMany(mappedBy="empresa")
	private List<Lotacao> lotacoes;	
	
	@OneToMany(mappedBy="empresa")
	private List<Funcionario> funcionarios;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public String getComplementoLogradouro() {
		return complementoLogradouro;
	}

	public void setComplementoLogradouro(String complementoLogradouro) {
		this.complementoLogradouro = complementoLogradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUnidadeFederativa() {
		return unidadeFederativa;
	}

	public void setUnidadeFederativa(String unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
	}
	
	public UnidadeFederativa getUnidadeFederativaEnum() {
		return (this.unidadeFederativa != null) ? UnidadeFederativa.parse(this.unidadeFederativa) : null;
	}

	public void setUnidadeFederativaEnum(UnidadeFederativa unidadeFederativa) {
		setUnidadeFederativa(unidadeFederativa.getValue());
	}	

	public String getLogradouroCorrespondencia() {
		return logradouroCorrespondencia;
	}

	public void setLogradouroCorrespondencia(String logradouroCorrespondencia) {
		this.logradouroCorrespondencia = logradouroCorrespondencia;
	}

	public String getNumeroLogradouroCorrespondencia() {
		return numeroLogradouroCorrespondencia;
	}

	public void setNumeroLogradouroCorrespondencia(
			String numeroLogradouroCorrespondencia) {
		this.numeroLogradouroCorrespondencia = numeroLogradouroCorrespondencia;
	}

	public String getComplementoLogradouroCorrespondencia() {
		return complementoLogradouroCorrespondencia;
	}

	public void setComplementoLogradouroCorrespondencia(
			String complementoLogradouroCorrespondencia) {
		this.complementoLogradouroCorrespondencia = complementoLogradouroCorrespondencia;
	}

	public String getBairroCorrespondencia() {
		return bairroCorrespondencia;
	}

	public void setBairroCorrespondencia(String bairroCorrespondencia) {
		this.bairroCorrespondencia = bairroCorrespondencia;
	}

	public String getCepCorrespondencia() {
		return cepCorrespondencia;
	}

	public void setCepCorrespondencia(String cepCorrespondencia) {
		this.cepCorrespondencia = cepCorrespondencia;
	}

	public String getCidadeCorrespondencia() {
		return cidadeCorrespondencia;
	}

	public void setCidadeCorrespondencia(String cidadeCorrespondencia) {
		this.cidadeCorrespondencia = cidadeCorrespondencia;
	}

	public String getUnidadeFederativaCorrespondencia() {
		return unidadeFederativaCorrespondencia;
	}

	public void setUnidadeFederativaCorrespondencia(
			String unidadeFederativaCorrespondencia) {
		this.unidadeFederativaCorrespondencia = unidadeFederativaCorrespondencia;
	}
	
	public UnidadeFederativa getUnidadeFederativaCorrespondenciaEnum() {
		return (this.unidadeFederativaCorrespondencia != null) ? 
				UnidadeFederativa.parse(this.unidadeFederativaCorrespondencia) : null;
	}

	public void setUnidadeFederativaCorrespondenciaEnum(UnidadeFederativa unidadeFederativa) {
		setUnidadeFederativaCorrespondencia(unidadeFederativa.getValue());
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

	public Collection<EmpresaServico> getEmpresasServicos() {
		return empresasServicos;
	}

	public void setEmpresasServicos(Collection<EmpresaServico> empresasServicos) {
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

	
}