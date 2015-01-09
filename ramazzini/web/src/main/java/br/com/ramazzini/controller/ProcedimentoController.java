package br.com.ramazzini.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoProcedimento;
import br.com.ramazzini.service.ProcedimentoService;

@Named
@ConversationScoped
public class ProcedimentoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private @Inject Conversation conversation;
	
    @Inject
    private ProcedimentoService procedimentoService;  	
	
	private Procedimento procedimentoNovo;
	
	@PostConstruct
	public void init() {

		procedimentoNovo = new Procedimento();
		
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	public void salvar() {
		procedimentoNovo.setTipoProcedimento(TipoProcedimento.EXAME_COMPLEMENTAR);
		procedimentoService.salvar(procedimentoNovo);
	}

	public Procedimento getProcedimentoNovo() {
		return procedimentoNovo;
	}

	public void setProcedimentoNovo(Procedimento procedimentoNovo) {
		this.procedimentoNovo = procedimentoNovo;
	}




    
}