package br.com.ramazzini.model.perfil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.tela.Tela;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.model.util.AbstractEntidade;

@Entity
@XmlRootElement
@Table(name = "perfil", uniqueConstraints = @UniqueConstraint(columnNames = "nm_perfil"))
public class Perfil extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_perfil")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name = "nm_perfil")
    @NotNull
    @Size(min = 1, max = 50)
    private String nome;
    
	@Column(name = "ic_ativo")
	@NotNull
	private boolean ativo = true;
	
	@ManyToMany(mappedBy="perfis")
	private List<Usuario> usuarios;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	name="perfil_tela",
	joinColumns={@JoinColumn(name="cd_perfil")},
	inverseJoinColumns={@JoinColumn(name="cd_tela")})
	private Set<Tela> telas = new HashSet<Tela>();	
		
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<Tela> getTelas() {
		return telas;
	}

	public void setTelas(Set<Tela> telas) {
		this.telas = telas;
	}

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

}