package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimento;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoExameClinico;
import br.com.ramazzini.service.entidade.AvaliacaoClinicaProcedimentoService;
import br.com.ramazzini.service.entidade.FuncaoProcedimentoService;
import br.com.ramazzini.service.entidade.FuncaoService;
import br.com.ramazzini.util.TimeFactory;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class AnaliseEmissaoDocumentosController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_ANALISE_EMISSAO_DOCUMENTOS = "/pages/agenda/analiseEmissaoDocumentos.jsf?faces-redirect=true";
	
	@Inject private FuncaoService funcaoService;
	@Inject private FuncaoProcedimentoService funcaoProcedimentoService;
	@Inject private AvaliacaoClinicaProcedimentoService avaliacaoClinicaProcedimentoService;
	
	private Empresa empresaSelecionada;
	
	private Funcionario funcionarioSelecionado;
	
	private Procedimento procedimentoSelecionado;
	
	private List<FuncaoProcedimento> procedimentosParaPedidoExame;
	
	private Procedimento procedimetoParaPedidoExame;
	
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
		
		if (funcaoSelecionada == null) {
			UtilMensagens.mensagemErroPorChave("mensagem.erro.informacaoObrigatoria","label.funcao");
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
	
		if (funcaoRealizaProcedimentos(funcaoSelecionada, procedimentoSelecionado.getTipoExameClinicoEnum())) {
			setEmissaoPedidoExame(Boolean.TRUE);
			setProcedimentosParaPedidoExame(procedimentosRealizadosPelaFuncao(funcaoSelecionada));
		} else {
			setEmissaoAso(Boolean.TRUE);
		}
		
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.analiseRealizada", 
				procedimentoSelecionado.getTipoExameClinicoEnum().getChave());
	}
	
	private void analisePeriodicoDemissionalRetornoTrabalho() {
		
		if (funcaoRealizaProcedimentos(funcaoSelecionada, procedimentoSelecionado.getTipoExameClinicoEnum())) {
			List<FuncaoProcedimento> procedimentosVencidos = procedimentosVencidosDaFuncao(funcaoSelecionada);
			if (procedimentosVencidos.size() > 0) {
				setEmissaoPedidoExame(Boolean.TRUE);
				setProcedimentosParaPedidoExame(procedimentosVencidos);
			} else {
				setEmissaoAso(Boolean.TRUE);
			}
		} else {
			setEmissaoAso(Boolean.TRUE);
		}
		
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.analiseRealizada", 
				procedimentoSelecionado.getTipoExameClinicoEnum().getChave());
	}
	
	private void analiseMudancaFuncao() {
		emissaoAso = Boolean.FALSE;
		emissaoPedidoExame = Boolean.FALSE;	
		
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.analiseRealizada", 
				procedimentoSelecionado.getTipoExameClinicoEnum().getChave());		
	}
	
	private List<FuncaoProcedimento> procedimentosVencidosDaFuncao(Funcao funcao) {
		List<FuncaoProcedimento> procedimentos = funcaoProcedimentoService.recuperarPorFuncao(funcao);
		List<FuncaoProcedimento> procedimentosVencidos = new ArrayList<FuncaoProcedimento>();
		for (FuncaoProcedimento fp : procedimentos) {
			if (!avaliacaoClinicaProcedimentoService.verificaValidadeDoProcedimento(
					funcionarioSelecionado, fp.getProcedimento(), dataReferencia)) {
				procedimentosVencidos.add(fp);
			}
		}
		return procedimentosVencidos;
	}
	
	private boolean funcaoRealizaProcedimentos(Funcao funcao, TipoExameClinico tipoExameClinico) {
		return funcaoService.realizaProcedimentos(funcao, tipoExameClinico);
	}
	
	private List<FuncaoProcedimento> procedimentosRealizadosPelaFuncao(Funcao funcao) {
		return funcaoProcedimentoService.recuperarPorFuncao(funcao);
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

	public List<FuncaoProcedimento> getProcedimentosParaPedidoExame() {
		return procedimentosParaPedidoExame;
	}

	public void setProcedimentosParaPedidoExame(
			List<FuncaoProcedimento> procedimentosParaPedidoExame) {
		this.procedimentosParaPedidoExame = procedimentosParaPedidoExame;
	}

	public Procedimento getProcedimetoParaPedidoExame() {
		return procedimetoParaPedidoExame;
	}

	public void setProcedimetoParaPedidoExame(
			Procedimento procedimetoParaPedidoExame) {
		this.procedimetoParaPedidoExame = procedimetoParaPedidoExame;
	}
	
}