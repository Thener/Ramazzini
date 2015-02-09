package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.service.entidade.AvaliacaoClinicaService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class AvaliacaoClinicaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_AVALIACAO_CLINICA = "/pages/avaliacaoClinica/cadastroAvaliacaoClinica.jsf?faces-redirect=true";
	
	@Inject private AvaliacaoClinicaService avaliacaoClinicaService;
	@Inject private AvaliacaoClinicaProcedimentoController avaliacaoClinicaProcedimentoController;
	
	private Date dataInicialSelecionada;
	
	private Date dataFinalSelecionada;
	
	private AvaliacaoClinica avaliacaoClinica;
	
	private Funcionario funcionario;
	
	private List<AvaliacaoClinica> avaliacoesClinicas;
	
	private Integer tabAtiva;
	
	public String incluirAvaliacaoClinica() {
		
		avaliacaoClinica = new AvaliacaoClinica();
		avaliacaoClinica.setFuncionario(funcionario);
		avaliacaoClinica.setFuncaoAtual(funcionario.getFuncao());
		return cadastroAvaliacaoClinica(avaliacaoClinica, Boolean.FALSE);
	}
	    
    public String alterarAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica){
    	
    	return cadastroAvaliacaoClinica(avaliacaoClinica, Boolean.FALSE);
    }
    
    public String visualizarAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica){
    	
    	return cadastroAvaliacaoClinica(avaliacaoClinica, Boolean.TRUE);
    }
    
    public void removerAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica){
    	
    	try {
    		avaliacaoClinicaService.remover(avaliacaoClinica, avaliacaoClinica.getId());
    		avaliacoesClinicas.remove(avaliacaoClinica);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "label.avaliacaoClinica");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro", "label.avaliacaoClinica");
        }
    }    
    
    private String cadastroAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica, Boolean somenteLeitura) {

    	setTabActiveIndex(0);
    	setAvaliacaoClinica(avaliacaoClinica);
    	setFuncionario(avaliacaoClinica.getFuncionario());
    	avaliacaoClinicaProcedimentoController.setAvaliacaoClinica(avaliacaoClinica);
    	setSomenteLeitura(somenteLeitura);
    	setUriRequisicao(getControleAcesso().getUriRequisicao());
    	return PAGINA_CADASTRO_AVALIACAO_CLINICA;    	
    }
    
	public String gravarAvaliacaoClinica() {

		boolean inclusao = avaliacaoClinica.isNovo();
		avaliacaoClinicaService.salvar(avaliacaoClinica);
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeGravadaComSucesso", "label.avaliacaoClinica");
		if (inclusao) {
			return "";
		} else {
			pesquisar();
			return voltar();
		}
		
	}
    
    public void pesquisar() {
		
    	avaliacoesClinicas = avaliacaoClinicaService.recuperarPorFuncionario(funcionario);
     
    }
	
    public String voltar() {				
 		return getUriRequisicao()+"?faces-redirect=true";
 	}
    
	public List<AvaliacaoClinica> getAvaliacoesClinicas() {
		return avaliacoesClinicas;
	}

	public Date getDataInicialSelecionada() {
		return dataInicialSelecionada;
	}

	public void setDataInicialSelecionada(Date dataInicialSelecionada) {
		this.dataInicialSelecionada = dataInicialSelecionada;
	}

	public Date getDataFinalSelecionada() {
		return dataFinalSelecionada;
	}

	public void setDataFinalSelecionada(Date dataFinalSelecionada) {
		this.dataFinalSelecionada = dataFinalSelecionada;
	}

	public AvaliacaoClinica getAvaliacaoClinica() {
		return avaliacaoClinica;
	}

	public void setAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica) {
		this.avaliacaoClinica = avaliacaoClinica;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		setAvaliacoesClinicas(null);
		this.funcionario = funcionario;
	}
	
	public void setAvaliacoesClinicas(List<AvaliacaoClinica> avaliacoesClinicas) {
		this.avaliacoesClinicas = avaliacoesClinicas;
	}

	public Integer getTabActiveIndex() {
        return tabAtiva;
    }

    public void setTabActiveIndex(Integer tabActiveIndex) {
        this.tabAtiva = tabActiveIndex;
    }	

}