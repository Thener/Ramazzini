package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoProcedimento;
import br.com.ramazzini.service.ProcedimentoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class ProcedimentoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_PROCEDIMENTO = "pesquisarProcedimento.js?faces-redirect=true";
	private static final String PAGINA_ALTERAR_PROCEDIMENTO = "alterarProcedimento.js?faces-redirect=true";
	private static final String PAGINA_INCLUIR_PROCEDIMENTO = "incluirProcedimento.jsf?faces-redirect=true";

	private @Inject Conversation conversation;
	
    @Inject
    private ProcedimentoService procedimentoService;  	
	
	private Procedimento novoProcedimento;
	
	private List<Procedimento> procedimentos;
	
	private String nomeProcedimentoPesquisa;
	
	private Procedimento procedimentoSelecionado;
	
	private boolean somenteLeitura = Boolean.FALSE;
	
	@PostConstruct
	public void init() {

		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	public String incluirProcedimento() {
		
		novoProcedimento = new Procedimento();
		return PAGINA_INCLUIR_PROCEDIMENTO;
	}
	
	public String salvar() {
		
		procedimentoService.salvar(novoProcedimento);
		procedimentos = procedimentoService.recuperarTodos("nome");
		return PAGINA_PESQUISAR_PROCEDIMENTO;
	}
	
	public String atualizar() {
		
		procedimentoService.salvar(procedimentoSelecionado);
		procedimentos = procedimentoService.recuperarTodos("nome");
		return PAGINA_PESQUISAR_PROCEDIMENTO;
	}	
	
    public void pesquisar() throws Exception {
		
    	if (nomeProcedimentoPesquisa.isEmpty()){
    		procedimentos = procedimentoService.recuperarTodos("nome");
		} else {
			procedimentos = procedimentoService.recuperarPorNome(nomeProcedimentoPesquisa);
		}      
    }  	
    
    public String visualizar(Procedimento procedimento){
    	
    	setProcedimentoSelecionado(procedimento);
    	setSomenteLeitura(Boolean.TRUE);
    	return PAGINA_ALTERAR_PROCEDIMENTO;
    }
    
    public String editar(Procedimento procedimento){
    	
    	setProcedimentoSelecionado(procedimento);
    	setSomenteLeitura(Boolean.FALSE);    	
    	return PAGINA_ALTERAR_PROCEDIMENTO;
    }
    
    public void remover(Procedimento procedimento){
    	
    	try {
    		procedimentoService.remover(procedimento, procedimento.getId());
    		procedimentos.remove(procedimento);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.registroExcluidoComSucesso");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro","o procedimento.");
        }
    }    

	public Procedimento getNovoProcedimento() {
		return novoProcedimento;
	}

	public void setNovoProcedimento(Procedimento novoProcedimento) {
		this.novoProcedimento = novoProcedimento;
	}

	public TipoProcedimento[] getTiposProcedimento() {
		return TipoProcedimento.values();
	}

	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public String getNomeProcedimentoPesquisa() {
		return nomeProcedimentoPesquisa;
	}

	public void setNomeProcedimentoPesquisa(String nomeProcedimentoPesquisa) {
		this.nomeProcedimentoPesquisa = nomeProcedimentoPesquisa;
	}

	public Procedimento getProcedimentoSelecionado() {
		return procedimentoSelecionado;
	}

	public void setProcedimentoSelecionado(Procedimento procedimentoSelecionado) {
		this.procedimentoSelecionado = procedimentoSelecionado;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}
    
	
}