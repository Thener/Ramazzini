package br.com.ramazzini.model.empresaServico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.servico.Servico;
import br.com.ramazzini.model.util.AbstractEntidade;


@Entity
@Table(name = "empresa_servico")
public class EmpresaServico extends AbstractEntidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@SequenceGenerator(name = "seq_empresa_servico", sequenceName = "seq_empresa_servico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_empresa_servico")	
	private EmpresaServicoId id;
    
    @ManyToOne
    @JoinColumn(name="cd_empresa")
    private Empresa empresa;
 
	@ManyToOne
    @JoinColumn(name="cd_servico")
    private Servico servico;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ts_data_servico", columnDefinition = "Date")
	private Date dataServico;	
	
	@Column(name = "ds_servico")
	private String descricao;

	public Long getId() {
		return id.getId();
	}

	public void setId(EmpresaServicoId id) {
		this.id = id;
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
