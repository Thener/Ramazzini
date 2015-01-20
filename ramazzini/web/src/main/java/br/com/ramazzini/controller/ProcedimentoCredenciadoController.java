package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.model.procedimentoCredenciado.ProcedimentoCredenciado;
import br.com.ramazzini.service.ProcedimentoCredenciadoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class ProcedimentoCredenciadoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_PROCEDIMENTO_CREDENCIADO = "/pages/procedimentoCredenciado/cadastroProcedimentoCredenciado.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_CREDENCIADO = "/pages/credenciado/cadastroCredenciado.jsf?faces-redirect=true";
	
    @Inject
    private ProcedimentoCredenciadoService procedimentoCredenciadoService; 
    
    private List<ProcedimentoCredenciado> procedimentosCredenciados;
    
    private ProcedimentoCredenciado procedimentoCredenciado;
    
    private Credenciado credenciado;
    
    private String nomeProcedimentoPesquisa;
    
	public String incluir() {	
		this.procedimentoCredenciado = new ProcedimentoCredenciado();
		this.procedimentoCredenciado.setCredenciado(credenciado);
		return cadastroProcedimentoCredenciado(procedimentoCredenciado, Boolean.FALSE);
	}
	    
    public String alterarProcedimentoCredenciado(ProcedimentoCredenciado procedimentoCredenciado){
    	return cadastroProcedimentoCredenciado(procedimentoCredenciado, Boolean.FALSE);
    }
    
    public String visualizarProcedimentoCredenciado(ProcedimentoCredenciado procedimentoCredenciado){
    	return cadastroProcedimentoCredenciado(procedimentoCredenciado, Boolean.TRUE);
    }
    
    public void removerProcedimentoCredenciado(ProcedimentoCredenciado procedimentoCredenciado){    	
    	try {
    		procedimentoCredenciadoService.remover(procedimentoCredenciado, procedimentoCredenciado.getId());
    		procedimentosCredenciados.remove(procedimentoCredenciado);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "Procedimento Credenciado");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro", "o Procedimento Credenciado.");
        }
    }    
    
    private String cadastroProcedimentoCredenciado(ProcedimentoCredenciado procedimentoCredenciado, Boolean somenteLeitura) {    	 	
    	setProcedimentoCredenciado(procedimentoCredenciado);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_PROCEDIMENTO_CREDENCIADO;    	
    }
    
    public boolean valoresCustoVendaValidos(){
    	if (getProcedimentoCredenciado().getPrecoCusto().compareTo(getProcedimentoCredenciado().getPrecoVenda()) == 1){
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.valoresIconsistentes");
    		return false; 
    	}
    	return true;
    }
    
	public String gravarProcedimentoCredenciado() {
		if (valoresCustoVendaValidos()){
			procedimentoCredenciadoService.salvar(procedimentoCredenciado);
			pesquisar();
			return PAGINA_CADASTRO_CREDENCIADO;
		} else {			
			return null;
		}			
	} 
    
    public void pesquisar(){
		if (nomeProcedimentoPesquisa == null || nomeProcedimentoPesquisa.isEmpty()){
    		procedimentosCredenciados = procedimentoCredenciadoService.recuperarPorCredenciado(credenciado);
		} else {
			procedimentosCredenciados = procedimentoCredenciadoService.recuperarPorNome(credenciado, nomeProcedimentoPesquisa);
		}      
    }    
	
	public List<ProcedimentoCredenciado> getProcedimentosCredenciados() {
			
		return procedimentosCredenciados;
	}

	public void setProcedimentosCredenciados(List<ProcedimentoCredenciado> procedimentosCredenciados) {
		this.procedimentosCredenciados = procedimentosCredenciados;
	}

	public Credenciado getCredenciado() {
		return credenciado;
	}

	public void setCredenciado(Credenciado credenciado) {
		setProcedimentosCredenciados(null); // se está mudando o credenciado, então zerar a lista.
		this.credenciado = credenciado;
	}

	public String getNomeProcedimentoPesquisa() {
		return nomeProcedimentoPesquisa;
	}

	public void setNomeProcedimentoPesquisa(String nomeProcedimentoPesquisa) {
		this.nomeProcedimentoPesquisa = nomeProcedimentoPesquisa;
	}

	public ProcedimentoCredenciado getProcedimentoCredenciado() {
		return procedimentoCredenciado;
	}

	public void setProcedimentoCredenciado(
			ProcedimentoCredenciado procedimentoCredenciado) {
		this.procedimentoCredenciado = procedimentoCredenciado;
	}
	
	
}