package br.com.ramazzini.model.acao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.perfilTela.PerfilTela;
import br.com.ramazzini.model.tela.Tela;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_acao", sequenceName = "seq_acao", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "acao", uniqueConstraints = @UniqueConstraint(columnNames = "nm_acao"))
public class Acao extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_acao")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_acao")
    private Long id;
    
    @Column(name = "nm_acao")
    @NotNull
    @Size(min = 1, max = 50)
    private String nome;
    
	@Column(name = "ic_ativo")
	@NotNull
	private boolean ativo = true;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cd_tela", nullable=false)
	private Tela tela;	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(
	name="perfil_tela_acao",
	joinColumns={@JoinColumn(name="cd_acao")},
	inverseJoinColumns={@JoinColumn(name="cd_perfil_tela")})
	private List<PerfilTela> perfisTelas = new ArrayList<PerfilTela>();	
	
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

	public List<PerfilTela> getPerfisTelas() {
		return perfisTelas;
	}

	public void setPerfisTelas(List<PerfilTela> perfisTelas) {
		this.perfisTelas = perfisTelas;
	}

}