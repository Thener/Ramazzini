package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.guia.Guia;
import br.com.ramazzini.model.guiaProcedimento.GuiaProcedimento;
import br.com.ramazzini.service.entidade.FuncionarioService;
import br.com.ramazzini.service.entidade.GuiaProcedimentoService;
import br.com.ramazzini.service.entidade.GuiaService;
import br.com.ramazzini.util.TratarExcecao;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class FuncionarioController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_FUNCIONARIO = "/pages/funcionario/cadastroFuncionario.jsf?faces-redirect=true";
	
    @Inject private FuncionarioService funcionarioService;
    @Inject private AvaliacaoClinicaController avaliacaoClinicaController;
    @Inject private DeficienciaController deficienciaController;
    @Inject private GuiaService guiaService;
    @Inject private GuiaProcedimentoService guiaProcedimentoService;
    
    private List<Funcionario> funcionarios;
    
    private Empresa empresa;
    
    private Funcionario funcionario;
    
    private String nomeFuncionarioPesquisa;
    
    private Integer tabAtiva;
    
    private String exceptionMessage;
    
    private boolean showModalException = Boolean.FALSE;
    
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
    		exceptionMessage = new TratarExcecao(e).getExceptionMessage();
    		showModalException = Boolean.TRUE;
        }
    }    
    
    private String cadatroFuncionario(Funcionario funcionario, Boolean somenteLeitura) {
    	funcionario=funcionarioService.load(funcionario.getId());
    	setEmpresa(funcionario.getEmpresa());
    	setFuncionario(funcionario);
    	avaliacaoClinicaController.setFuncionario(funcionario);
    	deficienciaController.setFuncionario(funcionario);
    	setSomenteLeitura(somenteLeitura);
    	setUriRequisicao(getControleAcesso().getUriRequisicao());
    	setTabActiveIndex(0);
    	return PAGINA_CADASTRO_FUNCIONARIO;    	
    }
    
	public String gravarFuncionario() {

		boolean inclusao = funcionario.isNovo();
		funcionarioService.salvar(funcionario);
		if (inclusao) {
			UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeGravadaComSucesso","label.funcionario");
			return "";
		} else {
			pesquisar();
			return voltar();
		}		
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
    
    public List<Guia> getGuiasEmitidasParaFuncionario() {
		return guiaService.recuperarPorFuncionario(funcionario);
	}
    
    public List<GuiaProcedimento> getProcedimentosGuiaEmitida(Guia guia) {
		return guiaProcedimentoService.recuperarPor(guia);
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
	
	public Integer getTabActiveIndex() {
        return tabAtiva;
    }

    public void setTabActiveIndex(Integer tabActiveIndex) {
        this.tabAtiva = tabActiveIndex;
    }

	public String getIdadeFuncionario() {
		return funcionario.getIdadeTexto();
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public boolean isShowModalException() {
		return showModalException;
	}

	public void setShowModalException(boolean showModalException) {
		this.showModalException = showModalException;
	}	
	
	public void hideModalException() {
		this.showModalException = false;
	}	
}