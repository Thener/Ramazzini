package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.agenda.Agenda;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.util.TimeFactory;

@Named
@ConversationScoped
public class MarcacaoAgendaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date dataSelecionada = TimeFactory.createDataHora();
	
	private List<Agenda> agendamentos = new ArrayList<Agenda>();
	
	private Agenda agenda;
	
	private Empresa empresaSelecionada;
	    
	@PostConstruct
	public void init() {
		beginConversation();
	}    
	
	public void incluirAgendamento() {
		agenda = new Agenda();
		agenda.setDataAgenda(dataSelecionada);
	}
	
	public void gravarAgendamento() {
		
	}
	
	public List<Agenda> getAgendamentos() {
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