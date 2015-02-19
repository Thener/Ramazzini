package br.com.ramazzini.controller.entidade;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.controller.util.ExportarPdfController;
import br.com.ramazzini.model.deficiencia.Deficiencia;
import br.com.ramazzini.model.enquadramentoDeficiencia.EnquadramentoDeficiencia;
import br.com.ramazzini.model.enquadramentoDeficiencia.EnquadramentoDeficienciaEnum;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.limitacoesDeficienciaMental.LimitacoesDeficienciaMental;
import br.com.ramazzini.model.origemDeficiencia.OrigemDeficiencia;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.entidade.EnquadramentoDeficienciaService;
import br.com.ramazzini.service.entidade.FuncionarioService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class DeficienciaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_DEFICIENCIA = "/pages/funcionario/cadastroDeficiencia.jsf?faces-redirect=true";
	String PATH_DIRECTORY_FUNCIONARIO = "FUNCIONARIO";
    String JASPER_AVALIACAO_MEDICA_DEFICIENTE = "AvaliacaoMedicaDeficiente.jasper";
    
    @Inject private FuncionarioService funcionarioService;
    @Inject private EnquadramentoDeficienciaService enquadramentoDeficienciaService;
    
    private Funcionario funcionario;
    
    private Integer tabAtiva;
    
    public String cadatroDeficiencia() {
    	funcionarioService.initTransaction();
    	if (funcionario.getDeficiencia()==null){
    		funcionario.setDeficiencia(new Deficiencia());
    	}
    	setUriRequisicao(getControleAcesso().getUriRequisicao());
    	return PAGINA_CADASTRO_DEFICIENCIA;    	
    }
    
	public String gravarDeficiencia() {
		funcionarioService.salvar(funcionario);
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeGravadaComSucesso");
		return "";
	}
    public boolean doencaMentalSelecionada(){
    	if (funcionario.getDeficiencia().getEnquadramentoDeficiencia() == null){
    		return false;
    	} else {
    		return funcionario.getDeficiencia().getEnquadramentoDeficiencia().contains(enquadramentoDeficienciaService.recuperarPorTipoEnquadramento(EnquadramentoDeficienciaEnum.DEFICIENCIA_MENTAL));
    	}
    }
    
    public void export() throws Exception {
    	File relatorio = getFileRelatorio(PATH_DIRECTORY_FUNCIONARIO, JASPER_AVALIACAO_MEDICA_DEFICIENTE);
    	ExportarPdfController export = new ExportarPdfController(carregaParametros(), "Deficiente_Avaliação_Médica", relatorio);
		export.downloadSemDataSource();
    }
    
    private HashMap<String, Object> carregaParametros() {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PARAM_CLIENTE", super.cliente);
		parameters.put("PARAM_FUNCIONARIO", funcionario);
		parameters.put("IMAGE_PATH", getCaminhoLogo());
		parameters.put("ORIGENS_DEFICIENCIA", getOrigensDeficiencia());
		parameters.put("ENQUADRAMENTOS_DEFICIENCIA", getEnquadramentosDeficiencia());
		parameters.put("LIMITACOES_DEFICIENCIA_MENTAL", getLimitacoesDeficienciaMental());
		//parameters.put("MEDICO_TRABALHO", medicoSelecionado);
		return parameters;
	}

	private List<String> getOrigensDeficiencia() {
		List<String> origens =  new ArrayList<String>();
		for (OrigemDeficiencia origemDeficiencia: funcionario.getDeficiencia().getOrigensDeficiencia()){
			origens.add(origemDeficiencia.getOrigemDeficiencia());
		}
		return origens;
	}
	
	private List<String> getEnquadramentosDeficiencia() {
		List<String> enquadramentos =  new ArrayList<String>();
		for (EnquadramentoDeficiencia enquadramentoDeficiencia: funcionario.getDeficiencia().getEnquadramentoDeficiencia()){
			enquadramentos.add(enquadramentoDeficiencia.getEnquadramentoDeficiencia());
		}
		return enquadramentos;
	}
	
	private List<String> getLimitacoesDeficienciaMental() {
		List<String> limitacoes =  new ArrayList<String>();
		for (LimitacoesDeficienciaMental limitacoesDeficienciaMental : funcionario.getDeficiencia().getLimitacoesDeficienciaMental()){
			limitacoes.add(limitacoesDeficienciaMental.getLimitacoesDeficienciaMental());
		}
		return limitacoes;
	}
    
    public String voltar() {				
		return getUriRequisicao()+"?faces-redirect=true";
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getTabActiveIndex() {
        return tabAtiva;
    }

    public void setTabActiveIndex(Integer tabActiveIndex) {
        this.tabAtiva = tabActiveIndex;
    }

	public String getIdadeFuncionario() {
		return funcionario.getIdadeTexto();
	}
}