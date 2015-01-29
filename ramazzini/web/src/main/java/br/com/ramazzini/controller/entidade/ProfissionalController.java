package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.entidade.ProfissionalService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class ProfissionalController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_PROFISSIONAL = "pesquisarProfissional.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_PROFISSIONAL = "cadastroProfissional.jsf?faces-redirect=true";

    @Inject
    private ProfissionalService profissionalService;  	
	
	private Profissional profissional;
	
	private List<Profissional> profissionais;
	
	private String nomeProfissionalPesquisa;
	
	@PostConstruct
	public void init() {

		beginConversation();
	}
		
    public void pesquisar() {

    	if (nomeProfissionalPesquisa.isEmpty()){
    		profissionais = profissionalService.recuperarTodos("nome");
		} else {
			profissionais = profissionalService.recuperarPorNome(nomeProfissionalPesquisa);
		}      
    }  
    
	public String incluirProfissional() {
		
		profissional = new Profissional();
		return cadastroProfissional(profissional, Boolean.FALSE);
	}    
	
    public String alterarProfissional(Profissional profissional){
    	
    	return cadastroProfissional(profissional, Boolean.FALSE);
    }	
    
    public String visualizarProfissional(Profissional profissional){
    	
    	return cadastroProfissional(profissional, Boolean.TRUE);
    }
    
    public void removerProfissional(Profissional profissional){
    	
    	try {
    		profissionalService.remover(profissional, profissional.getId());
    		profissionais.remove(profissional);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.registroExcluidoComSucesso");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro","o profissional.");
        }
    }
    
	public String gravarProfissional() {
		
		profissionalService.salvar(profissional);
		profissionais = profissionalService.recuperarTodos("nome");
		return PAGINA_PESQUISAR_PROFISSIONAL;
	}    
    
    private String cadastroProfissional(Profissional profissional, Boolean somenteLeitura) {
    	
    	setProfissional(profissional);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_PROFISSIONAL;    	
    }     

	public List<Profissional> getProfissionais() {
		return profissionais;
	}

	public String getNomeProfissionalPesquisa() {
		return nomeProfissionalPesquisa;
	}

	public void setNomeProfissionalPesquisa(String nomeProfissionalPesquisa) {
		this.nomeProfissionalPesquisa = nomeProfissionalPesquisa;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	
}