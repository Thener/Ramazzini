package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.service.CredenciadoService;
import br.com.ramazzini.service.ProcedimentoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class CredenciadoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_CREDENCIADO = "pesquisarCredenciado.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_CREDENCIADO = "cadastroCredenciado.jsf?faces-redirect=true";

	private @Inject Conversation conversation;
	
    @Inject
    private CredenciadoService credenciadoService;  	
    
    @Inject
    private ProcedimentoService procedimentoService;      
	
	private Credenciado credenciado;
	
	private List<Credenciado> credenciados;
	
	private String nomeCredenciadoPesquisa;
	
	private boolean somenteLeitura = Boolean.FALSE;
	
	@PostConstruct
	public void init() {

		credenciado = new Credenciado();	
		
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	public String incluirCredenciado() {
		
		this.credenciado = new Credenciado();
		return cadastroCredenciado(credenciado, Boolean.FALSE);
	}    
    
    public String alterarCredenciado(Credenciado credenciado){
    	
    	return cadastroCredenciado(credenciado, Boolean.FALSE);
    }
    
    public String visualizarCredenciado(Credenciado credenciado){
    	
    	return cadastroCredenciado(credenciado, Boolean.TRUE);
    } 
    
	private String cadastroCredenciado(Credenciado credenciado, Boolean somenteLeitura) {
    	setCredenciado(credenciado);
    	setSomenteLeitura(somenteLeitura);    	
    	return PAGINA_CADASTRO_CREDENCIADO;    	
    }
	
	public void salvar() {
		
		credenciadoService.salvar(credenciado);
		credenciados = credenciadoService.recuperarTodos("nome");
		
		//return PAGINA_PESQUISAR_CREDENCIADO;
	}
	
    public void pesquisar() throws Exception {
		
    	if (nomeCredenciadoPesquisa.isEmpty()){
    		credenciados = credenciadoService.recuperarTodos("nome");
		} else {
			credenciados = credenciadoService.recuperarPorNome(nomeCredenciadoPesquisa);
		}      
    }  	
     
    public void remover(Credenciado credenciado){
    	
    	try {
    		credenciadoService.remover(credenciado, credenciado.getId());
    		credenciados.remove(credenciado);
    		UtilMensagens.mensagemInformacao("Empresa removido com sucesso!");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErro("Não foi possível remover a empresa!");
        }
    }   
    
    public List<Procedimento> completeProcedimento(String query) {
        List<Procedimento> todosProcedimentos = procedimentoService.recuperarTodos();
        List<Procedimento> procedimentosFiltrados = new ArrayList<Procedimento>();
         
        for (int i = 0; i < todosProcedimentos.size(); i++) {
        	Procedimento skin = todosProcedimentos.get(i);
            if(skin.getNome().toLowerCase().contains(query)) {
            	procedimentosFiltrados.add(skin);
            }
        }         
        return procedimentosFiltrados;
    }	

	public Credenciado getCredenciado() {
		return credenciado;
	}
	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}
	public String getNomeCredenciadoPesquisa() {
		return nomeCredenciadoPesquisa;
	}

	public void setNomeCredenciadoPesquisa(String nomeCredenciadoPesquisa) {
		this.nomeCredenciadoPesquisa = nomeCredenciadoPesquisa;
	}
	
	public List<Credenciado> getCredenciados() {
		return credenciados;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}  
}