package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.horarioAtendimento.HorarioAtendimento;
import br.com.ramazzini.model.programacaoHorarioAtendimento.ProgramacaoHorarioAtendimento;
import br.com.ramazzini.service.entidade.ProgramacaoHorarioAtendimentoService;

@Named
@ConversationScoped
public class ProgramacaoHorarioAtendimentoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_PROGRAMACAO = "/pages/horarioAtendimento/cadastroProgramacaoHorarioAtendimento.jsf?faces-redirect=true";
	//private static final String PAGINA_CADASTRO_HORARIO_ATENDIMENTO = "/pages/horarioAtendimento/cadastroHorarioAtendimento.jsf?faces-redirect=true";
	
    @Inject
    private ProgramacaoHorarioAtendimentoService programacaoHorarioAtendimentoService; 
    
    private List<ProgramacaoHorarioAtendimento> programacoes;
    
    private HorarioAtendimento horarioAtendimento;
    
    private ProgramacaoHorarioAtendimento programacao;
    
	public void incluirProgramacao() {
		
		programacao = new ProgramacaoHorarioAtendimento();
		programacao.setHorarioAtendimento(horarioAtendimento);
		//return cadastroProgramacao(programacao, Boolean.FALSE);
	}
	    
    public String alterarProgramacao(ProgramacaoHorarioAtendimento programacao){
    	
    	return cadastroProgramacao(programacao, Boolean.FALSE);
    }
    
    public String visualizarProgramacao(ProgramacaoHorarioAtendimento programacao){
    	
    	return cadastroProgramacao(programacao, Boolean.TRUE);
    }
    
    public void removerProgramacao(ProgramacaoHorarioAtendimento programacao){
    	
    	programacoes.remove(programacao);
    }    
    
    private String cadastroProgramacao(ProgramacaoHorarioAtendimento programacao, Boolean somenteLeitura) {

    	setProgramacaoHorarioAtendimento(programacao);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_PROGRAMACAO;    	
    }
    
	public void adicionarProgramacao() {
		
		programacoes.add(programacao);
		incluirProgramacao();
	}
	
	public List<ProgramacaoHorarioAtendimento> getProgramacoes() {
		return programacoes;
	}

	public void setProgramacoes(List<ProgramacaoHorarioAtendimento> programacoes) {
		this.programacoes = programacoes;
	}

	public HorarioAtendimento getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(HorarioAtendimento horarioAtendimento) {
		setProgramacoes(programacaoHorarioAtendimentoService.recuperarPorHorarioAtendimento(horarioAtendimento)); // se está mudando a empresa, então recarregar a lista.
		this.horarioAtendimento = horarioAtendimento;
	}

	public ProgramacaoHorarioAtendimento getProgramacao() {
		return programacao;
	}

	public void setProgramacaoHorarioAtendimento(ProgramacaoHorarioAtendimento programacao) {
		this.programacao = programacao;
	}

}