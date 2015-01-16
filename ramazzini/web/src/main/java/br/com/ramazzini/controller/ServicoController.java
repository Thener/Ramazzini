package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.servico.Servico;
import br.com.ramazzini.service.ServicoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class ServicoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_SERVICO = "pesquisarServico.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_SERVICO = "cadastroServico.jsf?faces-redirect=true";

	private @Inject Conversation conversation;
	
    @Inject
    private ServicoService servicoService;  	
	
	private Servico servico;
	
	private List<Servico> servicos;
	
	private String nomeServicoPesquisa;
	
	private boolean somenteLeitura = Boolean.FALSE;
	
	@PostConstruct
	public void init() {

		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
			
    public void pesquisar() throws Exception {
		
    	if (nomeServicoPesquisa.isEmpty()){
    		servicos = servicoService.recuperarTodos("nome");
		} else {
			servicos = servicoService.recuperarPorNome(nomeServicoPesquisa);
		}      
    }  	
    
	public String incluirServico() {
		
		servico = new Servico();
		return cadatroServico(servico, Boolean.FALSE);
	}
	
    public String alterarServico(Servico servico){
    	
    	return cadatroServico(servico, Boolean.FALSE);
    }	
	
    public String visualizarServico(Servico servico){
    	
    	return cadatroServico(servico, Boolean.TRUE);
    }
    
    public void removerServico(Servico servico){
    	
    	try {
    		servicoService.remover(servico, servico.getId());
    		servicos.remove(servico);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.registroExcluidoComSucesso");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro","o serviço.");
        }
    }
    
	public String gravarServico() {

    	try {
    		servicoService.salvar(servico);
    		servicos = servicoService.recuperarTodos("nome");
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeGravadaComSucesso", "Serviço ");
    		return PAGINA_PESQUISAR_SERVICO;
    	} catch (Exception e) {           
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelGravarRegistro"," o serviço.");    		
    	}
    	
    	return null;
	}    
    
    private String cadatroServico(Servico servico, Boolean somenteLeitura) {
    	
    	setServico(servico);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_SERVICO;    	
    }    

	public List<Servico> getServicos() {
		return servicos;
	}

	public String getNomeServicoPesquisa() {
		return nomeServicoPesquisa;
	}

	public void setNomeServicoPesquisa(String nomeServicoPesquisa) {
		this.nomeServicoPesquisa = nomeServicoPesquisa;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}