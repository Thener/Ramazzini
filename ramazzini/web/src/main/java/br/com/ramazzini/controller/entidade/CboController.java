package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.cbo.Cbo;
import br.com.ramazzini.service.entidade.CboService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class CboController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_CBO = "pesquisarCbo.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_CBO = "cadastroCbo.jsf?faces-redirect=true";

    @Inject
    private CboService cboService;  	
	
	private Cbo cbo;
	
	private List<Cbo> cbos;
	
	private String numeroCboPesquisa;
		
	@PostConstruct
	public void init() {
		beginConversation();
	}
		
    public void pesquisar() {

    	if (numeroCboPesquisa.isEmpty()){
    		cbos = cboService.recuperarTodos("numero");
		} else {
			cbos = cboService.recuperarPorNumero(numeroCboPesquisa);
		}      
    }  
    
	public String incluirCbo() {
		
		cbo = new Cbo();
		return cadatroCbo(cbo, Boolean.FALSE);
	}    
	
    public String alterarCbo(Cbo cbo){
    	
    	return cadatroCbo(cbo, Boolean.FALSE);
    }	
    
    public String visualizarCbo(Cbo cbo){
    	
    	return cadatroCbo(cbo, Boolean.TRUE);
    }
    
    public void removerCbo(Cbo cbo){
    	
    	try {
    		cboService.remover(cbo, cbo.getId());
    		cbos.remove(cbo);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.registroExcluidoComSucesso");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro","o cbo.");
        }
    }
    
	public String gravarCbo() {
		
		cboService.salvar(cbo);
		cbos = cboService.recuperarTodos("numero");
		return PAGINA_PESQUISAR_CBO;
	}    
    
    private String cadatroCbo(Cbo cbo, Boolean somenteLeitura) {
    	setCbo(cbo);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_CBO;    	
    }  
    
	public List<Cbo> getCbos() {
		return cbos;
	}

	public Cbo getCbo() {
		return cbo;
	}

	public void setCbo(Cbo cbo) {
		this.cbo = cbo;
	}

	public String getNumeroCboPesquisa() {
		return numeroCboPesquisa;
	}

	public void setNumeroCboPesquisa(String numeroCboPesquisa) {
		this.numeroCboPesquisa = numeroCboPesquisa;
	}	
}