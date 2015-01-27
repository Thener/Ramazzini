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
	
    @Inject
    private ProgramacaoHorarioAtendimentoService programacaoHorarioAtendimentoService; 
    
    private List<ProgramacaoHorarioAtendimento> programacoes;
    
    private HorarioAtendimento horarioAtendimento;
    
    private ProgramacaoHorarioAtendimento programacao;
    
	public void incluirProgramacao() {
		
		programacao = new ProgramacaoHorarioAtendimento();
		programacao.setHorarioAtendimento(horarioAtendimento);
		setSomenteLeitura(Boolean.FALSE);
	}
	    
    public void alterarProgramacao(ProgramacaoHorarioAtendimento programacao){
    	
    	setSomenteLeitura(Boolean.FALSE);
    	setProgramacaoHorarioAtendimento(programacao);
    }
    
    public void visualizarProgramacao(ProgramacaoHorarioAtendimento programacao){
    	
    	setSomenteLeitura(Boolean.TRUE);
    	setProgramacaoHorarioAtendimento(programacao);
    }
    
    public void removerProgramacao(ProgramacaoHorarioAtendimento programacao){
    	
    	programacoes.remove(programacao);
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
	
	public String getHoraInicio(ProgramacaoHorarioAtendimento programacao) {
		return getFormattedTime(programacao.getHoraInicio(), "hh:mm");
	}
	
	public String getHoraFim(ProgramacaoHorarioAtendimento programacao) {
		return getFormattedTime(programacao.getHoraFim(), "hh:mm");
	}	
	
}