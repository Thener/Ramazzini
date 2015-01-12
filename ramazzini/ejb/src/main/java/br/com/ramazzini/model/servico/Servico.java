package br.com.ramazzini.model.servico;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import br.com.ramazzini.model.empresaServico.EmpresaServico;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_servico", sequenceName = "seq_servico", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "servico", uniqueConstraints = @UniqueConstraint(columnNames = "nm_servico"))
public class Servico extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_servico")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_servico")
    private Long id;
    
    @Column(name = "nm_servico")
    @NotNull
    @Size(min = 1, max = 100)
    private String nome;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "servico",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Collection<EmpresaServico> empresasServicos;    

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}