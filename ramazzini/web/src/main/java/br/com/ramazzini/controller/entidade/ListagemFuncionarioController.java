package br.com.ramazzini.controller.entidade;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.controller.util.ExportarPdfController;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.funcionario.SituacaoFuncionario;
import br.com.ramazzini.service.entidade.FuncionarioService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class ListagemFuncionarioController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Inject
    private FuncionarioService funcionarioService; 
    
    private List<Funcionario> funcionarios;
    
    String PATH_DIRECTORY_LISTAGEM_FUNCIONARIOS = "FUNCIONARIO";
    String JASPER_FUNCIONARIOS = "Funcionarios.jasper";
    
    //Filtros
    private Funcao funcao;
    private Empresa empresa;

    private List<String> situacoes;

    @PostConstruct
	public void init() {
    	situacoes = new ArrayList<String>();
    	situacoes.add(SituacaoFuncionario.ATIVO.getValue());
	}

    public void export() throws Exception {
    	funcionarios = funcionarioService.recuperarPor(funcao, situacoes, empresa);
    	if (!funcionarios.isEmpty()){
	    	File relatorio = getCaminhoRelatorio(PATH_DIRECTORY_LISTAGEM_FUNCIONARIOS, JASPER_FUNCIONARIOS);
	    	ExportarPdfController export = new ExportarPdfController(carregaParametros(), new JRBeanCollectionDataSource(funcionarios), "Listagem_Funcionarios", relatorio);
			export.download();
    	} else{
			UtilMensagens.mensagemInformacaoPorChave("mensagem.info.nenhumRegistroLocalizado");
    	}
    }
    
    private HashMap<String, Object> carregaParametros() {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PARAM_CLIENTE", super.cliente);
		StringBuilder sb = new StringBuilder();
		for(String situacao : situacoes){
			sb.append(situacao).append(" - ");
		}
		parameters.put("FILTRO_SITUACOES", sb.toString());
		parameters.put("FILTRO_FUNCAO", funcao);
		parameters.put("FILTRO_EMPRESA", empresa);
		parameters.put("IMAGE_PATH", getRequest().getServletContext().getRealPath("/resources/img/")+ "\\" );
		return parameters;
	}
		
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
 
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public List<String> getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(List<String> situacoes) {
		this.situacoes = situacoes;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}