package br.com.ramazzini.model.grupo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_grupo", sequenceName = "seq_grupo", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "grupo", uniqueConstraints = @UniqueConstraint(columnNames = "nm_grupo"))
public class Grupo extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_grupo")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_grupo")
    private Long id;
    
    @Column(name = "nm_grupo")
    @NotNull
    @Size(min = 1, max = 100)
    private String nome;
    
    @Column(name = "nm_pessoa_contato")
    @Size(max = 50)
    private String pessoaContato;
    
    @Column(name = "tf_contato")
    @Size(max = 20)
    private String telefone;
    
    @Column(name = "em_grupo")
    @Size(max = 200)
    private String email;
    
	@OneToMany(mappedBy="grupo")
	private List<Empresa> empresas;    

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPessoaContato() {
		return pessoaContato;
	}

	public void setPessoaContato(String pessoaContato) {
		this.pessoaContato = pessoaContato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}    

    

}