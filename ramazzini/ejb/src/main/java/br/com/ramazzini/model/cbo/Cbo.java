package br.com.ramazzini.model.cbo;

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

import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_cbo", sequenceName = "seq_cbo", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "cbo", uniqueConstraints = @UniqueConstraint(columnNames = "no_cbo"))
public class Cbo extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_cbo")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_cbo")
    private Long id;
    
    @Column(name = "no_cbo")
    @NotNull
    @Size(min = 1, max = 10)
    private String numero;
    
    @Column(name = "te_atividades", columnDefinition="TEXT")
    private String atividades;
    
	@OneToMany(mappedBy="cbo")
	private List<Funcao> funcoes;    
    
	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAtividades() {
		return atividades;
	}

	public void setAtividades(String atividades) {
		this.atividades = atividades;
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	
}