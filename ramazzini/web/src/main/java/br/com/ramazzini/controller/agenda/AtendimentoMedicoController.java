package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.agenda.Agenda;
import br.com.ramazzini.model.agenda.SituacaoMarcacaoAgenda;
import br.com.ramazzini.model.notificacao.Notificacao;
import br.com.ramazzini.model.parametro.ParametroSistema;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.entidade.AgendaService;
import br.com.ramazzini.service.entidade.ParametroService;
import br.com.ramazzini.service.entidade.ProfissionalService;
import br.com.ramazzini.util.TimeFactory;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class AtendimentoMedicoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject private AgendaService agendaService;
	@Inject private ParametroService parametroService;
	@Inject private ProfissionalService profissionalService;
	
	private Date dataSelecionada = TimeFactory.createDataHora();
	
	private String situacaoMarcacaoAgenda;
	
	private int totalAgendamentos;
	
	private List<Agenda> agendamentos = new ArrayList<Agenda>();
	
	private String tempoAtualizacaoAutomatica;
	
	private Date ultimaAtualizacaoAgenda;
	
	private Profissional medicoLogado;
	
	private Agenda agendaSelecionada;

	@PostConstruct
	public void init() {
		if (situacaoMarcacaoAgenda == null) {
			situacaoMarcacaoAgenda = SituacaoMarcacaoAgenda.AGUARDANDO.getValue();
		}
	}
	
	public void atualizacaoAutomatica() {
		agendamentos.clear();
	}
	
	public void atualizacaoManual() {
		agendamentos.clear();
	}
	
	public void onChangeDataSelecionada() {
		agendamentos.clear();
	}
	
	public void onChangeSituacaoMarcacaoAgenda() {
		agendamentos.clear();
	}	
	
	public void contarAgendamentos() {
		this.totalAgendamentos = agendamentos.size();
	}
	
	public List<Agenda> getAgendamentos() {
		if (agendamentos.isEmpty() || ultimaAtualizacaoAgenda.before(Notificacao.getUltimaModificacaoAgenda())) {
			agendamentos = agendaService.recuperarPorFiltros(dataSelecionada, situacaoMarcacaoAgenda, null);
			ultimaAtualizacaoAgenda = TimeFactory.createDataHora();
		}
		return agendamentos;
	}
	
	public void alterarSituacaoAgenda(String situacao) {
		agendaSelecionada.setSituacaoMarcacaoAgenda(situacao);
		gravarAgenda(agendaSelecionada);
	}
	
	public void atribuirme() {
		
		 if (agendaSelecionada.getProfissional() == null) {
			agendaSelecionada.setProfissional(medicoLogado);
			gravarAgenda(agendaSelecionada);			 
		 } else if (agendaSelecionada.getProfissional() != null) {
			UtilMensagens.mensagemErroPorChave("mensagem.erro.agendamentoAtribuido", 
				agendaSelecionada.getProfissional().getNome());			 
		 }
	}
	
	public void desatribuirme() {
		
		if (medicoLogado.equals(agendaSelecionada.getProfissional())) {
			agendaSelecionada.setProfissional(null);
			gravarAgenda(agendaSelecionada);
		} else if (agendaSelecionada.getProfissional() == null) {
			UtilMensagens.mensagemErroPorChave("mensagem.erro.agendamentoNaoEstaAtribuido");			
		} else {
			UtilMensagens.mensagemErroPorChave("mensagem.erro.agendamentoAtribuido", 
					agendaSelecionada.getProfissional().getNome());
		}
	}
	
	public void gravarAgenda(Agenda agenda) {
		agendaService.salvar(agenda);
		notificarModificacaoAgenda();
	}
	
	private void notificarModificacaoAgenda() {
		Notificacao.notificarModificacaoAgenda();
		sendNotify();
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.houveUmaAtualizacaoDaAgenda");
	}
	
    public void sendNotify() {
    	EventBus eventBus = EventBusFactory.getDefault().eventBus();
    	eventBus.publish("/agenda", "");
    }
    
	public Date getDataSelecionada() {
		return dataSelecionada;
	}

	public void setDataSelecionada(Date dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}
	
	public String getDiaSemana() {
		return TimeFactory.diaDaSemana(dataSelecionada).getStringChave();
	}	
	
	public String getTempoAtualizacaoAutomatica() {
		if (tempoAtualizacaoAutomatica == null || tempoAtualizacaoAutomatica.isEmpty() ) {
			tempoAtualizacaoAutomatica = parametroService.recuperarPorParametroSistema(
					ParametroSistema.AGENDA_TEMPO_ATUALIZACAO_AUTOMATICA).getValor(); 
		}
		return tempoAtualizacaoAutomatica;
	}	
	
	public String getSituacaoMarcacaoAgenda() {
		return situacaoMarcacaoAgenda;
	}

	public void setSituacaoMarcacaoAgenda(
			String situacaoMarcacaoAgenda) {
		this.situacaoMarcacaoAgenda = situacaoMarcacaoAgenda;
	}
	
	public int getTotalAgendamentos() {
		return totalAgendamentos;
	}

	public Profissional getMedicoLogado() {
		if (medicoLogado == null) {
			medicoLogado = profissionalService.recuperarPorUsuario(getUsuarioLogado());
		}
		return medicoLogado;
	}

	public Agenda getAgendaSelecionada() {
		return agendaSelecionada;
	}

	public void setAgendaSelecionada(Agenda agendaSelecionada) {
		this.agendaSelecionada = agendaSelecionada;
	}	
	
}