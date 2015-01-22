package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.grupo.Grupo;
import br.com.ramazzini.service.EmpresaService;
import br.com.ramazzini.service.GrupoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class GrupoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_GRUPO = "pesquisarGrupo.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_GRUPO = "cadastroGrupo.jsf?faces-redirect=true";

	private @Inject Conversation conversation;
	
    @Inject
    private GrupoService grupoService;
    
    @Inject
    private EmpresaService empresaService;      
	
	private Grupo grupo;
	
	private Empresa empresaSelecionada;
	
	private List<Grupo> grupos;
	
	private List<Empresa> empresasDoGrupo;
	
	private String nomeGrupoPesquisa;
	
	private boolean somenteLeitura = Boolean.FALSE;
	
	@PostConstruct
	public void init() {

		//empresasDoGrupo = grupoService.recuperarTodasEmpresas(grupo);
		
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
		
    public void pesquisar() {

    	if (nomeGrupoPesquisa.isEmpty()){
    		grupos = grupoService.recuperarTodos("nome");
		} else {
			grupos = grupoService.recuperarPorNome(nomeGrupoPesquisa);
		}      
    }  
    
	public String incluirGrupo() {
		
		grupo = new Grupo();
		return cadastroGrupo(grupo, Boolean.FALSE);
	}  
	
    public void incluirEmpresaNoGrupo() {
    	
    	empresaSelecionada.setGrupo(grupo);
    	grupo.getEmpresas().add(empresaSelecionada);
    }	
	
    public String alterarGrupo(Grupo grupo){
    	
    	return cadastroGrupo(grupo, Boolean.FALSE);
    }	
    
    public String visualizarGrupo(Grupo grupo){
    	
    	return cadastroGrupo(grupo, Boolean.TRUE);
    }
    
    public void removerGrupo(Grupo grupo){
    	
    	try {
    		grupoService.remover(grupo, grupo.getId());
    		grupos.remove(grupo);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.registroExcluidoComSucesso");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro","o grupo.");
        }
    }
    
    public void removerEmpresaDoGrupo(Empresa empresa) {
    	empresa.setGrupo(null);
    	empresaService.salvar(empresa);
    	grupo.getEmpresas().remove(empresa);
    }
    
	public String gravarGrupo() {
		boolean inclusao = grupo.isNovo();
		grupoService.salvar(grupo);
		grupos = grupoService.recuperarTodos("nome");
		return (inclusao) ? alterarGrupo(grupo) : PAGINA_PESQUISAR_GRUPO;
	}    
    
    private String cadastroGrupo(Grupo grupo, Boolean somenteLeitura) {
    	
    	if (!grupo.isNovo()) {
    		empresasDoGrupo = grupoService.recuperarTodasEmpresas(grupo);
    		grupo.setEmpresas(empresasDoGrupo);
    	}
    	setGrupo(grupo);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_GRUPO;    	
    }
    
	public List<Grupo> getGrupos() {
		return grupos;
	}

	public String getNomeGrupoPesquisa() {
		return nomeGrupoPesquisa;
	}

	public void setNomeGrupoPesquisa(String nomeGrupoPesquisa) {
		this.nomeGrupoPesquisa = nomeGrupoPesquisa;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}

	public List<Empresa> getEmpresasDoGrupo() {
		return empresasDoGrupo;
	}

	public void setEmpresasDoGrupo(List<Empresa> empresasDoGrupo) {
		this.empresasDoGrupo = empresasDoGrupo;
	}
	
	
	
}