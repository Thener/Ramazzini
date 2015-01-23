package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.horarioAtendimento.HorarioAtendimento;
import br.com.ramazzini.service.entidade.HorarioAtendimentoService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class HorarioAtendimentoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_PESQUISAR_HORARIO_ATENDIMENTO = "pesquisarHorarioAtendimento.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_HORARIO_ATENDIMENTO = "cadastroHorarioAtendimento.jsf?faces-redirect=true";

	private @Inject Conversation conversation;
	
    @Inject
    private HorarioAtendimentoService horarioAtendimentoService;  
    
    @Inject
    private ProgramacaoHorarioAtendimentoController programacaoHorarioAtendimentoController;     
	
	private HorarioAtendimento horarioAtendimento;
	
	private List<HorarioAtendimento> horariosAtendimento;
	
	private String nomeHorarioAtendimentoPesquisa;
	
	@PostConstruct
	public void init() {

		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
    
	public String incluirHorarioAtendimento() {
		
		horarioAtendimento = new HorarioAtendimento();
		return cadastroHorarioAtendimento(horarioAtendimento, Boolean.FALSE);
	}    
	
    public String alterarHorarioAtendimento(HorarioAtendimento horarioAtendimento){
    	
    	return cadastroHorarioAtendimento(horarioAtendimento, Boolean.FALSE);
    }	
    
    public String visualizarHorarioAtendimento(HorarioAtendimento horarioAtendimento){
    	
    	return cadastroHorarioAtendimento(horarioAtendimento, Boolean.TRUE);
    }
    
    public void removerHorarioAtendimento(HorarioAtendimento horarioAtendimento){
    	
    	try {
    		horarioAtendimentoService.remover(horarioAtendimento, horarioAtendimento.getId());
    		horariosAtendimento.remove(horarioAtendimento);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.registroExcluidoComSucesso");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro","o profissional.");
        }
    }
    
	public String gravarHorarioAtendimento() {
		
		boolean inclusao = horarioAtendimento.isNovo();
		horarioAtendimentoService.salvar(horarioAtendimento);
		if (inclusao) {
			return alterarHorarioAtendimento(horarioAtendimento);
		} else {
			horariosAtendimento = horarioAtendimentoService.recuperarTodos("nome");
			return PAGINA_PESQUISAR_HORARIO_ATENDIMENTO;
		}		
		
	}    
    
    private String cadastroHorarioAtendimento(HorarioAtendimento horarioAtendimento, Boolean somenteLeitura) {
    	
    	setHorarioAtendimento(horarioAtendimento);
    	programacaoHorarioAtendimentoController.setHorarioAtendimento(horarioAtendimento);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_HORARIO_ATENDIMENTO;    	
    } 
    
    public void pesquisar() {

    	if (nomeHorarioAtendimentoPesquisa.isEmpty()){
    		horariosAtendimento = horarioAtendimentoService.recuperarTodos("nome");
		} else {
			horariosAtendimento = horarioAtendimentoService.recuperarPorNome(nomeHorarioAtendimentoPesquisa);
		}      
    }     

	public List<HorarioAtendimento> getHorariosAtendimento() {
		return horariosAtendimento;
	}

	public String getNomeHorarioAtendimentoPesquisa() {
		return nomeHorarioAtendimentoPesquisa;
	}

	public void setNomeHorarioAtendimentoPesquisa(String nomeHorarioAtendimentoPesquisa) {
		this.nomeHorarioAtendimentoPesquisa = nomeHorarioAtendimentoPesquisa;
	}

	public HorarioAtendimento getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(HorarioAtendimento horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public ProgramacaoHorarioAtendimentoController getProgramacaoHorarioAtendimentoController() {
		return programacaoHorarioAtendimentoController;
	}
	
}