package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
	
	private @Inject ParametroService parametroService;

	private Parametro parametroAgendaTempoAtualizacaoAutomatica;
	
	private Parametro parametroAgendaStatusAtualizacaoAutomatica;
	
	private Parametro parametroAgendaStatusNotificacaoAutomatica;
	
	private Parametro parametroAcessoNumTentativasLogin;	
	
	private List<Parametro> listToSave = new ArrayList<Parametro>();
		
	@PostConstruct
	public void init() {

		beginConversation();
		
		parametroAgendaTempoAtualizacaoAutomatica = 
			parametroService.recuperarPorParametroSistema(ParametroSistema.AGENDA_TEMPO_ATUALIZACAO_AUTOMATICA);
		parametroAgendaStatusAtualizacaoAutomatica = 
				parametroService.recuperarPorParametroSistema(ParametroSistema.AGENDA_STATUS_ATUALIZACAO_AUTOMATICA);
		parametroAgendaStatusNotificacaoAutomatica = 
				parametroService.recuperarPorParametroSistema(ParametroSistema.AGENDA_STATUS_NOTIFICACAO_AUTOMATICA);		
		parametroAcessoNumTentativasLogin = 
			parametroService.recuperarPorParametroSistema(ParametroSistema.ACESSO_NUM_MAX_TENTATIVAS_LOGIN);
		
		listToSave.add(parametroAgendaTempoAtualizacaoAutomatica);
		listToSave.add(parametroAgendaStatusAtualizacaoAutomatica);
		listToSave.add(parametroAgendaStatusNotificacaoAutomatica);
		listToSave.add(parametroAcessoNumTentativasLogin);
	}
	
	public void gravar() {
		
		parametroService.salvarLista(listToSave);
		
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.dadosGravadosComSucesso","label.parametros");
	}
	
	public Parametro getParametroAgendaCriarSabado() {
		return parametroAgendaTempoAtualizacaoAutomatica;
	}

	public void setParametroAgendaCriarSabado(Parametro parametroAgendaCriarSabado) {
		this.parametroAgendaTempoAtualizacaoAutomatica = parametroAgendaCriarSabado;
	}
	
	public Parametro getParametroAgendaTempoAtualizacaoAutomatica() {
		return parametroAgendaTempoAtualizacaoAutomatica;
	}

	public void setParametroAgendaTempoAtualizacaoAutomatica(
			Parametro parametroAgendaTempoAtualizacaoAutomatica) {
		this.parametroAgendaTempoAtualizacaoAutomatica = parametroAgendaTempoAtualizacaoAutomatica;
	}

	public Parametro getParametroAgendaStatusAtualizacaoAutomatica() {
		return parametroAgendaStatusAtualizacaoAutomatica;
	}

	public void setParametroAgendaStatusAtualizacaoAutomatica(
			Parametro parametroAgendaStatusAtualizacaoAutomatica) {
		this.parametroAgendaStatusAtualizacaoAutomatica = parametroAgendaStatusAtualizacaoAutomatica;
	}

	public Parametro getParametroAcessoNumTentativasLogin() {
		return parametroAcessoNumTentativasLogin;
	}

	public void setParametroAcessoNumTentativasLogin(
			Parametro parametroAcessoNumTentativasLogin) {
		this.parametroAcessoNumTentativasLogin = parametroAcessoNumTentativasLogin;
	}

	public Parametro getParametroAgendaStatusNotificacaoAutomatica() {
		return parametroAgendaStatusNotificacaoAutomatica;
	}

	public void setParametroAgendaStatusNotificacaoAutomatica(
			Parametro parametroAgendaStatusNotificacaoAutomatica) {
		this.parametroAgendaStatusNotificacaoAutomatica = parametroAgendaStatusNotificacaoAutomatica;
	}
	
	
	
}