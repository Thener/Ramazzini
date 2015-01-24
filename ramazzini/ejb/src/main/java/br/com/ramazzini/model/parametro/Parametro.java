package br.com.ramazzini.model.parametro;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_parametro", sequenceName = "seq_parametro", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "parametro", uniqueConstraints = @UniqueConstraint(columnNames = "nm_parametro"))
public class Parametro extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_parametro")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_parametro")
    private Long id;
    
    @Column(name = "nm_parametro", length = 100)
    @NotNull
    private String nome;
    
    @Column(name = "vl_parametro", length = 100)
    @NotNull
    private String valor;
    
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}