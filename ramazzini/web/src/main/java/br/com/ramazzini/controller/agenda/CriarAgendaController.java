package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.util.UtilDate;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class CriarAgendaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Profissional profissionalSelecionado;
	
	private Date dataInicialSelecionada;
	
	private Date dataFinalSelecionada;
	
	public void criarAgenda() {
		
		if (dataInicialSelecionada.after(dataFinalSelecionada)) {
			UtilMensagens.mensagemErroPorChave("mensagem.info.dataInicialSuperiorDataFinal");
			return;
		}
		
		if (UtilDate.diasEntreDuasDatas(dataInicialSelecionada, dataFinalSelecionada) > 30) {
			UtilMensagens.mensagemErroPorChave("mensagem.info.periodoMaximoDe","30 dias.");
			return;			
		}
		
		Date dtInicial = dataInicialSelecionada;
		
		while (dtInicial.compareTo(dataFinalSelecionada) <= 0) {
			
			
			
			dtInicial = UtilDate.somarDias(dtInicial, 1);
		}
		
	}

	public Profissional getProfissionalSelecionado() {
		return profissionalSelecionado;
	}

	public void setProfissionalSelecionado(Profissional profissionalSelecionado) {
		this.profissionalSelecionado = profissionalSelecionado;
	}

	public Date getDataInicialSelecionada() {
		return dataInicialSelecionada;
	}

	public void setDataInicialSelecionada(Date dataInicialSelecionada) {
		this.dataInicialSelecionada = dataInicialSelecionada;
	}

	public Date getDataFinalSelecionada() {
		return dataFinalSelecionada;
	}

	public void setDataFinalSelecionada(Date dataFinalSelecionada) {
		this.dataFinalSelecionada = dataFinalSelecionada;
	}
	
	
	
}