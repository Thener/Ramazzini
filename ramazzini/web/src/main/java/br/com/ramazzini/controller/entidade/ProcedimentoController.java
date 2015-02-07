package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoProcedimento;
import br.com.ramazzini.service.entidade.ProcedimentoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class ProcedimentoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_PROCEDIMENTO = "pesquisarProcedimento.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_PROCEDIMENTO = "cadastroProcedimento.jsf?faces-redirect=true";

	@Inject
	private ProcedimentoCredenciadoController procedimentoCredenciadoController;
	
    @Inject
    private ProcedimentoService procedimentoService;  	
	
	private Procedimento procedimento;
	
	private List<Procedimento> procedimentos;
	
	private String nomeProcedimentoPesquisa;
		
	private Integer tabAtiva;
	
	@PostConstruct
	public void init() {

		beginConversation();
	}
		
    public void pesquisar() throws Exception {

    	if (nomeProcedimentoPesquisa.isEmpty()){
    		procedimentos = procedimentoService.recuperarTodos("nome");
		} else {
			procedimentos = procedimentoService.recuperarPorNome(nomeProcedimentoPesquisa);
		}      
    }  
    
	public String incluirProcedimento() {
		
		procedimento = new Procedimento();
		return cadastroProcedimento(procedimento, Boolean.FALSE);
	}    
	
    public String alterarProcedimento(Procedimento procedimento){
    	
    	return cadastroProcedimento(procedimento, Boolean.FALSE);
    }	
    
    public String visualizarProcedimento(Procedimento procedimento){
    	
    	return cadastroProcedimento(procedimento, Boolean.TRUE);
    }
    
    public void removerProcedimento(Procedimento procedimento){
    	
    	try {
    		procedimentoService.remover(procedimento, procedimento.getId());
    		procedimentos.remove(procedimento);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.registroExcluidoComSucesso");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro","o procedimento.");
        }
    }
    
	public String gravarProcedimento() {
		
		if (validar(procedimento)) {
			procedimentoService.salvar(procedimento);
			procedimentos = procedimentoService.recuperarTodos("nome");
			return PAGINA_PESQUISAR_PROCEDIMENTO;
		}
		
		return "";
	}    
    
    private String cadastroProcedimento(Procedimento procedimento, Boolean somenteLeitura) {
    	procedimentoCredenciadoController.setProcedimento(procedimento);
    	setProcedimento(procedimento);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_PROCEDIMENTO;    	
    } 
    
    public boolean validar(Procedimento procedimento) {
    	
    	if (procedimento.getTipoProcedimentoEnum().equals(TipoProcedimento.EXAME_CLINICO_OCUPACIONAL)
    			&& procedimento.getTipoExameClinicoEnum() == null) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.informacaoObrigatoria","label.tipoExameClinico");
    		return false;
    	}
    	
    	return true;
    }

	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public String getNomeProcedimentoPesquisa() {
		return nomeProcedimentoPesquisa;
	}

	public void setNomeProcedimentoPesquisa(String nomeProcedimentoPesquisa) {
		this.nomeProcedimentoPesquisa = nomeProcedimentoPesquisa;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}
	
	public Integer getTabActiveIndex() {
        return tabAtiva;
    }

    public void setTabActiveIndex(Integer tabActiveIndex) {
        this.tabAtiva = tabActiveIndex;
    }
    
}