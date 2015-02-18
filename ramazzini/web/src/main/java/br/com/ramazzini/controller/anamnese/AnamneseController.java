package br.com.ramazzini.controller.anamnese;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.anamnese.Anamnese;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.util.TimeFactory;

@Named
@ConversationScoped
public class AnamneseController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_ANAMNESE = "/pages/anamnese/cadastroAnamnese.jsf?faces-redirect=true";
	
	private Anamnese anamnese;
	
	private AvaliacaoClinica avaliacaoClinica;

	public String init(Funcionario funcionario, Profissional medico, Procedimento procedimento) {
		
		beginConversation();
		
		avaliacaoClinica = new AvaliacaoClinica(); // verificar criação ou continuar de onde parou (?????)
		avaliacaoClinica.setFuncionario(funcionario);
		avaliacaoClinica.setMedico(medico);
		avaliacaoClinica.setProcedimento(procedimento);

		anamnese = new Anamnese(); // verificar criação ou continuar de onde parou (????)
		anamnese.setMedico(medico);
		anamnese.setAvaliacaoClinica(avaliacaoClinica);
		anamnese.setDataRealizacao(TimeFactory.createDataHora());
		
		setUriRequisicao(getControleAcesso().getUriRequisicao());
		return PAGINA_ANAMNESE;
	}	

	public void gravarAnamnese() {
		
	}
	
    public String voltar() {	
    	endConversation();
		return getUriRequisicao()+"?faces-redirect=true";
	}

	public Anamnese getAnamnese() {
		return anamnese;
	}

	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}

	public AvaliacaoClinica getAvaliacaoClinica() {
		return avaliacaoClinica;
	}

	public void setAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica) {
		this.avaliacaoClinica = avaliacaoClinica;
	}	
    
	
}