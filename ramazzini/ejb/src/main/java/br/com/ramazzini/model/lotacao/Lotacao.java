package br.com.ramazzini.model.lotacao;

import java.io.Serializable;
import java.util.List;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.cnae.Cnae;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.empresa.TipoPcmso;
import br.com.ramazzini.model.empresa.TipoPessoa;
import br.com.ramazzini.model.empresa.UnidadeFederativa;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_lotacao", sequenceName = "seq_lotacao", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "lotacao")
public class Lotacao extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_lotacao")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_lotacao")
    private Long id;
    
    @Column(name = "nm_lotacao")
    @NotNull
    @Size(min = 1, max = 100)
    private String nome;
    
	@ManyToOne
	@NotNull
	@JoinColumn(name="cd_empresa")
	private Empresa empresa;	    
    
	@Column(name = "ic_ativa")
    @NotNull 
    private boolean ativa = Boolean.TRUE;
	
	@Column(name = "tp_pessoa", length = 3)
    @NotNull 
    private String tipoPessoa;
	
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
        
    @Column(name = "tf_lotacao")
    @Size(max = 20)
    private String telefone; 
    
    @Column(name = "nm_pessoa_contato")
    @Size(max = 50)
    private String pessoaContato;
    
    @Column(name = "te_orientacoes_especificas", columnDefinition="TEXT")
    private String orientacoesEspecificas; 
    
	@OneToMany(mappedBy="lotacao")
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPessoaContato() {
		return pessoaContato;
	}

	public void setPessoaContato(String pessoaContato) {
		this.pessoaContato = pessoaContato;
	}

	public String getOrientacoesEspecificas() {
		return orientacoesEspecificas;
	}

	public void setOrientacoesEspecificas(String orientacoesEspecificas) {
		this.orientacoesEspecificas = orientacoesEspecificas;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	
}