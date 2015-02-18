package br.com.ramazzini.model.credenciado;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.embeddable.Endereco;
import br.com.ramazzini.model.procedimentoCredenciado.ProcedimentoCredenciado;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_credenciado", sequenceName = "seq_credenciado", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "credenciado", uniqueConstraints = @UniqueConstraint(columnNames = "nm_credenciado"))
public class Credenciado extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_credenciado")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_credenciado")
    private Long id;
    
    @Column(name = "nm_credenciado", length = 100)
    @NotNull
    private String nome;
    
	@Column(name = "ic_ativo")
	@NotNull
	private boolean ativo = Boolean.TRUE;    
    
	@Embedded
    private Endereco endereco = new Endereco();   
    
    @Column(name = "te_horario_atendimento", length = 200)
	private String horarioAtendimento;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "credenciado",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Collection<ProcedimentoCredenciado> procedimentosCredenciados;    

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
	
	public String getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(String horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public Collection<ProcedimentoCredenciado> getProcedimentosCredenciados() {
		return procedimentosCredenciados;
	}

	public void setProcedimentosCredenciados(
			Collection<ProcedimentoCredenciado> procedimentosCredenciados) {
		this.procedimentosCredenciados = procedimentosCredenciados;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	} 
	        
}