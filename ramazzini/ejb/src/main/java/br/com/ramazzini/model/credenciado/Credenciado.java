package br.com.ramazzini.model.credenciado;

import java.io.Serializable;
import java.util.Collection;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.empresa.UnidadeFederativa;
import br.com.ramazzini.model.procedimentoCredenciado.ProcedimentoCredenciado;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_credenciado", sequenceName = "seq_credenciado", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "credenciado", uniqueConstraints = @UniqueConstraint(columnNames = "nm_credenciado"))
public class Credenciado extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_credenciado")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_credenciado")
    private Long id;
    
    @Column(name = "nm_credenciado")
    @NotNull
    @Size(min = 1, max = 100)
    private String nome;
    
	@Column(name = "ic_ativo")
	@NotNull
	private boolean ativo = Boolean.TRUE;    
    
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
    
    @Column(name = "te_referencia_endereco")
    @Size(max = 200)
	private String referenciaEndereco;   
    
    @Column(name = "te_horario_atendimento")
    @Size(max = 200)
	private String horarioAtendimento;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "credenciado",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Collection<ProcedimentoCredenciado> procedimentosCredenciados;    

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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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

	public String getReferenciaEndereco() {
		return referenciaEndereco;
	}

	public void setReferenciaEndereco(String referenciaEndereco) {
		this.referenciaEndereco = referenciaEndereco;
	}

	public String getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(String horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public Collection<ProcedimentoCredenciado> getProcedimentosCredenciados() {
		return procedimentosCredenciados;
	}

	public void setProcedimentosCredenciados(
			Collection<ProcedimentoCredenciado> procedimentosCredenciados) {
		this.procedimentosCredenciados = procedimentosCredenciados;
	} 
	
	
        
}