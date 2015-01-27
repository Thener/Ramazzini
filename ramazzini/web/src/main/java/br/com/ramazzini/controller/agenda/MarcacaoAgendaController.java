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
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.entidade.AgendaService;
import br.com.ramazzini.util.TimeFactory;

@Named
@ConversationScoped
public class MarcacaoAgendaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataSelecionada = TimeFactory.createDataHora();
	
	private List<Profissional> profissionaisDisponiveis;
	
    @Inject
    private AgendaService agendaService; 
    
    private Profissional profissionalSelecionado;
    
    private List<Agenda> horarios = new ArrayList<Agenda>();
    
    private Agenda agenda;
    
    private Empresa empresaSelecionada;
    
    public void editarMarcacao() {
    	
    }
	
	public void onChangeDataSelecionada() {
		profissionaisDisponiveis = agendaService.recuperarProfissionaisDisponiveisPorData(dataSelecionada);
		if (!profissionaisDisponiveis.isEmpty()) {
			profissionalSelecionado = profissionaisDisponiveis.get(0);
			onChangeProfissionalSelecionado();
		}
	}
	
	public void onChangeProfissionalSelecionado() {
		horarios = agendaService.recuperarPorDataProfissional(dataSelecionada, profissionalSelecionado);
	}
	
	public Date getDataSelecionada() {
		return dataSelecionada;
	}

	public void setDataSelecionada(Date dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}

	public String getCabecalhoCentro() {
		
		String cabecalho = "Data: " + TimeFactory.converterDataEmTexto(dataSelecionada);
		
		if (profissionalSelecionado != null) {
			cabecalho = cabecalho + " | Profissional: " + profissionalSelecionado.getNome();
		}
		
		return cabecalho;
	}
	
	public List<Profissional> getProfissionaisDisponiveis() {
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