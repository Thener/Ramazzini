package br.com.ramazzini.model.cnae;

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

@SequenceGenerator(name = "seq_cnae", sequenceName = "seq_cnae", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "cnae", uniqueConstraints = @UniqueConstraint(columnNames = "no_cnae"))
public class Cnae extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_cnae")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_cnae")
    private Long id;
    
    @Column(name = "no_cnae")
    @NotNull
    @Size(min = 1, max = 10)
    private String numero;
    
    @Column(name = "te_atividades", columnDefinition="TEXT")
    private String atividades;
    
	@Column(name = "no_grau_risco")
    @NotNull 
    private Integer grauRisco;
	
	@OneToMany(mappedBy="cnae")
	private List<Empresa> empresas;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getGrauRisco() {
		return grauRisco;
	}

	public void setGrauRisco(Integer grauRisco) {
		this.grauRisco = grauRisco;
	}

	public GrauRisco getGrauRiscoEnum() {
		return (this.grauRisco != null) ? GrauRisco.parse(this.grauRisco) : null;
	}

	public void setGrauRiscoEnum(GrauRisco grauRisco) {
		setGrauRisco(grauRisco.getValue());
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	
}