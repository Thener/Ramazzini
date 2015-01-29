package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.cnae.Cnae;
import br.com.ramazzini.service.entidade.CnaeService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class CnaeController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_CNAE = "pesquisarCnae.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_CNAE = "cadastroCnae.jsf?faces-redirect=true";

    @Inject
    private CnaeService cnaeService;  	
	
	private Cnae cnae;
	
	private List<Cnae> cnaes;
	
	private String numeroCnaePesquisa;
		
	@PostConstruct
	public void init() {

		beginConversation();
	}
		
    public void pesquisar() {

    	if (numeroCnaePesquisa.isEmpty()){
    		cnaes = cnaeService.recuperarTodos("numero");
		} else {
			cnaes = cnaeService.recuperarPorNumero(numeroCnaePesquisa);
		}      
    }  
    
	public String incluirCnae() {
		
		cnae = new Cnae();
		return cadatroCnae(cnae, Boolean.FALSE);
	}    
	
    public String alterarCnae(Cnae cnae){
    	
    	return cadatroCnae(cnae, Boolean.FALSE);
    }	
    
    public String visualizarCnae(Cnae cnae){
    	
    	return cadatroCnae(cnae, Boolean.TRUE);
    }
    
    public void removerCnae(Cnae cnae){
    	
    	try {
    		cnaeService.remover(cnae, cnae.getId());
    		cnaes.remove(cnae);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.registroExcluidoComSucesso");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro","o cnae.");
        }
    }
    
	public String gravarCnae() {
		
		cnaeService.salvar(cnae);
		cnaes = cnaeService.recuperarTodos("numero");
		return PAGINA_PESQUISAR_CNAE;
	}    
    
    private String cadatroCnae(Cnae cnae, Boolean somenteLeitura) {
    	
    	setCnae(cnae);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_CNAE;    	
    }  
    
	public List<Cnae> getCnaes() {
		return cnaes;
	}

	public Cnae getCnae() {
		return cnae;
	}

	public void setCnae(Cnae cnae) {
		this.cnae = cnae;
	}

	public String getNumeroCnaePesquisa() {
		return numeroCnaePesquisa;
	}

	public void setNumeroCnaePesquisa(String numeroCnaePesquisa) {
		this.numeroCnaePesquisa = numeroCnaePesquisa;
	}	
}