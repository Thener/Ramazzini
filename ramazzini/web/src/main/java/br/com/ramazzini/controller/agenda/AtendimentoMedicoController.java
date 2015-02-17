package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.agenda.Agenda;
import br.com.ramazzini.model.agenda.SituacaoMarcacaoAgenda;
import br.com.ramazzini.model.notificacao.Notificacao;
import br.com.ramazzini.model.parametro.ParametroSistema;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.entidade.AgendaService;
import br.com.ramazzini.service.entidade.ParametroService;
import br.com.ramazzini.util.TimeFactory;

@Named
@ConversationScoped
public class AtendimentoMedicoController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject private AgendaService agendaService;
	@Inject private ParametroService parametroService;
	
	private Date dataSelecionada = TimeFactory.createDataHora();
	
	private String situacaoMarcacaoAgenda = SituacaoMarcacaoAgenda.AGUARDANDO.getValue();
	
	private int totalAgendamentos;
	
	private List<Agenda> agendamentos = new ArrayList<Agenda>();
	
	private String tempoAtualizacaoAutomatica;
	
	private Date ultimaAtualizacaoAgenda;
	
	private Profissional medicoLogado;

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
			agendamentos = agendaService.recuperarPorFiltros(dataSelecionada, situacaoMarcacaoAgenda, medicoLogado);
			ultimaAtualizacaoAgenda = TimeFactory.createDataHora();
		}
		return agendamentos;
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
		return medicoLogado;
	}	

	
}