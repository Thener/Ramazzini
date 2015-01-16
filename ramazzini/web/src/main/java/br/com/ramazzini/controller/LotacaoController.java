package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.lotacao.Lotacao;
import br.com.ramazzini.service.LotacaoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class LotacaoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_LOTACAO = "/pages/lotacao/cadastroLotacao.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_EMPRESA = "/pages/empresa/cadastroEmpresa.jsf?faces-redirect=true";
	
    @Inject
    private LotacaoService lotacaoService; 
    
    private List<Lotacao> lotacoes;
    
    private Empresa empresa;
    
    private Lotacao lotacao;
    
    private String nomeLotacaoPesquisa;
       
	public String incluirLotacao() {
		
		this.lotacao = new Lotacao();
		return cadatroLotacao(lotacao, Boolean.FALSE);
	}
	    
    public String alterarLotacao(Lotacao lotacao){
    	
    	return cadatroLotacao(lotacao, Boolean.FALSE);
    }
    
    public String visualizarLotacao(Lotacao lotacao){
    	
    	return cadatroLotacao(lotacao, Boolean.TRUE);
    }
    
    public void removerLotacao(Lotacao lotacao){
    	
    	try {
    		lotacaoService.remover(lotacao, lotacao.getId());
    		setLotacoes(null);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "Lotação");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro", "a lotação.");
        }
    }    
    
    private String cadatroLotacao(Lotacao lotacao, Boolean somenteLeitura) {
    	if (lotacao.getEmpresa() == null) {
    		lotacao.setEmpresa(empresa);
    	}
    	setLotacao(lotacao);
    	return PAGINA_CADASTRO_LOTACAO;    	
    }
    
	public String gravarLotacao() {
		
		lotacaoService.salvar(lotacao);
		return PAGINA_CADASTRO_EMPRESA;
	}
	
 
    
    public void pesquisar() throws Exception {
		
    	if (nomeLotacaoPesquisa == null || nomeLotacaoPesquisa.isEmpty()){
    		lotacoes = lotacaoService.recuperarPorEmpresa(empresa);
		} else {
			lotacoes = lotacaoService.recuperarPorNome(empresa, nomeLotacaoPesquisa);
		}      
    }    
	
	public List<Lotacao> getLotacoes() {
		if (lotacoes == null || lotacoes.isEmpty()) {
			lotacoes = lotacaoService.recuperarPorEmpresa(empresa);
		}
		return lotacoes;
	}

	public void setLotacoes(List<Lotacao> lotacoes) {
		this.lotacoes = lotacoes;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		setLotacoes(null); // se está mudando a empresa, então zerar a lista.
		this.empresa = empresa;
	}

	public Lotacao getLotacao() {
		return lotacao;
	}

	public void setLotacao(Lotacao lotacao) {
		this.lotacao = lotacao;
	}

	public String getNomeLotacaoPesquisa() {
		return nomeLotacaoPesquisa;
	}

	public void setNomeLotacaoPesquisa(String nomeLotacaoPesquisa) {
		this.nomeLotacaoPesquisa = nomeLotacaoPesquisa;
	}
	
	

}