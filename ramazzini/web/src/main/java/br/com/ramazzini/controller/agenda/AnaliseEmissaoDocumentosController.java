package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.avaliacaoClinicaProcedimento.AvaliacaoClinicaProcedimento;
import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimento;
import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimentoVO;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.guia.Guia;
import br.com.ramazzini.model.guia.SituacaoGuia;
import br.com.ramazzini.model.guiaProcedimento.GuiaProcedimento;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoExameClinico;
import br.com.ramazzini.model.procedimentoCredenciado.ProcedimentoCredenciado;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.entidade.AvaliacaoClinicaProcedimentoService;
import br.com.ramazzini.service.entidade.FuncaoProcedimentoService;
import br.com.ramazzini.service.entidade.FuncaoService;
import br.com.ramazzini.service.entidade.GuiaService;
import br.com.ramazzini.service.entidade.ProcedimentoCredenciadoService;
import br.com.ramazzini.util.TimeFactory;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class AnaliseEmissaoDocumentosController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_ANALISE_EMISSAO_DOCUMENTOS = "/pages/agenda/analiseEmissaoDocumentos.jsf?faces-redirect=true";
	
	@Inject private AvaliacaoClinicaProcedimentoService avaliacaoClinicaProcedimentoService;
	@Inject private FuncaoService funcaoService;
	@Inject private FuncaoProcedimentoService funcaoProcedimentoService;
	@Inject private GuiaService guiaService;
	@Inject private ProcedimentoCredenciadoService procedimentoCredenciadoService;
	
	private Empresa empresaSelecionada;
	
	private Funcionario funcionarioSelecionado;
	
	private Procedimento procedimentoSelecionado;
	
	private List<FuncaoProcedimentoVO> procedimentosParaPedidoExame = new ArrayList<FuncaoProcedimentoVO>();
	
	private Procedimento procedimentoParaPedidoExame;
	
	private Funcao funcaoSelecionada;
	
	private Funcao funcaoAnteriorSelecionada;
	
	private Profissional profissionalSelecionado;
	
	private Date dataReferencia;
	
	private boolean emissaoPedidoExame = Boolean.FALSE;
	
	private boolean emissaoAso = Boolean.FALSE;
	
	private Credenciado credenciadoSelecionado;
	
	private List<GuiaProcedimento> guiasProcedimentos = new ArrayList<GuiaProcedimento>();
	
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
		
		emissaoAso = Boolean.FALSE;
		emissaoPedidoExame = Boolean.FALSE;
		
		guiasProcedimentos.clear();
		
		if (funcionarioSelecionado == null) {
			UtilMensagens.mensagemErroPorChave("mensagem.erro.informacaoObrigatoria","label.funcionario");
			return;
		}
		
		if (procedimentoSelecionado == null) {
			UtilMensagens.mensagemErroPorChave("mensagem.erro.informacaoObrigatoria","label.procedimento");
			return;
		}
		
		if (funcaoSelecionada == null) {
			UtilMensagens.mensagemErroPorChave("mensagem.erro.informacaoObrigatoria","label.funcao");
			return;
		}
		
		if (procedimentoSelecionado.getTipoExameClinicoEnum().equals(TipoExameClinico.MUDANCA_FUNCAO)) {
				
			if (funcaoAnteriorSelecionada == null) {
				UtilMensagens.mensagemErroPorChave("mensagem.erro.informacaoObrigatoria","label.funcaoAnterior");
				return;
			}
			
			if (funcaoSelecionada.equals(funcaoAnteriorSelecionada)) {
				UtilMensagens.mensagemErroPorChave("mensagem.erro.funcaoAnteriorEatualSaoIguais");
				return;				
			}
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
			definirAsoPedidoExame();
		} else {
			setEmissaoAso(Boolean.TRUE);
		}
		
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.analiseRealizada", 
				procedimentoSelecionado.getTipoExameClinicoEnum().getChave());
	}
	
	private void analisePeriodicoDemissionalRetornoTrabalho() {
		
		if (funcaoRealizaProcedimentos(funcaoSelecionada, procedimentoSelecionado.getTipoExameClinicoEnum())) {
			definirAsoPedidoExame();
		} else {
			setEmissaoAso(Boolean.TRUE);
		}
		
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.analiseRealizada", 
				procedimentoSelecionado.getTipoExameClinicoEnum().getChave());
	}
	
	private void analiseMudancaFuncao() {
		
		boolean funcaoAtualRealizaProcedimentos = funcaoRealizaProcedimentos(funcaoSelecionada, TipoExameClinico.MUDANCA_FUNCAO);
		boolean funcaoAnteriorRealizaProcedimentos = funcaoRealizaProcedimentos(funcaoAnteriorSelecionada, TipoExameClinico.MUDANCA_FUNCAO);
		
		if (!funcaoAtualRealizaProcedimentos && !funcaoAnteriorRealizaProcedimentos) {
			setEmissaoAso(Boolean.TRUE);
		} else {
			definirAsoPedidoExame();			
		}
		
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.analiseRealizada", 
				procedimentoSelecionado.getTipoExameClinicoEnum().getChave());		
	}
	
	private boolean funcaoRealizaProcedimentos(Funcao funcao, TipoExameClinico tipoExameClinico) {
		
		return funcaoService.realizaProcedimentos(funcao, tipoExameClinico);
	}
	
	private void definirAsoPedidoExame() {
		
		definirProcedimentosParaPedidoExame();
		if (procedimentosParaPedidoExame.size() > 0) {
			setEmissaoPedidoExame(Boolean.TRUE);
		} else {
			setEmissaoAso(Boolean.TRUE);
		}		
	}
	
	private void definirProcedimentosParaPedidoExame() {
		
		TipoExameClinico tipoExameClinico = procedimentoSelecionado.getTipoExameClinicoEnum();
		
		List<FuncaoProcedimento> funcoesProcedimentos;
		
		if (tipoExameClinico.equals(TipoExameClinico.MUDANCA_FUNCAO)) {
			funcoesProcedimentos = funcaoProcedimentoService.recuperarPorFuncaoAnteriorAtual(funcaoAnteriorSelecionada, funcaoSelecionada);
		} else {
			funcoesProcedimentos = funcaoProcedimentoService.recuperarPorFuncao(funcaoSelecionada);
		}
		
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
					solicitar = dataRetorno.before(dataReferencia) ? Boolean.TRUE : Boolean.FALSE;
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
		
	public void selecionarCredenciadoParaProcedimento(Procedimento procedimento) {
		this.credenciadoSelecionado = null;
		this.procedimentoParaPedidoExame = procedimento;
	}
	
	public void incluirProcedimentoGuia() {
		
		ProcedimentoCredenciado procedimentoCredenciado = 
			procedimentoCredenciadoService.recuperarPor(credenciadoSelecionado, procedimentoParaPedidoExame);
		
		GuiaProcedimento guiaProcedimento = 
			new GuiaProcedimento(procedimentoParaPedidoExame, 1, procedimentoCredenciado.getPrecoVenda(), 
					procedimentoCredenciado.getPrecoCusto(), credenciadoSelecionado);
		
		guiasProcedimentos.add(guiaProcedimento);
	}
		
	public void removerProcedimentoGuia(GuiaProcedimento guiaProcedimento) {
		guiasProcedimentos.remove(guiaProcedimento);
	}
	
	public void finalizarGuia() {
		
		List<Guia> guias = new ArrayList<Guia>();
    		
		//-------- Extraindo todos os credenciados eliminando repetições:
		
		HashMap<Credenciado, GuiaProcedimento> map = new HashMap<Credenciado, GuiaProcedimento>();
		for (GuiaProcedimento gp : guiasProcedimentos) {
			map.put(gp.getCredenciadoAuxiliar(), gp);
		}

	    for (GuiaProcedimento gp : map.values()) {
	    	Guia guia = new Guia(gp.getCredenciadoAuxiliar(), funcionarioSelecionado, SituacaoGuia.EMITIDA, TimeFactory.createDataHora());
	    	guias.add(guia);
	    }
	    
		//-------- Preparando cada guia:	    
    	
    	for (GuiaProcedimento gp : guiasProcedimentos) {
    		
    		for (Guia guia : guias) {
    			if (guia.getCredenciado().equals(gp.getCredenciadoAuxiliar())) {
    				gp.setGuia(guia);
    				guia.getProcedimentos().add(gp);
    			}
    		}
    	}
    	
    	guiasProcedimentos.clear();
    	
    	guiaService.salvarLista(guias);
		
	}
	
	public void imprimirGuia(Guia guia) {
		
		guia.setSituacaoGuiaEnum(SituacaoGuia.IMPRESSA);
		guiaService.salvar(guia);
		
		//..... continuar com o processo de impressão
		//..... ?????? 
		
	}
	
	public void cancelarGuia(Guia guia) {
		
		guia.setSituacaoGuiaEnum(SituacaoGuia.CANCELADA);
		guiaService.salvar(guia);

	}
	
	public void imprimirASO() {
		
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
	
	public List<Guia> getGuiasEmitidasParaFuncionario() {
		return guiaService.recuperarPor(funcionarioSelecionado, TimeFactory.createDataHora());
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

	public Procedimento getProcedimentoParaPedidoExame() {
		return procedimentoParaPedidoExame;
	}

	public void setProcedimentoParaPedidoExame(
			Procedimento procedimentoParaPedidoExame) {
		this.procedimentoParaPedidoExame = procedimentoParaPedidoExame;
	}

	public Funcao getFuncaoAnteriorSelecionada() {
		return funcaoAnteriorSelecionada;
	}

	public void setFuncaoAnteriorSelecionada(Funcao funcaoAnteriorSelecionada) {
		this.funcaoAnteriorSelecionada = funcaoAnteriorSelecionada;
	}

	public Credenciado getCredenciadoSelecionado() {
		return credenciadoSelecionado;
	}

	public void setCredenciadoSelecionado(Credenciado credenciadoSelecionado) {
		this.credenciadoSelecionado = credenciadoSelecionado;
	}

	public List<GuiaProcedimento> getGuiasProcedimentos() {
		return guiasProcedimentos;
	}

	public void setGuiasProcedimentos(List<GuiaProcedimento> guiasProcedimentos) {
		this.guiasProcedimentos = guiasProcedimentos;
	}

	public Profissional getProfissionalSelecionado() {
		return profissionalSelecionado;
	}

	public void setProfissionalSelecionado(Profissional profissionalSelecionado) {
		this.profissionalSelecionado = profissionalSelecionado;
	}

	
}