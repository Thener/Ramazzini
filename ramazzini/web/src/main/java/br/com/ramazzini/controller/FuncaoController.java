package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.riscoOcupacional.RiscoOcupacional;
import br.com.ramazzini.service.FuncaoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class FuncaoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_FUNCAO = "/pages/funcao/cadastroFuncao.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_EMPRESA = "/pages/empresa/cadastroEmpresa.jsf?faces-redirect=true";
	
    @Inject
    private FuncaoService funcaoService; 
    
    private List<Funcao> funcoes;
    
    private Empresa empresa;
    
    private Funcao funcao;
    
    private String nomeFuncaoPesquisa;
    
    private RiscoOcupacional riscoOcupacionalSelecionado;
    
    private List<RiscoOcupacional> riscosOcupacionais;
    
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
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "Função");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro", "a função.");
        }
    }    
    
    private String cadastroFuncao(Funcao funcao, Boolean somenteLeitura) {

    	if (!funcao.isNovo()) {
    		riscosOcupacionais = funcaoService.recuperarRiscosOcupacionais(funcao);
    		funcao.setRiscosOcupacionais(riscosOcupacionais);
    	}
    	riscoOcupacionalSelecionado = null;
    	setFuncao(funcao);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_FUNCAO;    	
    }
    
	public String gravarFuncao() {
		
		funcaoService.salvar(funcao);
		pesquisar();
		return PAGINA_CADASTRO_EMPRESA;
	}
    
    public void pesquisar() {
		
    	if (nomeFuncaoPesquisa == null || nomeFuncaoPesquisa.isEmpty()){
    		funcoes = funcaoService.recuperarPorEmpresa(empresa);
		} else {
			funcoes = funcaoService.recuperarPorNome(empresa, nomeFuncaoPesquisa);
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

}