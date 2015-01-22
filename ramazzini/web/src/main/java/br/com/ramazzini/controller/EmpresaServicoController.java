package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.empresaServico.EmpresaServico;
import br.com.ramazzini.model.servico.Servico;
import br.com.ramazzini.service.entidade.EmpresaServicoService;
import br.com.ramazzini.service.entidade.ServicoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class EmpresaServicoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_EMPRESA_SERVICO = "/pages/empresaServico/cadastroEmpresaServico.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_EMPRESA = "/pages/empresa/cadastroEmpresa.jsf?faces-redirect=true";
	
    @Inject
    private EmpresaServicoService empresaServicoService; 
    
    @Inject
    private ServicoService servicoService; 
    
    private List<EmpresaServico> empresasServicos;
    
    private List<Servico> servicos;
    
    private Empresa empresa;
    
    private EmpresaServico empresaServico;
    
    private Servico servicoPesquisa;
    
	public String incluirEmpresaServico() {
		
		empresaServico = new EmpresaServico();
		empresaServico.setEmpresa(empresa);
		return cadatroEmpresaServico(empresaServico, Boolean.FALSE);
	}
	    
    public String alterarEmpresaServico(EmpresaServico empresaServico){
    	
    	return cadatroEmpresaServico(empresaServico, Boolean.FALSE);
    }
    
    public String visualizarEmpresaServico(EmpresaServico empresaServico){
    	
    	return cadatroEmpresaServico(empresaServico, Boolean.TRUE);
    }
    
    public void removerEmpresaServico(EmpresaServico empresaServico){
    	
    	try {
    		empresaServicoService.remover(empresaServico, empresaServico.getId());
    		getEmpresasServicos().remove(empresaServico);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "Serviço");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro", "o serviço.");
        }
    }    
    
    private String cadatroEmpresaServico(EmpresaServico empresaServico, Boolean somenteLeitura) {

    	setEmpresaServico(empresaServico);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_EMPRESA_SERVICO;    	
    }
    
	public String gravarEmpresaServico() {

		empresaServicoService.salvar(empresaServico);
		pesquisar();
		return PAGINA_CADASTRO_EMPRESA;
	}
    
    public void pesquisar() {
		if (servicoPesquisa == null){
    		empresasServicos = empresaServicoService.recuperarPorEmpresa(empresa);
		} else {
			empresasServicos = empresaServicoService.recuperarPorServico(empresa, servicoPesquisa);
		}
    }    
	
	public List<EmpresaServico> getEmpresasServicos() {
		return empresasServicos;
	}

	public void setEmpresasServicos(List<EmpresaServico> empresasServicos) {
		this.empresasServicos = empresasServicos;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		setEmpresasServicos(null); // se está mudando a empresa, então zerar a lista.
		this.empresa = empresa;
	}

	public EmpresaServico getEmpresaServico() {
		return empresaServico;
	}

	public void setEmpresaServico(EmpresaServico empresaServico) {
		this.empresaServico = empresaServico;
	}

	public Servico getServicoPesquisa() {
		return servicoPesquisa;
	}

	public void setServicoPesquisa(Servico servicoPesquisa) {
		this.servicoPesquisa = servicoPesquisa;
	}

	public List<Servico> getServicos() {
		if (servicos == null || servicos.isEmpty()) {
			servicos = servicoService.recuperarTodos("nome");
		}
		return servicos;
	}


}