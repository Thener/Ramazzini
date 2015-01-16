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
	
	private static final String PAGINA_PESQUISAR_EMPRESA = "pesquisarEmpresa.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_EMPRESA = "cadastroEmpresa.jsf?faces-redirect=true";

	private @Inject Conversation conversation;
	
    @Inject
    private EmpresaService empresaService;  	
	
    @Inject
    private CnaeService cnaeService; 
    
    @Inject
    private LotacaoController lotacaoController;    
    
	private Empresa empresa;
	
	private List<Empresa> empresas;
	
	private List<Cnae> cnaes;
	
	private String nomeEmpresaPesquisa;
	
	private boolean somenteLeitura = Boolean.FALSE;
	
	private boolean acaoInclusao = Boolean.FALSE;
	private boolean acaoAlteracao = Boolean.FALSE;
	private boolean acaoVisualizacao = Boolean.FALSE;
	
	@PostConstruct
	public void init() {

		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
		
    public void pesquisar() throws Exception {
		
    	if (nomeEmpresaPesquisa == null || nomeEmpresaPesquisa.isEmpty()){
    		empresas = empresaService.recuperarTodos("nome");
		} else {
			empresas = empresaService.recuperarPorNome(nomeEmpresaPesquisa);
		}      
    }  	
        
	public String incluirEmpresa() {
		
		empresa = new Empresa();
		setAcaoInclusao(Boolean.TRUE);
		return cadastroEmpresa(empresa, Boolean.FALSE);
	}    
    
    public String alterarEmpresa(Empresa empresa){
    	
    	setAcaoAlteracao(Boolean.TRUE);
    	return cadastroEmpresa(empresa, Boolean.FALSE);
    }
    
    public String visualizarEmpresa(Empresa empresa){
    	
    	setAcaoVisualizacao(Boolean.TRUE);
    	return cadastroEmpresa(empresa, Boolean.TRUE);
    } 
    
    public void removerEmpresa(Empresa empresa){
    	
    	try {
    		empresaService.remover(empresa, empresa.getId());
    		empresas.remove(empresa);
    		UtilMensagens.mensagemInformacao("Empresa removido com sucesso!");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErro("Não foi possível remover a empresa!");
        }
    }     
    
    private String cadastroEmpresa(Empresa empresa, Boolean somenteLeitura) {
    	setEmpresa(empresa);
    	lotacaoController.setEmpresa(empresa);
    	setSomenteLeitura(somenteLeitura);    	
    	return PAGINA_CADASTRO_EMPRESA;    	
    }
    
	public String gravarEmpresa() {
		
		empresaService.salvar(empresa);
		empresas = empresaService.recuperarTodos("nome");
		return (acaoInclusao) ? alterarEmpresa(empresa) : PAGINA_PESQUISAR_EMPRESA;
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public LotacaoController getLotacaoController() {
		return lotacaoController;
	}

	public boolean isAcaoInclusao() {
		return acaoInclusao;
	}

	public void setAcaoInclusao(boolean acaoInclusao) {
		this.acaoInclusao = acaoInclusao;
		this.acaoAlteracao = !acaoInclusao;
		this.acaoVisualizacao = !acaoInclusao;
	}

	public boolean isAcaoAlteracao() {
		return acaoAlteracao;
	}

	public void setAcaoAlteracao(boolean acaoAlteracao) {
		this.acaoAlteracao = acaoAlteracao;
		this.acaoInclusao = !acaoAlteracao;
		this.acaoVisualizacao = !acaoAlteracao;
	}

	public boolean isAcaoVisualizacao() {
		return acaoVisualizacao;
	}

	public void setAcaoVisualizacao(boolean acaoVisualizacao) {
		this.acaoVisualizacao = acaoVisualizacao;
		this.acaoInclusao = !acaoVisualizacao;
		this.acaoAlteracao = !acaoVisualizacao;
	}
	
	
}