package br.com.ramazzini.model.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.ramazzini.model.util.AbstractEntidade;

@Entity
@XmlRootElement
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "nm_login"))
public class Usuario extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_usuario")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    /**
     * Nome do usuário.
     */
    @Column(name = "nm_usuario")
    @NotNull
    @Size(min = 1, max = 50)
    private String nome;
    
    /**
	 * Login do usuário.
	 */
    @Column(name = "nm_login")
    @NotNull
    @NotEmpty
    @Email
    private String login;
    
    /**
	 * Senha do usuario.
	 */
	@Column(name = "nm_senha", length = 200)
	@NotNull
	private String senha;
	
	/**
	 * Indica se o usuário está ativo/inativo.
	 */
	@Column(name = "ic_ativo")
	@NotNull
	private boolean ativo = true;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}	
}
