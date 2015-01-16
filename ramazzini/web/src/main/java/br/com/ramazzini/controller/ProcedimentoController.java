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
	
	private static final String PAGINA_PESQUISAR_PROCEDIMENTO = "pesquisarProcedimento.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_PROCEDIMENTO = "cadastroProcedimento.jsf?faces-redirect=true";

	private @Inject Conversation conversation;
	
    @Inject
    private ProcedimentoService procedimentoService;  	
	
	private Procedimento procedimento;
	
	private List<Procedimento> procedimentos;
	
	private String nomeProcedimentoPesquisa;
	
	private boolean somenteLeitura = Boolean.FALSE;
	
	@PostConstruct
	public void init() {

		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	public String incluirProcedimento() {
		
		procedimento = new Procedimento();
		return PAGINA_CADASTRO_PROCEDIMENTO;
	}
	
	public String gravarProcedimento() {
		
		procedimentoService.salvar(procedimento);
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
    
    public String visualizarProcedimento(Procedimento procedimento){
    	
    	setProcedimento(procedimento);
    	setSomenteLeitura(Boolean.TRUE);
    	return PAGINA_CADASTRO_PROCEDIMENTO;
    }
    
    public String alterarProcedimento(Procedimento procedimento){
    	
    	setProcedimento(procedimento);
    	setSomenteLeitura(Boolean.FALSE);    	
    	return PAGINA_CADASTRO_PROCEDIMENTO;
    }
    
    public void removerProcedimento(Procedimento procedimento){
    	
    	try {
    		procedimentoService.remover(procedimento, procedimento.getId());
    		procedimentos.remove(procedimento);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.registroExcluidoComSucesso");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro","o procedimento.");
        }
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

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}
    
	
	
}