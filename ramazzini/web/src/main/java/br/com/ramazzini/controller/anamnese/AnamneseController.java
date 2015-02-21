package br.com.ramazzini.controller.anamnese;

import java.io.Serializable;
import java.util.ArrayList;
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
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.funcionario.SituacaoFuncionario;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.entidade.AgendaService;
import br.com.ramazzini.service.entidade.AnamneseService;
import br.com.ramazzini.service.entidade.AvaliacaoClinicaService;
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
	@Inject private AgendaService agendaService;
	@Inject private FuncionarioService funcionarioService;
	
	private Anamnese anamnese;
	
	private AvaliacaoClinica avaliacaoClinica;
	
	private Agenda agenda;
	
	private List<String> alertas = new ArrayList<String>();
	
	public String iniciarAtendimento(Funcionario funcionario, Profissional medico, Procedimento procedimento, Agenda agenda) {
		
		beginConversation();
		
		this.agenda = agenda;
		
		alertas.clear();
		
		//---- definindo Avaliação Clínica:
		
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
						avaliacaoClinica.getProcedimento().getNome(), procedimento.getNome()));
			}

		}
		
		avaliacaoClinica.setMedico(medico);
		avaliacaoClinica.setProcedimento(procedimento);
		avaliacaoClinica.setDataRealizacao(TimeFactory.createDataHora());
		avaliacaoClinica.setFuncaoAtual(funcionario.getFuncao());
		
		//---- definindo Anamnese:
		
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
		
		setUriRequisicao(getControleAcesso().getUriRequisicao());
		return PAGINA_ANAMNESE;
	}	
	
	public String continuarAtendimento(Funcionario funcionario, Profissional medico, Procedimento procedimento, Agenda agenda) {
		return "";
	}
	
	private AvaliacaoClinica buscarAvaliacaoClinicaEmAndamento(Funcionario funcionario) {
		return avaliacaoClinicaService.recuperarAvaliacaoClinicaEmAndamentoPor(funcionario);
	}

	public void gravarAnamnese() {
		
		if (agenda != null) {
			agenda.setSituacaoMarcacaoAgendaEnum(SituacaoMarcacaoAgenda.ATENDIDO);
			agendaService.salvar(agenda);
		}
		
		avaliacaoClinica.setSituacaoAvaliacaoClinica(anamnese.getSituacaoAvaliacaoClinica());
		avaliacaoClinicaService.salvar(avaliacaoClinica);
		
		if (avaliacaoClinica.getFuncionario().getSituacaoFuncionarioEnum().equals(SituacaoFuncionario.AGENDADO)) {
			Funcionario funcionario = avaliacaoClinica.getFuncionario();
			funcionario.setSituacaoFuncionarioEnum(SituacaoFuncionario.ATIVO);
			funcionarioService.salvar(funcionario);
		}
		
		anamneseService.salvar(anamnese);
		
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeGravadaComSucesso","label.anamnese");
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
	
	

}