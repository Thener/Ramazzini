package br.com.ramazzini.model.perfil;

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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.perfilTela.PerfilTela;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_perfil", sequenceName = "seq_perfil", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "perfil", uniqueConstraints = @UniqueConstraint(columnNames = "nm_perfil"))
public class Perfil extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_perfil")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_perfil")
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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "perfil",
		cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Collection<PerfilTela> perfisTelas;
		
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Collection<PerfilTela> getPerfisTelas() {
		return perfisTelas;
	}

	public void setPerfisTelas(Collection<PerfilTela> perfisTelas) {
		this.perfisTelas = perfisTelas;
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