package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.cnae.Cnae;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.empresa.FiltroEmpresa;
import br.com.ramazzini.model.empresa.SituacaoEmpresa;
import br.com.ramazzini.model.empresa.TipoPcmso;
import br.com.ramazzini.model.empresa.TipoPessoa;
import br.com.ramazzini.model.empresa.UnidadeFederativa;
import br.com.ramazzini.service.entidade.CnaeService;
import br.com.ramazzini.service.entidade.EmpresaService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class EmpresaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_EMPRESA = "/pages/empresa/cadastroEmpresa.jsf?faces-redirect=true";

    @Inject private EmpresaService empresaService;  	
    @Inject private CnaeService cnaeService; 
    @Inject private LotacaoController lotacaoController;
    @Inject private EmpresaServicoController empresaServicoController;    
    @Inject private ResponsavelController responsavelController;
    @Inject private FuncionarioController funcionarioController;   
    @Inject private FuncaoController funcaoController;  
    @Inject private SetorController setorController;    
    
	private Empresa empresa;
	
	private List<Empresa> empresas;
	
	private List<Cnae> cnaes;
	
	private Integer tabAtiva;
	
	private FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
	private LazyDataModel<Empresa> lazyDataModelEmpresa;
	
	@PostConstruct
	public void init() {

		beginConversation();
	}
	
	public EmpresaController() {
		
		lazyDataModelEmpresa = new LazyDataModel<Empresa>() {
			private static final long serialVersionUID = 1L;
			@Override
			public List<Empresa> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				filtroEmpresa.setPrimeiroRegistro(first);
				filtroEmpresa.setQuantidadeRegistros(pageSize);
				filtroEmpresa.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtroEmpresa.setPropriedadeOrdenacao(sortField);
				
				setRowCount(empresaService.gridContarRegistros(filtroEmpresa));
				
				return empresaService.gridRecuperarRegistros(filtroEmpresa);
			}
		};
	}
        
	public String incluirEmpresa() {
		
		empresa = new Empresa();
		return cadastroEmpresa(empresa, Boolean.FALSE);
	}    
    
    public String alterarEmpresa(Empresa empresa){
    	
    	return cadastroEmpresa(empresa, Boolean.FALSE);
    }
    
    public String visualizarEmpresa(Empresa empresa){
    	
    	return cadastroEmpresa(empresa, Boolean.TRUE);
    } 
    
    public void removerEmpresa(Empresa empresa){
    	
    	try {
    		empresaService.remover(empresa, empresa.getId());
    		empresas.remove(empresa);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso","label.empresa");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro","label.empresa");
        }
    }     
    
    private String cadastroEmpresa(Empresa empresa, Boolean somenteLeitura) {
    	setEmpresa(empresa);
    	lotacaoController.setEmpresa(empresa);
    	empresaServicoController.setEmpresa(empresa);
    	responsavelController.setEmpresa(empresa);
    	funcionarioController.setEmpresa(empresa);
    	funcaoController.setEmpresa(empresa);
    	setorController.setEmpresa(empresa);
    	setSomenteLeitura(somenteLeitura);
    	setTabActiveIndex(0);
    	setUriRequisicao(getControleAcesso().getUriRequisicao());
    	return PAGINA_CADASTRO_EMPRESA;    	
    }
    
	public String gravarEmpresa() {
		
		boolean inclusao = empresa.isNovo();
		empresaService.salvar(empresa);
		if (inclusao) {
			UtilMensagens.mensagemInformacaoPorChaveAposRedirect("mensagem.info.entidadeGravadaComSucesso","label.empresa");
			return "";
		} else {
			return voltar();
		}		
	}    
	
	public String voltar() {				
		return getUriRequisicao()+"?faces-redirect=true";
	}
      
	public List<Empresa> getEmpresas() {
		return empresas;
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
	
	public EmpresaServicoController getEmpresaServicoController() {
		return empresaServicoController;
	}
	
    public ResponsavelController getResponsavelController() {
		return responsavelController;
	}

	public FuncionarioController getFuncionarioController() {
		return funcionarioController;
	}

	public Integer getTabActiveIndex() {
        return tabAtiva;
    }

    public void setTabActiveIndex(Integer tabActiveIndex) {
        this.tabAtiva = tabActiveIndex;
    }

	public FiltroEmpresa getFiltroEmpresa() {
		return filtroEmpresa;
	}

	public void setFiltroEmpresa(FiltroEmpresa filtroEmpresa) {
		this.filtroEmpresa = filtroEmpresa;
	}

	public LazyDataModel<Empresa> getLazyDataModelEmpresa() {
		return lazyDataModelEmpresa;
	}
	
}