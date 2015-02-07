package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.riscoOcupacional.RiscoOcupacional;
import br.com.ramazzini.service.entidade.FuncaoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class FuncaoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_FUNCAO = "/pages/funcao/cadastroFuncao.jsf?faces-redirect=true";
	
    @Inject private FuncaoService funcaoService; 
    @Inject private FuncaoProcedimentoController funcaoProcedimentoController;    
    
    private List<Funcao> funcoes;
    
    private Empresa empresa;
    
    private Funcao funcao;
    
    private String nomeFuncaoPesquisa;
    
    private RiscoOcupacional riscoOcupacionalSelecionado;
    
    private List<RiscoOcupacional> riscosOcupacionais;
    
    private Integer tabAtiva;
    
	public String incluirFuncao() {
		
		funcao = new Funcao();
		funcao.setEmpresa(empresa);
		return cadastroFuncao(funcao, Boolean.FALSE);
	}
	    
    public String alterarFuncao(Funcao funcao){
    	
    	return cadastroFuncao(funcao, Boolean.FALSE);
    }
    
    public String visualizarFuncao(Funcao funcao){
    	
    	return cadastroFuncao(funcao, Boolean.TRUE);
    }
    
    public void removerFuncao(Funcao funcao){
    	
    	try {
    		funcaoService.remover(funcao, funcao.getId());
    		funcoes.remove(funcao);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "label.funcao");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro", "label.funcao");
        }
    }    
    
    private String cadastroFuncao(Funcao funcao, Boolean somenteLeitura) {

    	if (!funcao.isNovo()) {
    		riscosOcupacionais = funcaoService.recuperarRiscosOcupacionais(funcao);
    		funcao.setRiscosOcupacionais(riscosOcupacionais);
    	}
    	setEmpresa(funcao.getEmpresa());
    	riscoOcupacionalSelecionado = null;
    	funcaoProcedimentoController.setFuncao(funcao);
    	setFuncao(funcao);
    	setSomenteLeitura(somenteLeitura);
    	setUriRequisicao(getControleAcesso().getUriRequisicao());
    	return PAGINA_CADASTRO_FUNCAO;    	
    }
    
	public String gravarFuncao() {
		
		boolean inclusao = funcao.isNovo();
		funcaoService.salvar(funcao);
		if (inclusao) {
			//return alterarFuncao(funcao); // isso faz com que o botão voltar fique voltando para o proprio cadastro da funcao
			return "";
		} else {
			pesquisar();
			return voltar();
		}		
		
	}
    
    public void pesquisar() {
		
    	if (nomeFuncaoPesquisa == null || nomeFuncaoPesquisa.isEmpty()){
    		funcoes = funcaoService.recuperarPorEmpresa(empresa);
		} else {
			funcoes = funcaoService.recuperarPorNome(empresa, nomeFuncaoPesquisa);
		}      
    }  
    
    public void pesquisarGeral() {		
    	if (!nomeFuncaoPesquisa.isEmpty()){
			funcoes = funcaoService.recuperarPorNome(nomeFuncaoPesquisa);
		} else {
			UtilMensagens.mensagemInformacaoPorChave("mensagem.info.nomePesquisaNaoInformado", "label.funcao");
		}
    } 
    
    public void incluirRiscoOcupacional() {
    	
    	if (riscoOcupacionalSelecionado != null) {
    		funcao.getRiscosOcupacionais().add(riscoOcupacionalSelecionado);
    	}
    }    
    
    public void removerRiscoOcupacional(RiscoOcupacional riscoOcupacional) {
    	riscosOcupacionais.remove(riscoOcupacional);
    	funcao.getRiscosOcupacionais().remove(riscoOcupacional);
    }
    
    public String voltar() {				
		return getUriRequisicao()+"?faces-redirect=true";
	}
	
	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		setFuncoes(null); // se está mudando a empresa, então zerar a lista.
		this.empresa = empresa;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public String getNomeFuncaoPesquisa() {
		return nomeFuncaoPesquisa;
	}

	public void setNomeFuncaoPesquisa(String nomeFuncaoPesquisa) {
		this.nomeFuncaoPesquisa = nomeFuncaoPesquisa;
	}

	public RiscoOcupacional getRiscoOcupacionalSelecionado() {
		return riscoOcupacionalSelecionado;
	}

	public void setRiscoOcupacionalSelecionado(
			RiscoOcupacional riscoOcupacionalSelecionado) {
		this.riscoOcupacionalSelecionado = riscoOcupacionalSelecionado;
	}

	public List<RiscoOcupacional> getRiscosOcupacionais() {
		return riscosOcupacionais;
	}

	public void setRiscosOcupacionais(List<RiscoOcupacional> riscosOcupacionais) {
		this.riscosOcupacionais = riscosOcupacionais;
	}

	public FuncaoProcedimentoController getFuncaoProcedimentoController() {
		return funcaoProcedimentoController;
	}

	public Integer getTabActiveIndex() {
        return tabAtiva;
    }

    public void setTabActiveIndex(Integer tabActiveIndex) {
        this.tabAtiva = tabActiveIndex;
    }	
}