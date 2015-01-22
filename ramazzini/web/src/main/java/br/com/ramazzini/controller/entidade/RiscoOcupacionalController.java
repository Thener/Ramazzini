package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.riscoOcupacional.RiscoOcupacional;
import br.com.ramazzini.model.riscoOcupacional.TipoRiscoOcupacional;
import br.com.ramazzini.service.entidade.RiscoOcupacionalService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class RiscoOcupacionalController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_RISCO_OCUPACIONAL = "pesquisarRiscoOcupacional.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_RISCO_OCUPACIONAL = "cadastroRiscoOcupacional.jsf?faces-redirect=true";

	private @Inject Conversation conversation;
	
    @Inject
    private RiscoOcupacionalService riscoOcupacionalService;  	
	
	private RiscoOcupacional riscoOcupacional;
	
	private List<RiscoOcupacional> riscosOcupacionais;
	
	private String nomeRiscoOcupacionalPesquisa;
	
	private boolean somenteLeitura = Boolean.FALSE;
	
	@PostConstruct
	public void init() {

		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
		
    public void pesquisar() {

    	if (nomeRiscoOcupacionalPesquisa.isEmpty()){
    		riscosOcupacionais = riscoOcupacionalService.recuperarTodos("nome");
		} else {
			riscosOcupacionais = riscoOcupacionalService.recuperarPorNome(nomeRiscoOcupacionalPesquisa);
		}      
    }  
    
	public String incluirRiscoOcupacional() {
		
		riscoOcupacional = new RiscoOcupacional();
		return cadastroRiscoOcupacional(riscoOcupacional, Boolean.FALSE);
	}    
	
    public String alterarRiscoOcupacional(RiscoOcupacional riscoOcupacional){
    	
    	return cadastroRiscoOcupacional(riscoOcupacional, Boolean.FALSE);
    }	
    
    public String visualizarRiscoOcupacional(RiscoOcupacional riscoOcupacional){
    	
    	return cadastroRiscoOcupacional(riscoOcupacional, Boolean.TRUE);
    }
    
    public void removerRiscoOcupacional(RiscoOcupacional riscoOcupacional){
    	
    	try {
    		riscoOcupacionalService.remover(riscoOcupacional, riscoOcupacional.getId());
    		riscosOcupacionais.remove(riscoOcupacional);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.registroExcluidoComSucesso");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro","o risco ocupacional.");
        }
    }
    
	public String gravarRiscoOcupacional() {
		
		riscoOcupacionalService.salvar(riscoOcupacional);
		riscosOcupacionais = riscoOcupacionalService.recuperarTodos("nome");
		return PAGINA_PESQUISAR_RISCO_OCUPACIONAL;
	}    
    
    private String cadastroRiscoOcupacional(RiscoOcupacional riscoOcupacional, Boolean somenteLeitura) {
    	
    	setRiscoOcupacional(riscoOcupacional);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_RISCO_OCUPACIONAL;    	
    }     

	public TipoRiscoOcupacional[] getTiposRiscoOcupacional() {
		return TipoRiscoOcupacional.values();
	}

	public List<RiscoOcupacional> getRiscosOcupacionais() {
		return riscosOcupacionais;
	}

	public String getNomeRiscoOcupacionalPesquisa() {
		return nomeRiscoOcupacionalPesquisa;
	}

	public void setNomeRiscoOcupacionalPesquisa(String nomeRiscoOcupacionalPesquisa) {
		this.nomeRiscoOcupacionalPesquisa = nomeRiscoOcupacionalPesquisa;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}

	public RiscoOcupacional getRiscoOcupacional() {
		return riscoOcupacional;
	}

	public void setRiscoOcupacional(RiscoOcupacional riscoOcupacional) {
		this.riscoOcupacional = riscoOcupacional;
	}
	
}