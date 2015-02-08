package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoExameClinico;
import br.com.ramazzini.service.entidade.FuncaoProcedimentoService;
import br.com.ramazzini.util.TimeFactory;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class AnaliseEmissaoDocumentosController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_ANALISE_EMISSAO_DOCUMENTOS = "/pages/agenda/analiseEmissaoDocumentos.jsf?faces-redirect=true";
	
	@Inject private FuncaoProcedimentoService funcaoProcedimentoService;
	
	private Empresa empresaSelecionada;
	
	private Funcionario funcionarioSelecionado;
	
	private Procedimento procedimentoSelecionado;
	
	private List<Procedimento> procedimentosParaPedidoExame;
	
	private Funcao funcaoSelecionada;
	
	private Date dataReferencia;
	
	private boolean emissaoPedidoExame = Boolean.FALSE;
	
	private boolean emissaoAso = Boolean.FALSE;
	
	public String init(Funcionario funcionario, Procedimento procedimento) {
		beginConversation();
		funcionarioSelecionado = funcionario;
		empresaSelecionada = funcionario.getEmpresa();
		procedimentoSelecionado = procedimento;
		funcaoSelecionada = funcionario.getFuncao();
		dataReferencia = TimeFactory.createDataHora();
		dataReferencia = TimeFactory.somarDias(dataReferencia, 30);
		analisar();
		setUriRequisicao(getControleAcesso().getUriRequisicao());
		return PAGINA_ANALISE_EMISSAO_DOCUMENTOS;
	}
	
	public void analisar() {
		
		if (procedimentoSelecionado == null) {
			UtilMensagens.mensagemErroPorChave("mensagem.erro.informacaoObrigatoria","label.procedimento");
			return;
		}
		
		if (procedimentoSelecionado.getTipoExameClinicoEnum().equals(TipoExameClinico.ADMISSIONAL)) {
			analiseAdmissioal();
		} else if (procedimentoSelecionado.getTipoExameClinicoEnum().equals(TipoExameClinico.MUDANCA_FUNCAO)) {
			analiseMudancaFuncao();	
		} else {
			analisePeriodicoDemissionalRetornoTrabalho();
		}
	}
	
	private void analiseAdmissioal() {
	
		if (funcaoRealizaProcedimentos(funcaoSelecionada, TipoExameClinico.ADMISSIONAL)) {
			setEmissaoPedidoExame(Boolean.TRUE);
			setProcedimentosParaPedidoExame(procedimentosRealizadosPelaFuncao(funcaoSelecionada, TipoExameClinico.ADMISSIONAL));
		} else {
			setEmissaoAso(Boolean.TRUE);
		}
	}
	
	private void analisePeriodicoDemissionalRetornoTrabalho() {
		emissaoAso = Boolean.FALSE;
		emissaoPedidoExame = Boolean.FALSE;
	}
	
	private void analiseMudancaFuncao() {
		emissaoAso = Boolean.FALSE;
		emissaoPedidoExame = Boolean.FALSE;		
	}
	
	private boolean funcaoRealizaProcedimentos(Funcao funcao, TipoExameClinico tipoExameClinico) {
		return (procedimentosRealizadosPelaFuncao(funcao, tipoExameClinico).size() > 0) ? Boolean.TRUE : Boolean.FALSE;
	}
	
	private List<Procedimento> procedimentosRealizadosPelaFuncao(Funcao funcao, TipoExameClinico tipoExameClinico) {
		return funcaoProcedimentoService.recuperarProcedimentosDaFuncao(funcao, tipoExameClinico);
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
	
	public Funcao getFuncaoSelecionada() {
		return funcaoSelecionada;
	}

	public void setFuncaoSelecionada(Funcao funcaoSelecionada) {
		this.funcaoSelecionada = funcaoSelecionada;
	}

	public Date getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public boolean isEmissaoPedidoExame() {
		return emissaoPedidoExame;
	}

	public void setEmissaoPedidoExame(boolean emissaoPedidoExame) {
		emissaoAso = !emissaoPedidoExame;
		this.emissaoPedidoExame = emissaoPedidoExame;
	}

	public boolean isEmissaoAso() {
		return emissaoAso;
	}

	public void setEmissaoAso(boolean emissaoAso) {
		emissaoPedidoExame = !emissaoAso;
		this.emissaoAso = emissaoAso;
	}

	public List<Procedimento> getProcedimentosParaPedidoExame() {
		return procedimentosParaPedidoExame;
	}

	public void setProcedimentosParaPedidoExame(
			List<Procedimento> procedimentosParaPedidoExame) {
		this.procedimentosParaPedidoExame = procedimentosParaPedidoExame;
	}
	
	
	
}