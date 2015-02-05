package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.service.entidade.FuncionarioService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class FuncionarioController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_FUNCIONARIO = "/pages/funcionario/cadastroFuncionario.jsf?faces-redirect=true";
	
    @Inject
    private FuncionarioService funcionarioService; 
    
    private List<Funcionario> funcionarios;
    
    private Empresa empresa;
    
    private Funcionario funcionario;
    
    private String nomeFuncionarioPesquisa;
    
	public String incluirFuncionario() {
		
		funcionario = new Funcionario();
		funcionario.setEmpresa(empresa);
		return cadatroFuncionario(funcionario, Boolean.FALSE);
	}
	    
    public String alterarFuncionario(Funcionario funcionario){
    	
    	return cadatroFuncionario(funcionario, Boolean.FALSE);
    }    
    
    public String visualizarFuncionario(Funcionario funcionario){
    	
    	return cadatroFuncionario(funcionario, Boolean.TRUE);
    }
    
    public void removerFuncionario(Funcionario funcionario){
    	
    	try {
    		funcionarioService.remover(funcionario, funcionario.getId());
    		funcionarios.remove(funcionario);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "label.funcionario");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro", "label.funcionario");
        }
    }    
    
    private String cadatroFuncionario(Funcionario funcionario, Boolean somenteLeitura) {
    	setEmpresa(funcionario.getEmpresa());
    	setFuncionario(funcionario);
    	setSomenteLeitura(somenteLeitura);
    	setUriRequisicao(getControleAcesso().getUriRequisicao());
    	return PAGINA_CADASTRO_FUNCIONARIO;    	
    }
    
	public String gravarFuncionario() {
		
		funcionarioService.salvar(funcionario);
		pesquisar();
		return voltar();
	}
    
    public void pesquisar() {
		
    	if (nomeFuncionarioPesquisa == null || nomeFuncionarioPesquisa.isEmpty()){
    		funcionarios = funcionarioService.recuperarPorEmpresa(empresa);
		} else {
			funcionarios = funcionarioService.recuperarPorNome(empresa, nomeFuncionarioPesquisa);
		}      
    }    
    
    public void pesquisarGeral() {		
    	if (!nomeFuncionarioPesquisa.isEmpty()){
			funcionarios = funcionarioService.recuperarPorNome(nomeFuncionarioPesquisa);
		} else {
			UtilMensagens.mensagemInformacaoPorChave("mensagem.info.nomePesquisaNaoInformado", "label.funcionario");
		}
    }    
    
    public String voltar() {				
		return getUriRequisicao()+"?faces-redirect=true";
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
 
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		setFuncionarios(null); // se está mudando a empresa, então zerar a lista.
		this.empresa = empresa;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getNomeFuncionarioPesquisa() {
		return nomeFuncionarioPesquisa;
	}

	public void setNomeFuncionarioPesquisa(String nomeFuncionarioPesquisa) {
		this.nomeFuncionarioPesquisa = nomeFuncionarioPesquisa;
	}

}