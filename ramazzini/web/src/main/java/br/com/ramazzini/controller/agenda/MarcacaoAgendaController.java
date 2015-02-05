package br.com.ramazzini.controller.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.agenda.Agenda;
import br.com.ramazzini.model.agenda.SituacaoMarcacaoAgenda;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.funcionario.SituacaoFuncionario;
import br.com.ramazzini.model.notificacao.Notificacao;
import br.com.ramazzini.model.parametro.ParametroSistema;
import br.com.ramazzini.model.procedimento.TipoExameClinico;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.model.programacaoHorarioAtendimento.ProgramacaoHorarioAtendimento;
import br.com.ramazzini.service.entidade.AgendaService;
import br.com.ramazzini.service.entidade.FuncionarioService;
import br.com.ramazzini.service.entidade.ParametroService;
import br.com.ramazzini.service.entidade.ProcedimentoService;
import br.com.ramazzini.service.entidade.ProfissionalService;
import br.com.ramazzini.service.entidade.ProgramacaoHorarioAtendimentoService;
import br.com.ramazzini.util.TimeFactory;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class MarcacaoAgendaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final static String CHANNEL = "/agenda";
	
    @Inject private AgendaService agendaService;
    @Inject private FuncionarioService funcionarioService;
    @Inject private ParametroService parametroService;
    @Inject private ProcedimentoService procedimentoService;
    @Inject private ProfissionalService profissionalService;
    @Inject private ProgramacaoHorarioAtendimentoService programacaoHorarioAtendimentoService;
    
	private Date dataSelecionada = TimeFactory.createDataHora();
	
	private List<Agenda> agendamentos = new ArrayList<Agenda>();
	
	private Agenda agenda;
	
	private Empresa empresaSelecionada = new Empresa();
	
	private String situacaoMarcacaoAgenda = SituacaoMarcacaoAgenda.AGUARDANDO.getValue();
	
	private int totalAgendamentos;
	
	private Profissional profissionalSelecionado;
	
	private List<Profissional> profissionaisDisponiveis = new ArrayList<Profissional>();
	
	private boolean exibirColunaProfissional = Boolean.FALSE;
	
	private boolean exibirColunaObservacoes = Boolean.FALSE;
	
	private boolean exibirColunaFuncao = Boolean.FALSE;
	
	private Date ultimaAtualizacaoAgenda;
	
	private String tempoAtualizacaoAutomatica;
	
	private Funcionario novoFuncionario;
	
	@PostConstruct
	public void init() {
		beginConversation();
	}    
	
    public void sendNotify() {
    	EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage("TESTE 1", "TESTE 2"));
        /*
         * não entendi ainda como funciona o eventBus...
         * tentei retirar o new Faces, mas pára de funcionar. 
         * Até mesmo remover um dos parâmetros (teste 1 ou teste 2) pára de funcionar.
         */
    }
    
	public void onChangeDataSelecionada() {
		recarregarAgenda();
	}
	
	public void onChangeSituacaoMarcacaoAgenda() {
		recarregarAgenda();
	}

	public void onChangeProfissionalSelecionado() {
		agendamentos.clear();
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
	
	public void incluirAdmissional() {
		novoFuncionario = new Funcionario();
	}
	
	public void editarAgendamento(Agenda agenda) {
		this.agenda = new Agenda();
		this.agenda = agendaService.recuperarPorId(agenda.getId());
		if (agenda.getFuncionario() != null) {
			empresaSelecionada = agenda.getFuncionario().getEmpresa();
		}
	}
	
	public void gravarAgendamento() {
		gravarAgenda(agenda);
	}
	
	public void gravarAgendamentoAdmissional() {
		novoFuncionario.setEmpresa(empresaSelecionada);
		novoFuncionario.setSituacaoFuncionarioEnum(SituacaoFuncionario.AGENDADO);
		// quando o médico gravar a primeira ac, trocar para Ativo.
		funcionarioService.salvar(novoFuncionario);
		incluirAgendamento();
		agenda.setFuncionario(novoFuncionario);
		agenda.setProcedimento(procedimentoService.recuperarPorTipoExameClinico(TipoExameClinico.ADMISSIONAL));
		gravarAgenda(agenda);
	}
	
	private void gravarAgenda(Agenda agenda) {
		agendaService.salvar(agenda);
		agendamentos.clear();
		Notificacao.notificarModificacaoAgenda();		
	}
	
	public void excluirAgendamento(Agenda agenda) {
		agendamentos.remove(agenda);
		agendaService.remover(agenda, agenda.getId());
		Notificacao.notificarModificacaoAgenda();
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "label.agendamento");
	}

	public void atualizacaoAutomatica() {
		agendamentos.clear();
	}
	
	public void atualizacaoManual() {
		agendamentos.clear();
	}
	
	public String montarProfissionaisDisponiveis() {
		
		String table = "<table>";
		
		for (Profissional p : getProfissionaisDisponiveis(null)) {
			
			String nome = p.getNomeAbreviado();
			// evitando lazy
			List<ProgramacaoHorarioAtendimento> programacoes = 
					programacaoHorarioAtendimentoService.recuperarPorHorarioAtendimento(p.getHorarioAtendimento(), TimeFactory.diaDaSemana(dataSelecionada));
			
			for (ProgramacaoHorarioAtendimento pa : programacoes) {
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
	
	public List<Agenda> getAgendamentos() {
		if (agendamentos.isEmpty() || ultimaAtualizacaoAgenda.before(Notificacao.getUltimaModificacaoAgenda())) {
			agendamentos = agendaService.recuperarPorFiltros(dataSelecionada, situacaoMarcacaoAgenda, profissionalSelecionado);
			ultimaAtualizacaoAgenda = TimeFactory.createDataHora();
			sendNotify();
		}
		return agendamentos;
	}
	
	public List<Profissional> getProfissionaisDisponiveis(Profissional profissional) {
		if (profissionaisDisponiveis.isEmpty()) {
			profissionaisDisponiveis = profissionalService.recuperarPorDiaAtendimento(dataSelecionada);
		}
		if (profissional != null && !profissionaisDisponiveis.contains(profissional)) {
			profissionaisDisponiveis.add(profissional);
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

	public Profissional getProfissionalSelecionado() {
		return profissionalSelecionado;
	}

	public void setProfissionalSelecionado(Profissional profissionalSelecionado) {
		this.profissionalSelecionado = profissionalSelecionado;
	}
	
	public boolean isExibirColunaProfissional() {
		return exibirColunaProfissional;
	}

	public void setExibirColunaProfissional(boolean exibirColunaProfissional) {
		this.exibirColunaProfissional = exibirColunaProfissional;
	}
	
	public boolean isExibirColunaObservacoes() {
		return exibirColunaObservacoes;
	}

	public void setExibirColunaObservacoes(boolean exibirColunaObservacoes) {
		this.exibirColunaObservacoes = exibirColunaObservacoes;
	}

	public boolean isExibirColunaFuncao() {
		return exibirColunaFuncao;
	}

	public void setExibirColunaFuncao(boolean exibirColunaFuncao) {
		this.exibirColunaFuncao = exibirColunaFuncao;
	}

	public String getTempoAtualizacaoAutomatica() {
		if (tempoAtualizacaoAutomatica == null || tempoAtualizacaoAutomatica.isEmpty() ) {
			tempoAtualizacaoAutomatica = parametroService.recuperarPorParametroSistema(
					ParametroSistema.AGENDA_TEMPO_ATUALIZACAO_AUTOMATICA).getValor(); 
		}
		return tempoAtualizacaoAutomatica;
	}

	public Funcionario getNovoFuncionario() {
		return novoFuncionario;
	}

	public void setNovoFuncionario(Funcionario novoFuncionario) {
		this.novoFuncionario = novoFuncionario;
	}

}