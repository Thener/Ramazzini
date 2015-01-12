package br.com.ramazzini.model.riscoOcupacional;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_risco_ocupacional", sequenceName = "seq_risco_ocupacional", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "risco_ocupacional", uniqueConstraints = @UniqueConstraint(columnNames = "nm_risco_ocupacional"))
public class RiscoOcupacional extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_risco_ocupacional")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_risco_ocupacional")
    private Long id;
    
    @Column(name = "nm_risco_ocupacional")
    @NotNull
    @Size(min = 1, max = 100)
    private String nome;
    
	@Column(name = "tp_risco_ocupacional", length = 3)
    @NotNull 
    private String tipoRiscoOcupacional;
	
	@ManyToMany(mappedBy="riscosOcupacionais")
	private List<Funcao> funcoes;	

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoRiscoOcupacional() {
		return tipoRiscoOcupacional;
	}

	public void setTipoRiscoOcupacional(String tipoRiscoOcupacional) {
		this.tipoRiscoOcupacional = tipoRiscoOcupacional;
	}

	public TipoRiscoOcupacional getTipoRiscoOcupacionalEnum() {
		return (this.tipoRiscoOcupacional != null) ? TipoRiscoOcupacional.parse(this.tipoRiscoOcupacional) : null;
	}

	public void setTipoRiscoOcupacionalEnum(TipoRiscoOcupacional tipoRiscoOcupacional) {
		setTipoRiscoOcupacional(tipoRiscoOcupacional.getValue());
	}

}