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
import br.com.ramazzini.model.horarioAtendimento.DiaSemana;
import br.com.ramazzini.model.parametro.Parametro;
import br.com.ramazzini.model.parametro.ParametroSistema;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.model.programacaoHorarioAtendimento.ProgramacaoHorarioAtendimento;
import br.com.ramazzini.service.entidade.AgendaService;
import br.com.ramazzini.service.entidade.ParametroService;
import br.com.ramazzini.service.entidade.ProgramacaoHorarioAtendimentoService;
import br.com.ramazzini.util.TimeFactory;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class CriarAgendaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Inject
    private ParametroService parametroService;
    
    @Inject
    private ProgramacaoHorarioAtendimentoService programacaoHorarioAtendimentoService;
    
    @Inject
    private AgendaService agendaService;    
    
	private Profissional profissionalSelecionado;
	
	private Date dataInicialSelecionada;
	
	private Date dataFinalSelecionada;
	
	private List<ProgramacaoHorarioAtendimento> programacoes;
	
	public void criarAgenda() {
		
		if (dataInicialSelecionada.after(dataFinalSelecionada)) {
			UtilMensagens.mensagemErroPorChave("mensagem.info.dataInicialSuperiorDataFinal");
			return;
		}
		
		if (TimeFactory.diasEntreDuasDatas(dataInicialSelecionada, dataFinalSelecionada) > 30) {
			UtilMensagens.mensagemErroPorChave("mensagem.info.periodoMaximoDe","30 dias.");
			return;			
		}
		
		if (profissionalSelecionado.getHorarioAtendimento() == null) {
			UtilMensagens.mensagemErroPorChave("mensagem.erro.horarioNaoInformadoCadastroProfissional");
			return;
		}
		
		programacoes = programacaoHorarioAtendimentoService.recuperarPorHorarioAtendimento(profissionalSelecionado.getHorarioAtendimento());
		
		if (programacoes.isEmpty()) {
			UtilMensagens.mensagemErro("mensagem.erro.programacaoNaoExistente");
			return;
		}
		
		Parametro criarSabado = parametroService.recuperarPorParametroSistema(ParametroSistema.AGENDA_CRIAR_SABADO);
		Parametro criarDomingo = parametroService.recuperarPorParametroSistema(ParametroSistema.AGENDA_CRIAR_DOMINGO);
		
		List<Agenda> agendas = new ArrayList<Agenda>();
		
		Date dtIni = dataInicialSelecionada;
		
		while (dtIni.compareTo(dataFinalSelecionada) <= 0) {
			
			Date dtInicial = dtIni;
			dtIni = TimeFactory.somarDias(dtIni, 1);
			
			DiaSemana diaSemana = TimeFactory.diaDaSemana(dtInicial);
			
			if (diaSemana.equals(DiaSemana.SABADO)
				&& criarSabado.getValor().equals("0")) {
				continue;
			} 
			
			if (diaSemana.equals(DiaSemana.DOMINGO)
				&& criarDomingo.getValor().equals("0")) {
				continue;
			}
			
			for (ProgramacaoHorarioAtendimento prog : programacoes) {
				if (prog.getDiaSemanaEnum().equals(diaSemana)) {
					Date horaInicial = prog.getHoraInicio();
					Date horaFinal = prog.getHoraFim();
					while (horaInicial.compareTo(horaFinal) <= 0) {
						
						Agenda agenda = new Agenda();
						
						agenda.setDataAgenda(dtInicial);
						agenda.setProfissional(profissionalSelecionado);
						agenda.setHoraAgenda(horaInicial);
						agenda.setSituacaoMarcacaoAgendaEnum(SituacaoMarcacaoAgenda.LIVRE);
						
						agendas.add(agenda);
						
						horaInicial = TimeFactory.somarMinutos(horaInicial, prog.getIntervalo());
					}
				}
			}
			
		}
		
		if (agendas.isEmpty()) {
			UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelCriarAgenda");
		} else {
			Integer total = agendaService.salvarLista(agendas).size();
			if (total > 0) {
				UtilMensagens.mensagemInformacaoPorChave("mensagem.info.totalDiasCriadosNaAgenda", total.toString());				
			} else {
				UtilMensagens.mensagemErroPorChave("mensagem.erro.houveErroInternoSistemaGeracaoAgenda");
			}
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