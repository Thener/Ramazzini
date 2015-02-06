package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoExameClinico;
import br.com.ramazzini.util.TimeFactory;

@Named
@ConversationScoped
public class AnaliseEmissaoDocumentosController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_ANALISE_EMISSAO_DOCUMENTOS = "/pages/agenda/analiseEmissaoDocumentos.jsf?faces-redirect=true";
	
	private Empresa empresaSelecionada;
	
	private Funcionario funcionarioSelecionado;
	
	private Procedimento procedimentoSelecionado;
	
	private Date dataReferencia;
	
	public String init(Funcionario funcionario, Procedimento procedimento) {
		beginConversation();
		funcionarioSelecionado = funcionario;
		empresaSelecionada = funcionario.getEmpresa();
		procedimentoSelecionado = procedimento;
		dataReferencia = TimeFactory.createDataHora();
		dataReferencia = TimeFactory.somarDias(dataReferencia, 30);
		analisar();
		setUriRequisicao(getControleAcesso().getUriRequisicao());
		return PAGINA_ANALISE_EMISSAO_DOCUMENTOS;
	}
	
	public void analisar() {
		if (procedimentoSelecionado.getTipoExameClinicoEnum().equals(TipoExameClinico.ADMISSIONAL)) {
			analiseAdmissioal();
		} else if (procedimentoSelecionado.getTipoExameClinicoEnum().equals(TipoExameClinico.MUDANCA_FUNCAO)) {
			analiseMudancaFuncao();	
		} else {
			analisePeriodicoDemissionalRetornoTrabalho();
		}
	}
	
	private void analiseAdmissioal() {
		
	}
	
	private void analisePeriodicoDemissionalRetornoTrabalho() {
		
	}
	
	private void analiseMudancaFuncao() {
		
	}

    public String voltar() {	
    	endConversation();
		return getUriRequisicao()+"?faces-redirect=true";
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public Procedimento getProcedimentoSelecionado() {
		return procedimentoSelecionado;
	}

	public void setProcedimentoSelecionado(Procedimento procedimentoSelecionado) {
		this.procedimentoSelecionado = procedimentoSelecionado;
	}

	public Date getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}
    
	
}