package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.credenciado.Credenciado;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimentoCredenciado.ProcedimentoCredenciado;
import br.com.ramazzini.service.entidade.ProcedimentoCredenciadoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class ProcedimentoCredenciadoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_PROCEDIMENTO_CREDENCIADO = "/pages/procedimentoCredenciado/cadastroProcedimentoCredenciado.jsf?faces-redirect=true";
	 
    @Inject
    private ProcedimentoCredenciadoService procedimentoCredenciadoService; 
    
    private List<ProcedimentoCredenciado> procedimentosCredenciados;
    
    private ProcedimentoCredenciado procedimentoCredenciado;
    
    private Credenciado credenciado;
    
    private Procedimento procedimento;
    
    private String nomeProcedimentoPesquisa;
    
    private String nomeCredenciadoPesquisa;
    
	public String incluir() {	
		this.procedimentoCredenciado = new ProcedimentoCredenciado();
		this.procedimentoCredenciado.setCredenciado(credenciado);
		this.procedimentoCredenciado.setProcedimento(procedimento);
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
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "label.procedimentoCredenciado");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro", "label.procedimentoCredenciado.");
        }
    }    
    
    private String cadastroProcedimentoCredenciado(ProcedimentoCredenciado procedimentoCredenciado, Boolean somenteLeitura) {    	 	
    	setUriRequisicao(getControleAcesso().getUriRequisicao());
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
			pesquisarPorCredenciado();
			return voltar();
		} else {			
			return null;
		}			
	} 

	public String voltar() {				
		return getUriRequisicao()+"?faces-redirect=true";
	}
	
    public void pesquisarPorCredenciado(){
		if (nomeProcedimentoPesquisa == null || nomeProcedimentoPesquisa.isEmpty()){
    		procedimentosCredenciados = procedimentoCredenciadoService.recuperarPor(credenciado);
		} else {
			procedimentosCredenciados = procedimentoCredenciadoService.recuperarPorNome(credenciado, nomeProcedimentoPesquisa);
		}      
    }  
    
    public void pesquisarPorProcedimento(){
		if (nomeProcedimentoPesquisa == null || nomeProcedimentoPesquisa.isEmpty()){
    		procedimentosCredenciados = procedimentoCredenciadoService.recuperarPor(procedimento);
		} else {
			procedimentosCredenciados = procedimentoCredenciadoService.recuperarPorNome(procedimento, nomeCredenciadoPesquisa);
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

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		setProcedimentosCredenciados(null); // se está mudando, então zerar a lista.
		this.procedimento = procedimento;
	}

	public String getNomeProcedimentoPesquisa() {
		return nomeProcedimentoPesquisa;
	}

	public void setNomeProcedimentoPesquisa(String nomeProcedimentoPesquisa) {
		this.nomeProcedimentoPesquisa = nomeProcedimentoPesquisa;
	}
	
	public String getNomeCredenciadoPesquisa() {
		return nomeCredenciadoPesquisa;
	}

	public void setNomeCredenciadoPesquisa(String nomeCredenciadoPesquisa) {
		this.nomeCredenciadoPesquisa = nomeCredenciadoPesquisa;
	}

	public ProcedimentoCredenciado getProcedimentoCredenciado() {
		return procedimentoCredenciado;
	}

	public void setProcedimentoCredenciado(
			ProcedimentoCredenciado procedimentoCredenciado) {
		this.procedimentoCredenciado = procedimentoCredenciado;
	}
}