package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.responsavel.Responsavel;
import br.com.ramazzini.service.entidade.ResponsavelService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class ResponsavelController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_RESPONSAVEL = "/pages/responsavel/cadastroResponsavel.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_EMPRESA = "/pages/empresa/cadastroEmpresa.jsf?faces-redirect=true";
	
    @Inject
    private ResponsavelService responsavelService; 
    
    private List<Responsavel> responsaveis;
    
    private Empresa empresa;
    
    private Responsavel responsavel;
    
    private String nomeProfissionalPesquisa;
    
	public String incluirResponsavel() {
		
		responsavel = new Responsavel();
		responsavel.setEmpresa(empresa);
		return cadatroResponsavel(responsavel, Boolean.FALSE);
	}
	    
    public String alterarResponsavel(Responsavel responsavel){
    	
    	return cadatroResponsavel(responsavel, Boolean.FALSE);
    }
    
    public String visualizarResponsavel(Responsavel responsavel){
    	
    	return cadatroResponsavel(responsavel, Boolean.TRUE);
    }
    
    public void removerResponsavel(Responsavel responsavel){
    	
    	try {
    		responsavelService.remover(responsavel, responsavel.getId());
    		responsaveis.remove(responsavel);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "Responsável");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro", "o responsável.");
        }
    }    
    
    private String cadatroResponsavel(Responsavel responsavel, Boolean somenteLeitura) {

    	setResponsavel(responsavel);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_RESPONSAVEL;    	
    }
    
	public String gravarResponsavel() {
		
		responsavelService.salvar(responsavel);
		pesquisar();
		return PAGINA_CADASTRO_EMPRESA;
	}
    
    public void pesquisar() {
		
    	if (nomeProfissionalPesquisa == null || nomeProfissionalPesquisa.isEmpty()){
    		responsaveis = responsavelService.recuperarPorEmpresa(empresa);
		} else {
			responsaveis = responsavelService.recuperarPorNomeProfissional(empresa, nomeProfissionalPesquisa);
		}      
    }    
	
	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		setResponsaveis(null); // se está mudando a empresa, então zerar a lista.
		this.empresa = empresa;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public String getNomeProfissionalPesquisa() {
		return nomeProfissionalPesquisa;
	}

	public void setNomeProfissionalPesquisa(String nomeProfissionalPesquisa) {
		this.nomeProfissionalPesquisa = nomeProfissionalPesquisa;
	}

}