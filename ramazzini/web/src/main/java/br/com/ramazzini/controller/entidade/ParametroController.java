package br.com.ramazzini.controller.entidade;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.parametro.Parametro;
import br.com.ramazzini.model.parametro.ParametroSistema;
import br.com.ramazzini.service.entidade.ParametroService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class ParametroController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private @Inject Conversation conversation;
	
	private @Inject ParametroService parametroService;

	private Parametro parametroAgendaCriarSabado;
	
	private Parametro parametroAgendaCriarDomingo;

	private Parametro parametroAgendaCriarFeriado;	
		
	@PostConstruct
	public void init() {

		if (conversation.isTransient()) {
			conversation.begin();
		}
		
		parametroAgendaCriarSabado = parametroService.recuperarPorNome(ParametroSistema.AGENDA_CRIAR_SABADO.getValue());
		parametroAgendaCriarDomingo = parametroService.recuperarPorNome(ParametroSistema.AGENDA_CRIAR_DOMINGO.getValue());
		parametroAgendaCriarFeriado = parametroService.recuperarPorNome(ParametroSistema.AGENDA_CRIAR_FERIADO.getValue());
	}
	
	public void gravar() {
		
		parametroService.salvar(parametroAgendaCriarSabado);
		parametroService.salvar(parametroAgendaCriarDomingo);
		parametroService.salvar(parametroAgendaCriarFeriado);
		
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.dadosGravadosComSucesso","Parâmetros");
	}

	public Parametro getParametroAgendaCriarSabado() {
		return parametroAgendaCriarSabado;
	}

	public void setParametroAgendaCriarSabado(Parametro parametroAgendaCriarSabado) {
		this.parametroAgendaCriarSabado = parametroAgendaCriarSabado;
	}

	public Parametro getParametroAgendaCriarDomingo() {
		return parametroAgendaCriarDomingo;
	}

	public void setParametroAgendaCriarDomingo(Parametro parametroAgendaCriarDomingo) {
		this.parametroAgendaCriarDomingo = parametroAgendaCriarDomingo;
	}

	public Parametro getParametroAgendaCriarFeriado() {
		return parametroAgendaCriarFeriado;
	}

	public void setParametroAgendaCriarFeriado(Parametro parametroAgendaCriarFeriado) {
		this.parametroAgendaCriarFeriado = parametroAgendaCriarFeriado;
	}


		

	
}