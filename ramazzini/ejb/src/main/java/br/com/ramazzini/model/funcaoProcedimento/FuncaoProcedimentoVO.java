package br.com.ramazzini.model.funcaoProcedimento;

import java.io.Serializable;
import java.util.Date;


public class FuncaoProcedimentoVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private FuncaoProcedimento funcaoProcedimento;
	
	private Date dataRetorno;
	
	private boolean solicitar;
	
	public FuncaoProcedimentoVO(FuncaoProcedimento funcaoProcedimento, Date dataRetorno, boolean solicitar) {
		this.funcaoProcedimento = funcaoProcedimento;
		this.dataRetorno = dataRetorno;
		this.solicitar = solicitar;
	}

	public FuncaoProcedimento getFuncaoProcedimento() {
		return funcaoProcedimento;
	}

	public void setFuncaoProcedimento(FuncaoProcedimento funcaoProcedimento) {
		this.funcaoProcedimento = funcaoProcedimento;
	}

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public boolean isSolicitar() {
		return solicitar;
	}

	public void setSolicitar(boolean solicitar) {
		this.solicitar = solicitar;
	}
	
}
