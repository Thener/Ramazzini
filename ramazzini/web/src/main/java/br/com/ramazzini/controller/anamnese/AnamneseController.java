package br.com.ramazzini.controller.anamnese;

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
import br.com.ramazzini.model.anamnese.Anamnese;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.avaliacaoClinica.SituacaoAvaliacaoClinica;
import br.com.ramazzini.model.avaliacaoClinicaProcedimento.AvaliacaoClinicaProcedimento;
import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimento;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.funcionario.SituacaoFuncionario;
import br.com.ramazzini.model.notificacao.Notificacao;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoExameClinico;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.entidade.AgendaService;
import br.com.ramazzini.service.entidade.AnamneseService;
import br.com.ramazzini.service.entidade.AvaliacaoClinicaProcedimentoService;
import br.com.ramazzini.service.entidade.AvaliacaoClinicaService;
import br.com.ramazzini.service.entidade.FuncaoProcedimentoService;
import br.com.ramazzini.service.entidade.FuncionarioService;
import br.com.ramazzini.util.TimeFactory;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class AnamneseController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_ANAMNESE = "/pages/anamnese/cadastroAnamnese.jsf?faces-redirect=true";
	
	@Inject private AnamneseService anamneseService;
	@Inject private AvaliacaoClinicaService avaliacaoClinicaService;
	@Inject private AvaliacaoClinicaProcedimentoService avaliacaoClinicaProcedimentoService;
	@Inject private AgendaService agendaService;
	@Inject private FuncaoProcedimentoService funcaoProcedimentoService;
	@Inject private FuncionarioService funcionarioService;
	
	private Anamnese anamnese;
	
	private AvaliacaoClinica avaliacaoClinica;
	
	private List<AvaliacaoClinicaProcedimento> procedimentosAvaliacao = new ArrayList<AvaliacaoClinicaProcedimento>();
	
	private Agenda agenda;
	
	private List<String> alertas = new ArrayList<String>();
	
	private List<String> avisos = new ArrayList<String>();
	
	private Procedimento procedimento;
	
	public String iniciarAtendimento(Funcionario funcionario, Profissional medico, Procedimento procedimento, Agenda agenda) {
		
		beginConversation();
		
		this.agenda = agenda;
		
		if (agenda != null) {
			agenda.setSituacaoMarcacaoAgendaEnum(SituacaoMarcacaoAgenda.EM_ATENDIMENTO);
			agenda.setProfissional(medico);
			gravarAgenda(agenda);			
		}

		alertas.clear();
		avisos.clear();
		
		//---- definindo Avaliação Clínica e Anamnese:
		
		avaliacaoClinica = buscarAvaliacaoClinicaEmAndamento(funcionario);
		
		if (avaliacaoClinica == null) {

			avaliacaoClinica = new AvaliacaoClinica();
			avaliacaoClinica.setFuncionario(funcionario);
			avaliacaoClinica.setSituacaoAvaliacaoClinicaEnum(SituacaoAvaliacaoClinica.EM_ANDAMENTO);
		
		} else {

			alertas.add(getValorChaveMsg("mensagem.info.continuandoAvaliacaoClinicaNaoConcluida", 
					TimeFactory.converterDateTimeEmTexto(avaliacaoClinica.getDataInclusao())));
			
			if (!medico.equals(avaliacaoClinica.getMedico())) {
				alertas.add(getValorChaveMsg("mensagem.info.medicoEstaSendoSobrescrito", 
						avaliacaoClinica.getMedico().getNome()));
			}
			
			if (!procedimento.equals(avaliacaoClinica.getProcedimento())) {
				alertas.add(getValorChaveMsg("mensagem.info.procedimentoEstaSendoTrocado", 
						anamnese.getAvaliacaoClinica().getProcedimento().getNome(), procedimento.getNome()));
			}

		}
		
		carregarProcedimentosAvaliacao(avaliacaoClinica);
		
		anamnese = anamneseService.recuperarAnamneseEmAndamentoPor(avaliacaoClinica, medico);
		
		if (anamnese == null) {

			anamnese = new Anamnese();
			anamnese.setMedico(medico);
			anamnese.setAvaliacaoClinica(avaliacaoClinica);
			anamnese.setDataRealizacao(TimeFactory.createDataHora());
			anamnese.setSituacaoAvaliacaoClinicaEnum(SituacaoAvaliacaoClinica.EM_ANDAMENTO);
			
		} else {

			alertas.add(getValorChaveMsg("mensagem.info.continuandoAnamneseNaoConcluida", 
					TimeFactory.converterDateTimeEmTexto(anamnese.getDataInclusao())));
		}
		
		//--- Verificando alerta deixado no último atendimento:
		
		Anamnese anamneseAnterior = anamneseService.recuperarAnamneseAnterior(funcionario, anamnese.getDataRealizacao());
		
		if (anamneseAnterior != null && anamneseAnterior.isAlertaProximoAtendimento()) {
			alertas.add(getValorChaveMsg("mensagem.info.alertaProximoAtendimento",
					anamneseAnterior.getMedico().getNome(),
					TimeFactory.converterDataEmTexto(anamneseAnterior.getDataInclusao())));
		}		
		
		//--- Atualizando avaliação clínica:
		
		avaliacaoClinica.setDataRealizacao(TimeFactory.createDataHora());
		avaliacaoClinica.setFuncaoAtual(funcionario.getFuncao());
		avaliacaoClinica.setMedico(medico);
		avaliacaoClinica.setProcedimento(procedimento);
		
		setUriRequisicao(getControleAcesso().getUriRequisicao());
		return PAGINA_ANAMNESE;
	}	
	
	public String cancelarAtendimento() {
		
		if (agenda != null) {
			agenda.setSituacaoMarcacaoAgendaEnum(SituacaoMarcacaoAgenda.AGUARDANDO);
			agenda.setProfissional(null);
			gravarAgenda(agenda);			
		}		
		
		return voltar();
	}
	
	private AvaliacaoClinica buscarAvaliacaoClinicaEmAndamento(Funcionario funcionario) {
		return avaliacaoClinicaService.recuperarAvaliacaoClinicaEmAndamentoPor(funcionario);
	}

	public void gravarAnamnese() {
		
		avisos.clear();
		
		if (anamnese.getSituacaoAvaliacaoClinicaEnum().equals(SituacaoAvaliacaoClinica.EM_ANDAMENTO)) {
			UtilMensagens.mensagemErroPorChave("mensagem.erro.situacaoAvaliacaoClinicaNaoPodeSerEmAndamento");
			return;
		}
		
		if (agenda != null) {
			agenda.setSituacaoMarcacaoAgendaEnum(SituacaoMarcacaoAgenda.ATENDIDO);
			agenda.setProcedimento(avaliacaoClinica.getProcedimento());
			gravarAgenda(agenda);
		}
		
		calcularDataRetornoProcedimentos(); // precisa estar antes do calculo da data de retorno da ac
		
		avaliacaoClinica.setProcedimentos(procedimentosAvaliacao);
		
		avaliacaoClinica.setDataRetorno(avaliacaoClinicaService.calcularDataRetornoAvaliacaoClinica(avaliacaoClinica, procedimentosAvaliacao));
		avaliacaoClinica.setSituacaoAvaliacaoClinica(anamnese.getSituacaoAvaliacaoClinica());
		avaliacaoClinicaService.salvar(avaliacaoClinica);
		
		atualizarFuncionario();
		
		anamneseService.salvar(anamnese);
		
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeGravadaComSucesso","label.anamnese");
	}
	
	private void gravarAgenda(Agenda agenda) {
		
		agendaService.salvar(agenda);
		Notificacao.notificarModificacaoAgenda();
	}
	
	private void atualizarFuncionario() {
		
		boolean salvarFuncionario = Boolean.FALSE;
		
		Funcionario funcionario = avaliacaoClinica.getFuncionario();
		TipoExameClinico tipoExame = avaliacaoClinica.getProcedimento().getTipoExameClinicoEnum();
		
		if (funcionario.getSituacaoFuncionarioEnum().equals(SituacaoFuncionario.AGENDADO)) {
			funcionario.setSituacaoFuncionarioEnum(SituacaoFuncionario.ATIVO);
			salvarFuncionario = Boolean.TRUE;
			
			avisos.add(getValorChaveMsg("mensagem.info.situacaoFuncionarioAlteradaPara", 
					SituacaoFuncionario.ATIVO.getStringChave()));			
		}
		
		if (!funcionario.getFuncao().equals(avaliacaoClinica.getFuncaoAtual())) {
			funcionario.setFuncao(avaliacaoClinica.getFuncaoAtual());
			salvarFuncionario = Boolean.TRUE;
			
			avisos.add(getValorChaveMsg("mensagem.info.funcaoFuncionarioAlterada"));
		}
		
		if (tipoExame.equals(TipoExameClinico.DEMISSIONAL) 
				&& (anamnese.getSituacaoAvaliacaoClinicaEnum().equals(SituacaoAvaliacaoClinica.APTO) 
						|| anamnese.getSituacaoAvaliacaoClinicaEnum().equals(SituacaoAvaliacaoClinica.APTO_COM_RESTRICAO)
						|| anamnese.getSituacaoAvaliacaoClinicaEnum().equals(SituacaoAvaliacaoClinica.APTO_INCLUINDO_TRABALHO_ALTURA))) {
			
			funcionario.setSituacaoFuncionarioEnum(SituacaoFuncionario.DEMITIDO);
			salvarFuncionario = Boolean.TRUE;
			avisos.add(getValorChaveMsg("mensagem.info.situacaoFuncionarioAlteradoPara",
					SituacaoFuncionario.DEMITIDO.getStringChave()));
			
		} else if (funcionario.getSituacaoFuncionarioEnum().equals(SituacaoFuncionario.DEMITIDO)) {
			
			funcionario.setSituacaoFuncionarioEnum(SituacaoFuncionario.ATIVO);
			salvarFuncionario = Boolean.TRUE;
			avisos.add(getValorChaveMsg("mensagem.info.situacaoFuncionarioAlteradaPara",
					SituacaoFuncionario.ATIVO.getStringChave()));			
		}
		
		if (salvarFuncionario) {
			funcionarioService.salvar(funcionario);
		}		
	}
	
	private void carregarProcedimentosAvaliacao(AvaliacaoClinica avaliacaoClinica) {
		
		procedimentosAvaliacao.clear();
		
		if (!avaliacaoClinica.isNovo()) {
			
			procedimentosAvaliacao = avaliacaoClinicaProcedimentoService.recuperarPorAvaliacaoClinica(avaliacaoClinica);
			
		} else {
			
			List<FuncaoProcedimento> funcoesProcedimentos = 
					funcaoProcedimentoService.recuperarPorFuncao(avaliacaoClinica.getFuncionario().getFuncao());
			
			for (FuncaoProcedimento fp : funcoesProcedimentos) {
				
				AvaliacaoClinicaProcedimento acp = new AvaliacaoClinicaProcedimento();
				
				acp.setAvaliacaoClinica(avaliacaoClinica);
				acp.setProcedimento(fp.getProcedimento());
				
				procedimentosAvaliacao.add(acp);
			}
		
		}
	}
	
	private void calcularDataRetornoProcedimentos() {
		
		if (procedimentosAvaliacao.size() > 0) {
			
			for (AvaliacaoClinicaProcedimento acp : procedimentosAvaliacao) {
				Date dtRetorno = avaliacaoClinicaProcedimentoService.calcularDataRetornoProcedimento(avaliacaoClinica, acp);
				acp.setDataRetorno(dtRetorno);
			}
		}
	}
	
	public void incluirProcedimento() {
		
		if (procedimento != null) {
			
			AvaliacaoClinicaProcedimento acp = new AvaliacaoClinicaProcedimento();
			
			acp.setAvaliacaoClinica(avaliacaoClinica);
			acp.setProcedimento(procedimento);	
			
			procedimentosAvaliacao.add(acp);
			
			procedimento = null;
		}
	}
	
	public void removerProcedimento(AvaliacaoClinicaProcedimento procedimento) {
		procedimentosAvaliacao.remove(procedimento);	
	}
	
    public String voltar() {	
    	endConversation();
		return getUriRequisicao()+"?faces-redirect=true";
	}

	public Anamnese getAnamnese() {
		return anamnese;
	}

	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}

	public AvaliacaoClinica getAvaliacaoClinica() {
		return avaliacaoClinica;
	}

	public void setAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica) {
		this.avaliacaoClinica = avaliacaoClinica;
	}

	public List<String> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<String> alertas) {
		this.alertas = alertas;
	}
	
	public List<String> getAvisos() {
		return avisos;
	}

	public void setAvisos(List<String> avisos) {
		this.avisos = avisos;
	}

	public List<AvaliacaoClinicaProcedimento> getProcedimentosAvaliacao() {
		return procedimentosAvaliacao;
	}

	public void setProcedimentosAvaliacao(
			List<AvaliacaoClinicaProcedimento> procedimentosAvaliacao) {
		this.procedimentosAvaliacao = procedimentosAvaliacao;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}
	
}