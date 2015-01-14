package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.cnae.Cnae;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.empresa.SituacaoEmpresa;
import br.com.ramazzini.model.empresa.TipoPcmso;
import br.com.ramazzini.model.empresa.TipoPessoa;
import br.com.ramazzini.model.empresa.UnidadeFederativa;
import br.com.ramazzini.service.CnaeService;
import br.com.ramazzini.service.EmpresaService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class EmpresaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_EMPRESA = "pesquisarEmpresa.js?faces-redirect=true";
	private static final String PAGINA_ALTERAR_EMPRESA = "alterarEmpresa.js?faces-redirect=true";
	private static final String PAGINA_INCLUIR_EMPRESA = "incluirEmpresa.jsf?faces-redirect=true";

	private @Inject Conversation conversation;
	
    @Inject
    private EmpresaService empresaService;  	
	
    @Inject
    private CnaeService cnaeService; 
    
	private Empresa novaEmpresa;
	
	private List<Empresa> empresas;
	
	private List<Cnae> cnaes;
	
	private String nomeEmpresaPesquisa;
	
	private Empresa empresaSelecionada;
	
	private boolean somenteLeitura = Boolean.FALSE;
	
	@PostConstruct
	public void init() {

		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	public String incluirEmpresa() {
		this.novaEmpresa = new Empresa();
		return PAGINA_INCLUIR_EMPRESA;
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
	
	public List<Cnae> getCnaes() {
		if (cnaes == null || cnaes.isEmpty()) {
			cnaes = cnaeService.recuperarTodos("numero");
		}
		return cnaes;
	}
	
	public UnidadeFederativa[] getUfs() {
		return UnidadeFederativa.values();
	}
	
	public SituacaoEmpresa[] getSituacoesEmpresa() {
		return SituacaoEmpresa.values();
	}
	
	public TipoPcmso[] getTiposPcmso() {
		return TipoPcmso.values();
	}	
	
}