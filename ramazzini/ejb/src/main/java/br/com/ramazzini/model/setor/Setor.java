package br.com.ramazzini.model.setor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_setor", sequenceName = "seq_setor", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "setor")
public class Setor extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_setor")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_setor")
    private Long id;
    
    @Column(name = "nm_setor")
    @NotNull
    @Size(min = 1, max = 50)
    private String nome;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull
	@JoinColumn(name="cd_empresa")
	private Empresa empresa;
	
	@OneToMany(mappedBy="setor")
	private List<Funcao> funcoes;
	
	@Deprecated
    @Column(name = "cd_sistema_anterior", length = 20)
    private String idSistemaAnterior;	

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}