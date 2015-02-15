package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.avaliacaoClinicaProcedimento.AvaliacaoClinicaProcedimento;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimento;
import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimentoVO;
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
	
	private List<FuncaoProcedimentoVO> procedimentosParaPedidoExame = new ArrayList<FuncaoProcedimentoVO>();
	
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
			definirProcedimentosParaPedidoExame(funcaoSelecionada, TipoExameClinico.ADMISSIONAL);
		} else {
			setEmissaoAso(Boolean.TRUE);
		}
		
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.analiseRealizada", 
				procedimentoSelecionado.getTipoExameClinicoEnum().getChave());
	}
	
	private void analisePeriodicoDemissionalRetornoTrabalho() {
		
		if (funcaoRealizaProcedimentos(funcaoSelecionada, procedimentoSelecionado.getTipoExameClinicoEnum())) {
			definirProcedimentosParaPedidoExame(funcaoSelecionada, procedimentoSelecionado.getTipoExameClinicoEnum());
			if (procedimentosParaPedidoExame.size() > 0) {
				setEmissaoPedidoExame(Boolean.TRUE);
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
	
	private boolean funcaoRealizaProcedimentos(Funcao funcao, TipoExameClinico tipoExameClinico) {
		
		return funcaoService.realizaProcedimentos(funcao, tipoExameClinico);
	}
	
	private void definirProcedimentosParaPedidoExame(Funcao funcao, TipoExameClinico tipoExameClinico) {
		
		List<FuncaoProcedimento> funcoesProcedimentos = funcaoProcedimentoService.recuperarPorFuncao(funcao);
		
		procedimentosParaPedidoExame.clear();
		
		boolean procedimentoExigido = Boolean.FALSE;
		
		for (FuncaoProcedimento fp : funcoesProcedimentos) {
			
			boolean solicitar;
			
			AvaliacaoClinicaProcedimento acp = 
					avaliacaoClinicaProcedimentoService.recuperarMaisRecentePor(funcionarioSelecionado, fp.getProcedimento());

			Date dataRetorno = acp != null ? acp.getDataRetorno() : null;
			
			if (!verificarExigencia(fp, tipoExameClinico)) {
				solicitar = Boolean.FALSE;
			} else {
				if (acp == null) {
					solicitar = Boolean.TRUE;
				} else {
					solicitar = acp.getDataRetorno().before(dataReferencia) ? Boolean.TRUE : Boolean.FALSE;
				}
			} 
			
			if (solicitar) {
				procedimentoExigido = Boolean.TRUE;
			}
			
			procedimentosParaPedidoExame.add(new FuncaoProcedimentoVO(fp, dataRetorno, solicitar));
		}
		
		if (!procedimentoExigido) { // então nenhum é exigido para o tipoExameClinico ou estão em dia.
			procedimentosParaPedidoExame.clear();
		}
	}
	
	public void incluirProcedimentoNaGuia(Procedimento procedimento) {
		
	}
	
	public boolean verificarExigencia(FuncaoProcedimento funcaoProcedimento, TipoExameClinico tipoExameClinico) {
		
		if (funcaoProcedimento.isRealizaAdmissional() && tipoExameClinico.equals(TipoExameClinico.ADMISSIONAL)) {
			return Boolean.TRUE;
		} else if (funcaoProcedimento.isRealizaPeriodico() && tipoExameClinico.equals(TipoExameClinico.PERIODICO)) {
			return Boolean.TRUE;
		} else if (funcaoProcedimento.isRealizaDemissional() && tipoExameClinico.equals(TipoExameClinico.DEMISSIONAL)) {
			return Boolean.TRUE;
		} else if (funcaoProcedimento.isRealizaMudancaFuncao() && tipoExameClinico.equals(TipoExameClinico.MUDANCA_FUNCAO)) {
			return Boolean.TRUE;
		} else if (funcaoProcedimento.isRealizaRetornoTrabalho() && tipoExameClinico.equals(TipoExameClinico.RETORNO_TRABALHO)) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
		
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

	public List<FuncaoProcedimentoVO> getProcedimentosParaPedidoExame() {
		return procedimentosParaPedidoExame;
	}

	public void setProcedimentosParaPedidoExame(
			List<FuncaoProcedimentoVO> procedimentosParaPedidoExame) {
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