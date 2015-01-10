package br.com.ramazzini.model.funcao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.cbo.Cbo;
import br.com.ramazzini.model.empresa.Empresa;
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
	@JoinColumn(name="cd_empresa")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name="cd_setor")
	private Setor setor;	
	
	@ManyToOne
	@JoinColumn(name="cd_cbo")
	private Cbo cbo;

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
    
	

}