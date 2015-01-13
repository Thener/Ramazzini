package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.service.CredenciadoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class CredenciadoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_CREDENCIADO = "pesquisarCredenciado.js?faces-redirect=true";
	private static final String PAGINA_ALTERAR_CREDENCIADO = "alterarCredenciado.js?faces-redirect=true";

	private @Inject Conversation conversation;
	
    @Inject
    private CredenciadoService credenciadoService;  	
	
	private Credenciado novoCredenciado;
	
	private List<Credenciado> credenciados;
	
	private String nomeCredenciadoPesquisa;
	
	private Credenciado credenciadoSelecionado;
	
	private boolean somenteLeitura = Boolean.FALSE;
	
	@PostConstruct
	public void init() {

		novoCredenciado = new Credenciado();
		
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	public String salvar() {
		
		credenciadoService.salvar(novoCredenciado);
		credenciados = credenciadoService.recuperarTodos("nome");
		return PAGINA_PESQUISAR_CREDENCIADO;
	}
	
	public String atualizar() {
		
		credenciadoService.salvar(credenciadoSelecionado);
		credenciados = credenciadoService.recuperarTodos("nome");
		return PAGINA_PESQUISAR_CREDENCIADO;
	}	
	
    public void pesquisar() throws Exception {
		
    	if (nomeCredenciadoPesquisa.isEmpty()){
    		credenciados = credenciadoService.recuperarTodos("nome");
		} else {
			credenciados = credenciadoService.recuperarPorNome(nomeCredenciadoPesquisa);
		}      
    }  	
    
    public String visualizar(Credenciado credenciado){
    	
    	setCredenciadoSelecionado(credenciado);
    	setSomenteLeitura(Boolean.TRUE);
    	return PAGINA_ALTERAR_CREDENCIADO;
    }
    
    public String editar(Credenciado credenciado){
    	
    	setCredenciadoSelecionado(credenciado);
    	setSomenteLeitura(Boolean.FALSE);    	
    	return PAGINA_ALTERAR_CREDENCIADO;
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

	public Credenciado getNovoCredenciado() {
		return novoCredenciado;
	}

	public void setNovoCredenciado(Credenciado novoCredenciado) {
		this.novoCredenciado = novoCredenciado;
	}

	public String getNomeCredenciadoPesquisa() {
		return nomeCredenciadoPesquisa;
	}

	public void setNomeCredenciadoPesquisa(String nomeCredenciadoPesquisa) {
		this.nomeCredenciadoPesquisa = nomeCredenciadoPesquisa;
	}

	public Credenciado getCredenciadoSelecionado() {
		return credenciadoSelecionado;
	}

	public void setCredenciadoSelecionado(Credenciado credenciadoSelecionado) {
		this.credenciadoSelecionado = credenciadoSelecionado;
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