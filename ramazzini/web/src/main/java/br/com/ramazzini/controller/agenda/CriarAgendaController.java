package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.horarioAtendimento.DiaSemana;
import br.com.ramazzini.model.parametro.Parametro;
import br.com.ramazzini.model.parametro.ParametroSistema;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.entidade.ParametroService;
import br.com.ramazzini.util.TimeFactory;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class CriarAgendaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Inject
    private ParametroService parametroService;
    
	private Profissional profissionalSelecionado;
	
	private Date dataInicialSelecionada;
	
	private Date dataFinalSelecionada;
	
	public void criarAgenda() {
		
		if (dataInicialSelecionada.after(dataFinalSelecionada)) {
			UtilMensagens.mensagemErroPorChave("mensagem.info.dataInicialSuperiorDataFinal");
			return;
		}
		
		if (TimeFactory.diasEntreDuasDatas(dataInicialSelecionada, dataFinalSelecionada) > 30) {
			UtilMensagens.mensagemErroPorChave("mensagem.info.periodoMaximoDe","30 dias.");
			return;			
		}
		
		Parametro criarSabado = parametroService.recuperarPorParametroSistema(ParametroSistema.AGENDA_CRIAR_SABADO);
		Parametro criarDomingo = parametroService.recuperarPorParametroSistema(ParametroSistema.AGENDA_CRIAR_DOMINGO);
		
		Date dtInicial = dataInicialSelecionada;
		
		while (dtInicial.compareTo(dataFinalSelecionada) <= 0) {
			
			if (TimeFactory.diaDaSemana(dtInicial).equals(DiaSemana.SABADO)
				&& criarSabado.getValor().equals("0")) {
				continue;
			} 
			
			if (TimeFactory.diaDaSemana(dtInicial).equals(DiaSemana.DOMINGO)
				&& criarDomingo.getValor().equals("0")) {
				continue;
			}
			
			dtInicial = TimeFactory.somarDias(dtInicial, 1);
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