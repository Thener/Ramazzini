package br.com.ramazzini.model.empresaServico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.servico.Servico;
import br.com.ramazzini.model.util.AbstractEntidade;


@Entity
@Table(name = "empresa_servico")
public class EmpresaServico extends AbstractEntidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_empresa_servico", sequenceName = "seq_empresa_servico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_empresa_servico")	
	private Long id;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name="cd_empresa")
    private Empresa empresa;
 
	@ManyToOne
	@NotNull
    @JoinColumn(name="cd_servico")
    private Servico servico;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ts_data_servico", columnDefinition = "timestamp without time zone")
	private Date dataServico;	
	
	@Column(name = "ds_servico", length=200)
	private String descricao;

	public Long getId() {
		return id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Date getDataServico() {
		return dataServico;
	}

	public void setDataServico(Date dataServico) {
		this.dataServico = dataServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
