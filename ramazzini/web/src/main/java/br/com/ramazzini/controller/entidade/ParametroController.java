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
	
	private Parametro parametroAcessoNumTentativasLogin;	
	
	private List<Parametro> listToSave = new ArrayList<Parametro>();
		
	@PostConstruct
	public void init() {

		beginConversation();
		
		parametroAgendaTempoAtualizacaoAutomatica = 
			parametroService.recuperarPorParametroSistema(ParametroSistema.AGENDA_TEMPO_ATUALIZACAO_AUTOMATICA);
		parametroAcessoNumTentativasLogin = 
			parametroService.recuperarPorParametroSistema(ParametroSistema.ACESSO_NUM_MAX_TENTATIVAS_LOGIN);
		
		listToSave.add(parametroAgendaTempoAtualizacaoAutomatica);
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

	public Parametro getParametroAcessoNumTentativasLogin() {
		return parametroAcessoNumTentativasLogin;
	}

	public void setParametroAcessoNumTentativasLogin(
			Parametro parametroAcessoNumTentativasLogin) {
		this.parametroAcessoNumTentativasLogin = parametroAcessoNumTentativasLogin;
	}
	
}