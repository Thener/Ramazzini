package br.com.ramazzini.model.responsavel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_responsavel", sequenceName = "seq_responsavel", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "responsavel")
public class Responsavel extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_responsavel")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_responsavel")
    private Long id;
    
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cd_empresa")
	private Empresa empresa;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cd_profissional")
	private Profissional profissional;	

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_inicio", columnDefinition = "Date")
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_fim", columnDefinition = "Date")
	private Date dataFim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	

}