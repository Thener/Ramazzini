package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.service.entidade.CredenciadoService;
import br.com.ramazzini.util.TratarExcecao;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class CredenciadoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_CREDENCIADO = "pesquisarCredenciado.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_CREDENCIADO = "cadastroCredenciado.jsf?faces-redirect=true";

	@Inject
	private ProcedimentoCredenciadoController procedimentoCredenciadoController;
	
    @Inject
    private CredenciadoService credenciadoService;  	
      
	private Credenciado credenciado;
	
	private List<Credenciado> credenciados;
	
	private String nomeCredenciadoPesquisa;
	
	private Integer tabAtiva;
	
	
	@PostConstruct
	public void init() {

		credenciado = new Credenciado();	
		
		beginConversation();
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
    	procedimentoCredenciadoController.setCredenciado(credenciado);
    	setSomenteLeitura(somenteLeitura);    	
    	return PAGINA_CADASTRO_CREDENCIADO;    	
    }
	
	public String salvar() {
		boolean inclusao = credenciado.isNovo();
		credenciadoService.salvar(credenciado);
		if (inclusao) {
			UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeGravadaComSucesso","label.credenciado");
			return "";
		} else {
			credenciados = credenciadoService.recuperarTodos("nome");
			return PAGINA_PESQUISAR_CREDENCIADO;
		}	
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
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso","label.credenciado");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErro(new TratarExcecao(e).getExceptionMessage());
    	}
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

	public ProcedimentoCredenciadoController getProcedimentoCredenciadoController() {
		return procedimentoCredenciadoController;
	}
	
	public void setProcedimentoCredenciadoController(
			ProcedimentoCredenciadoController procedimentoCredenciadoController) {
		this.procedimentoCredenciadoController = procedimentoCredenciadoController;
	}

	public Integer getTabActiveIndex() {
        return tabAtiva;
    }

    public void setTabActiveIndex(Integer tabActiveIndex) {
        this.tabAtiva = tabActiveIndex;
    }	 		
}