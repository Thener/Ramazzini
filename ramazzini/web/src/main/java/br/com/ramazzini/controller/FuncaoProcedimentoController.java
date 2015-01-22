package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimento;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.service.entidade.FuncaoProcedimentoService;
import br.com.ramazzini.service.entidade.ProcedimentoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class FuncaoProcedimentoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_FUNCAO_PROCEDIMENTO = "/pages/funcaoProcedimento/cadastroFuncaoProcedimento.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_FUNCAO = "/pages/funcao/cadastroFuncao.jsf?faces-redirect=true";
	
    @Inject
    private FuncaoProcedimentoService funcaoProcedimentoService; 
    
    @Inject
    private ProcedimentoService procedimentoService; 
    
    private List<FuncaoProcedimento> funcoesProcedimentos;
    
    private List<Procedimento> procedimentos;
    
    private Funcao funcao;
    
    private FuncaoProcedimento funcaoProcedimento;
    
    private Procedimento procedimentoPesquisa;
    
	public String incluirFuncaoProcedimento() {
		
		funcaoProcedimento = new FuncaoProcedimento();
		funcaoProcedimento.setFuncao(funcao);
		return cadatroFuncaoProcedimento(funcaoProcedimento, Boolean.FALSE);
	}
	    
    public String alterarFuncaoProcedimento(FuncaoProcedimento funcaoProcedimento){
    	
    	return cadatroFuncaoProcedimento(funcaoProcedimento, Boolean.FALSE);
    }
    
    public String visualizarFuncaoProcedimento(FuncaoProcedimento funcaoProcedimento){
    	
    	return cadatroFuncaoProcedimento(funcaoProcedimento, Boolean.TRUE);
    }
    
    public void removerFuncaoProcedimento(FuncaoProcedimento funcaoProcedimento){
    	
    	try {
    		funcaoProcedimentoService.remover(funcaoProcedimento, funcaoProcedimento.getId());
    		getFuncoesProcedimentos().remove(funcaoProcedimento);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "Procedimento");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro", "o procedimento.");
        }
    }    
    
    private String cadatroFuncaoProcedimento(FuncaoProcedimento funcaoProcedimento, Boolean somenteLeitura) {

    	setFuncaoProcedimento(funcaoProcedimento);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_FUNCAO_PROCEDIMENTO;    	
    }
    
	public String gravarFuncaoProcedimento() {

		funcaoProcedimentoService.salvar(funcaoProcedimento);
		pesquisar();
		return PAGINA_CADASTRO_FUNCAO;
	}
    
    public void pesquisar() {
		if (procedimentoPesquisa == null){
    		funcoesProcedimentos = funcaoProcedimentoService.recuperarPorFuncao(funcao);
		} else {
			funcoesProcedimentos = funcaoProcedimentoService.recuperarPorProcedimento(funcao, procedimentoPesquisa);
		}
    }    
	
	public List<FuncaoProcedimento> getFuncoesProcedimentos() {
		return funcoesProcedimentos;
	}

	public void setFuncoesProcedimentos(List<FuncaoProcedimento> funcoesProcedimentos) {
		this.funcoesProcedimentos = funcoesProcedimentos;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		setFuncoesProcedimentos(null); // se está mudando a empresa, então zerar a lista.
		this.funcao = funcao;
	}

	public FuncaoProcedimento getFuncaoProcedimento() {
		return funcaoProcedimento;
	}

	public void setFuncaoProcedimento(FuncaoProcedimento funcaoProcedimento) {
		this.funcaoProcedimento = funcaoProcedimento;
	}

	public Procedimento getProcedimentoPesquisa() {
		return procedimentoPesquisa;
	}

	public void setProcedimentoPesquisa(Procedimento procedimentoPesquisa) {
		this.procedimentoPesquisa = procedimentoPesquisa;
	}

	public List<Procedimento> getProcedimentos() {
		if (procedimentos == null || procedimentos.isEmpty()) {
			procedimentos = procedimentoService.recuperarTodos("nome");
		}
		return procedimentos;
	}


}