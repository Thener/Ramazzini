package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.feriado.Feriado;
import br.com.ramazzini.service.entidade.FeriadoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class FeriadoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_FERIADO = "pesquisarFeriado.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_FERIADO = "cadastroFeriado.jsf?faces-redirect=true";

	private @Inject Conversation conversation;
	
    @Inject
    private FeriadoService feriadoService;  	
	
	private Feriado feriado;
	
	private List<Feriado> feriados;
	
	private String nomeFeriadoPesquisa;
		
	@PostConstruct
	public void init() {

		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
		
    public void pesquisar() {

    	if (nomeFeriadoPesquisa.isEmpty()){
    		feriados = feriadoService.recuperarTodos("nome");
		} else {
			feriados = feriadoService.recuperarPorNome(nomeFeriadoPesquisa);
		}      
    }  
    
	public String incluirFeriado() {
		
		feriado = new Feriado();
		return cadatroFeriado(feriado, Boolean.FALSE);
	}    
	
    public String alterarFeriado(Feriado feriado){
    	
    	return cadatroFeriado(feriado, Boolean.FALSE);
    }	
    
    public String visualizarFeriado(Feriado feriado){
    	
    	return cadatroFeriado(feriado, Boolean.TRUE);
    }
    
    public void removerFeriado(Feriado feriado){
    	
    	try {
    		feriadoService.remover(feriado, feriado.getId());
    		feriados.remove(feriado);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.registroExcluidoComSucesso");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro","o feriado.");
        }
    }
    
	public String gravarFeriado() {
		
		feriadoService.salvar(feriado);
		UtilMensagens.mensagemInformacaoPorChaveAposRedirect("mensagem.info.dadosGravadosComSucesso","Feriado");
		feriados = feriadoService.recuperarTodos("nome");
		return PAGINA_PESQUISAR_FERIADO;
	}    
    
    private String cadatroFeriado(Feriado feriado, Boolean somenteLeitura) {
    	
    	setFeriado(feriado);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_FERIADO;    	
    }  
    
	public List<Feriado> getFeriados() {
		return feriados;
	}

	public Feriado getFeriado() {
		return feriado;
	}

	public void setFeriado(Feriado feriado) {
		this.feriado = feriado;
	}

	public String getNomeFeriadoPesquisa() {
		return nomeFeriadoPesquisa;
	}

	public void setNomeFeriadoPesquisa(String nomeFeriadoPesquisa) {
		this.nomeFeriadoPesquisa = nomeFeriadoPesquisa;
	}	
}