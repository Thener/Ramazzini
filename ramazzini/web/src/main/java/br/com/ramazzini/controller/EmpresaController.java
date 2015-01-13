package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.empresa.TipoPessoa;
import br.com.ramazzini.service.EmpresaService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class EmpresaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_EMPRESA = "pesquisarEmpresa.js?faces-redirect=true";
	private static final String PAGINA_ALTERAR_EMPRESA = "alterarEmpresa.js?faces-redirect=true";

	private @Inject Conversation conversation;
	
    @Inject
    private EmpresaService empresaService;  	
	
	private Empresa novaEmpresa;
	
	private List<Empresa> empresas;
	
	private String nomeEmpresaPesquisa;
	
	private Empresa empresaSelecionada;
	
	private boolean somenteLeitura = Boolean.FALSE;
	
	@PostConstruct
	public void init() {

		novaEmpresa = new Empresa();
		
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	public String salvar() {
		
		empresaService.salvar(novaEmpresa);
		empresas = empresaService.recuperarTodos("nome");
		return PAGINA_PESQUISAR_EMPRESA;
	}
	
	public String atualizar() {
		
		empresaService.salvar(empresaSelecionada);
		empresas = empresaService.recuperarTodos("nome");
		return PAGINA_PESQUISAR_EMPRESA;
	}	
	
    public void pesquisar() throws Exception {
		
    	if (nomeEmpresaPesquisa == null || nomeEmpresaPesquisa.isEmpty()){
    		empresas = empresaService.recuperarTodos("nome");
		} else {
			empresas = empresaService.recuperarPorNome(nomeEmpresaPesquisa);
		}      
    }  	
    
    public String visualizar(Empresa empresa){
    	
    	setEmpresaSelecionada(empresa);
    	setSomenteLeitura(Boolean.TRUE);
    	return PAGINA_ALTERAR_EMPRESA;
    }
    
    public String editar(Empresa empresa){
    	
    	setEmpresaSelecionada(empresa);
    	setSomenteLeitura(Boolean.FALSE);    	
    	return PAGINA_ALTERAR_EMPRESA;
    }
    
    public void remover(Empresa empresa){
    	
    	try {
    		empresaService.remover(empresa, empresa.getId());
    		empresas.remove(empresa);
    		UtilMensagens.mensagemInformacao("Empresa removido com sucesso!");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErro("Não foi possível remover a empresa!");
        }
    }    

	public Empresa getNovaEmpresa() {
		return novaEmpresa;
	}

	public void setNovaEmpresa(Empresa novaEmpresa) {
		this.novaEmpresa = novaEmpresa;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public String getNomeEmpresaPesquisa() {
		return nomeEmpresaPesquisa;
	}

	public void setNomeEmpresaPesquisa(String nomeEmpresaPesquisa) {
		this.nomeEmpresaPesquisa = nomeEmpresaPesquisa;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}
    
	public TipoPessoa[] getTiposPessoa() {
		return TipoPessoa.values();
	}	
	
}