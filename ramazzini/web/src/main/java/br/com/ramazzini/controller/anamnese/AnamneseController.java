package br.com.ramazzini.controller.anamnese;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.profissional.Profissional;

@Named
@ConversationScoped
public class AnamneseController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_ANAMNESE = "/pages/anamnese/cadastroAnamnese.jsf?faces-redirect=true";

	public String init(Funcionario funcionario, Profissional medico) {
		
		beginConversation();
		setUriRequisicao(getControleAcesso().getUriRequisicao());
		return PAGINA_ANAMNESE;
	}	

	public void gravarAnamnese() {
		
	}
	
    public String voltar() {	
    	endConversation();
		return getUriRequisicao()+"?faces-redirect=true";
	}	
}