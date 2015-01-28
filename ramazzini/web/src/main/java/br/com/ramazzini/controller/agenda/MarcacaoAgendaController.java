package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.agenda.Agenda;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.entidade.AgendaService;
import br.com.ramazzini.util.TimeFactory;

@Named
@ConversationScoped
public class MarcacaoAgendaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private @Inject Conversation conversation;

	private Date dataSelecionada = TimeFactory.createDataHora();
	
	private List<Profissional> profissionaisDisponiveis = new ArrayList<Profissional>();
	
    @Inject
    private AgendaService agendaService; 
    
    private Profissional profissionalSelecionado;
    
    private List<Agenda> horarios = new ArrayList<Agenda>();
    
    private Agenda agenda;
    
    private Empresa empresaSelecionada;
    
	@PostConstruct
	public void init() {

		if (conversation.isTransient()) {
			conversation.begin();
		}
	}    
    
    public void editarMarcacao(Agenda agenda) {
    	this.agenda = agenda;
   		empresaSelecionada = (agenda.getFuncionario() != null) ? agenda.getFuncionario().getEmpresa() : null;
    }
    
    public void gravarMarcacao() {
    	agendaService.salvar(agenda);
    }
	
	public void onChangeDataSelecionada() {
		profissionaisDisponiveis.clear();
		horarios.clear();
	}
	
	public void onChangeProfissionalSelecionado() {
		horarios.clear();
	}
	
	public Date getDataSelecionada() {
		return dataSelecionada;
	}

	public void setDataSelecionada(Date dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}

	public String getCabecalhoCentro() {
		
		String cabecalho = "Data: " + TimeFactory.converterDataEmTexto(dataSelecionada);
		cabecalho = cabecalho + " :: " + TimeFactory.diaDaSemana(dataSelecionada).getStringChave();
		
		if (profissionalSelecionado != null) {
			cabecalho = cabecalho + " | Profissional: " + profissionalSelecionado.getNome();
		}
		
		return cabecalho;
	}
	
	public List<Profissional> getProfissionaisDisponiveis() {
		if (profissionaisDisponiveis.isEmpty()) {
			profissionaisDisponiveis = agendaService.recuperarProfissionaisDisponiveisPorData(dataSelecionada);
		}
		if (profissionaisDisponiveis.size() > 0) {
			profissionalSelecionado = profissionaisDisponiveis.get(0);
		}		
		return profissionaisDisponiveis;
	}

	public void setProfissionaisDisponiveis(List<Profissional> profissionaisDisponiveis) {
		this.profissionaisDisponiveis = profissionaisDisponiveis;
	}

	public Profissional getProfissionalSelecionado() {
		return profissionalSelecionado;
	}

	public void setProfissionalSelecionado(Profissional profissionalSelecionado) {
		this.profissionalSelecionado = profissionalSelecionado;
	}

	public List<Agenda> getHorarios() {
		if (horarios.isEmpty()) {
			horarios = agendaService.recuperarPorDataProfissional(dataSelecionada, profissionalSelecionado);
		}
		return horarios;
	}

	public void setHorarios(List<Agenda> horarios) {
		this.horarios = horarios;
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
	
	
}