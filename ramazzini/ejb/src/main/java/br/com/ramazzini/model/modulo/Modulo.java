package br.com.ramazzini.model.modulo;

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

import br.com.ramazzini.model.tela.Tela;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_modulo", sequenceName = "seq_modulo", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "modulo", uniqueConstraints = @UniqueConstraint(columnNames = "nm_modulo"))
public class Modulo extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_modulo")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_modulo")
    private Long id;
    
    @Column(name = "nm_modulo")
    @NotNull
    @Size(min = 1, max = 50)
    private String nome;
    
	@Column(name = "ic_ativo")
	@NotNull
	private boolean ativo = true;
	
	@OneToMany(mappedBy="modulo")
	private List<Tela> telas;	

	public Long getId() {
		return id;
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