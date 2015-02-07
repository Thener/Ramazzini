package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.avaliacaoClinicaProcedimento.AvaliacaoClinicaProcedimento;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.service.entidade.AvaliacaoClinicaProcedimentoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class AvaliacaoClinicaProcedimentoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_AVALIACAO_CLINICA_PROCEDIMENTO = "/pages/avaliacaoClinicaProcedimento/cadastroAvaliacaoClinicaProcedimento.jsf?faces-redirect=true";
	
	@Inject private AvaliacaoClinicaProcedimentoService avaliacaoClinicaProcedimentoService;
	
	private Procedimento procedimentoSelecionado;
	
	private AvaliacaoClinica avaliacaoClinica;
	
	private AvaliacaoClinicaProcedimento avaliacaoClinicaProcedimento; 
	
	private List<AvaliacaoClinicaProcedimento> avaliacoesClinicasProcedimentos;

	public String incluirAvaliacaoClinicaProcedimento() {
		
		avaliacaoClinicaProcedimento = new AvaliacaoClinicaProcedimento();
		avaliacaoClinicaProcedimento.setAvaliacaoClinica(avaliacaoClinica);
		return cadastroAvaliacaoClinicaProcedimento(avaliacaoClinicaProcedimento, Boolean.FALSE);
	}
	    
    public String alterarAvaliacaoClinicaProcedimento(AvaliacaoClinicaProcedimento avaliacaoClinicaProcedimento){
    	
    	return cadastroAvaliacaoClinicaProcedimento(avaliacaoClinicaProcedimento, Boolean.FALSE);
    }
    
    public String visualizarAvaliacaoClinicaProcedimento(AvaliacaoClinicaProcedimento avaliacaoClinicaProcedimento){
    	
    	return cadastroAvaliacaoClinicaProcedimento(avaliacaoClinicaProcedimento, Boolean.TRUE);
    }
    
    public void removerAvaliacaoClinicaProcedimento(AvaliacaoClinicaProcedimento avaliacaoClinicaProcedimento){
    	
    	try {
    		avaliacaoClinicaProcedimentoService.remover(avaliacaoClinicaProcedimento, avaliacaoClinicaProcedimento.getId());
    		avaliacoesClinicasProcedimentos.remove(avaliacaoClinicaProcedimento);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "label.procedimento");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro", "label.procedimento");
        }
    }    
    
    private String cadastroAvaliacaoClinicaProcedimento(AvaliacaoClinicaProcedimento avaliacaoClinicaProcedimento, Boolean somenteLeitura) {

    	setAvaliacaoClinicaProcedimento(avaliacaoClinicaProcedimento);
    	setAvaliacaoClinica(avaliacaoClinicaProcedimento.getAvaliacaoClinica());
    	setSomenteLeitura(somenteLeitura);
    	setUriRequisicao(getControleAcesso().getUriRequisicao());
    	return PAGINA_CADASTRO_AVALIACAO_CLINICA_PROCEDIMENTO;    	
    }
    
	public String gravarAvaliacaoClinicaProcedimento() {

		boolean inclusao = avaliacaoClinicaProcedimento.isNovo();
		avaliacaoClinicaProcedimentoService.salvar(avaliacaoClinicaProcedimento);
		if (inclusao) {
			return "";
		} else {
			pesquisar();
			return voltar();
		}
		
	}
    
    public void pesquisar() {
		
    	if (procedimentoSelecionado == null) {
    		avaliacoesClinicasProcedimentos = avaliacaoClinicaProcedimentoService.recuperarPorAvaliacaoClinica(avaliacaoClinica);
    	} else {
    		avaliacoesClinicasProcedimentos = avaliacaoClinicaProcedimentoService.recuperarPorProcedimento(avaliacaoClinica, procedimentoSelecionado);
    	}
     
    }
	
    public String voltar() {				
 		return getUriRequisicao()+"?faces-redirect=true";
 	}
    
	public Procedimento getProcedimentoSelecionado() {
		return procedimentoSelecionado;
	}

	public void setProcedimentoSelecionado(Procedimento procedimentoSelecionado) {
		this.procedimentoSelecionado = procedimentoSelecionado;
	}

	public AvaliacaoClinica getAvaliacaoClinica() {
		return avaliacaoClinica;
	}

	public void setAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica) {
		setAvaliacoesClinicasProcedimentos(null);
		this.avaliacaoClinica = avaliacaoClinica;
	}

	public AvaliacaoClinicaProcedimento getAvaliacaoClinicaProcedimento() {
		return avaliacaoClinicaProcedimento;
	}

	public void setAvaliacaoClinicaProcedimento(
			AvaliacaoClinicaProcedimento avaliacaoClinicaProcedimento) {
		this.avaliacaoClinicaProcedimento = avaliacaoClinicaProcedimento;
	}

	public List<AvaliacaoClinicaProcedimento> getAvaliacoesClinicasProcedimentos() {
		return avaliacoesClinicasProcedimentos;
	}

	public void setAvaliacoesClinicasProcedimentos(
			List<AvaliacaoClinicaProcedimento> avaliacoesClinicasProcedimentos) {
		this.avaliacoesClinicasProcedimentos = avaliacoesClinicasProcedimentos;
	}
	
}