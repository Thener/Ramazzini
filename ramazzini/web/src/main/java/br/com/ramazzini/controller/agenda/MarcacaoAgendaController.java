package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.agenda.Agenda;
import br.com.ramazzini.model.agenda.SituacaoMarcacaoAgenda;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.model.programacaoHorarioAtendimento.ProgramacaoHorarioAtendimento;
import br.com.ramazzini.service.entidade.AgendaService;
import br.com.ramazzini.service.entidade.ProfissionalService;
import br.com.ramazzini.util.TimeFactory;

@Named
@ConversationScoped
public class MarcacaoAgendaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Inject
    private AgendaService agendaService;
    
    @Inject
    private ProfissionalService profissionalService;    
    
	private Date dataSelecionada = TimeFactory.createDataHora();
	
	private List<Agenda> agendamentos = new ArrayList<Agenda>();
	
	private Agenda agenda;
	
	private Empresa empresaSelecionada;
	
	private String situacaoMarcacaoAgenda = SituacaoMarcacaoAgenda.AGUARDANDO.getValue();
	
	private int totalAgendamentos;
	
	private List<Profissional> profissionaisDisponiveis = new ArrayList<Profissional>();
	
	@PostConstruct
	public void init() {
		beginConversation();
	}    
	
	public void onChangeDataSelecionada() {
		recarregarAgenda();
	}
	
	public void onChangeSituacaoMarcacaoAgenda() {
		recarregarAgenda();
	}
	
	public void recarregarAgenda() {
		agendamentos.clear();
		profissionaisDisponiveis.clear();
	}
	
	public void incluirAgendamento() {
		agenda = new Agenda();
		agenda.setDataAgenda(dataSelecionada);
		agenda.setHoraChegada(TimeFactory.createDataHora());
		agenda.setSituacaoMarcacaoAgendaEnum(SituacaoMarcacaoAgenda.AGUARDANDO);
	}
	
	public void editarAgendamento(Agenda agenda) {
		this.agenda = new Agenda();
		this.agenda = agenda;
		if (agenda.getFuncionario() != null) {
			empresaSelecionada = agenda.getFuncionario().getEmpresa();
		}
	}
	
	public void gravarAgendamento() {
		agendaService.salvar(agenda);
		agendamentos.clear();
	}
	
	public void excluirAgendamento(Agenda agenda) {
		agendamentos.remove(agenda);
		agendaService.remover(agenda, agenda.getId());
	}
	
	public List<Agenda> getAgendamentos() {
		if (agendamentos.isEmpty()) {
			if (situacaoMarcacaoAgenda.isEmpty()) {
				agendamentos = agendaService.recuperarPorDataAgenda(dataSelecionada);
			} else {
				agendamentos = agendaService.recuperarPorDataAgendaEsituacao(dataSelecionada, situacaoMarcacaoAgenda);
			}
		}
		return agendamentos;
	}
	
	public List<Profissional> getProfissionaisDisponiveis() {
		if (profissionaisDisponiveis.isEmpty()) {
			profissionaisDisponiveis = profissionalService.recuperarPorDiaAtendimento(dataSelecionada);
		}
		return profissionaisDisponiveis;
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

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
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

	public void contarAgendamentos() {
		this.totalAgendamentos = agendamentos.size();
	}

	public String montarProfissionaisDisponiveis() {
		
		String table = "<table>";
		for (Profissional p : getProfissionaisDisponiveis()) {
			String nome = p.getNomeAbreviado();
			for (ProgramacaoHorarioAtendimento pa : p.getHorarioAtendimento().getProgramacoes()) {
				table += "<tr>";
				table += "<td>" + nome + "</td>" ;	
				table += "<td>" + getFormattedTime(pa.getHoraInicio(),"HH:mm") + "</td>";
				table += "<td>às</td>";
				table += "<td>" + getFormattedTime(pa.getHoraFim(),"HH:mm") + "</td>";
				table += "</tr>";
				nome = "";
			}
			table += "</tr>";
		}
		table += "</table>";
		return table;
	}
}