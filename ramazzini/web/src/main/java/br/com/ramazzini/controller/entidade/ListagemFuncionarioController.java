package br.com.ramazzini.controller.entidade;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.controller.util.ExportarPdfController;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.service.entidade.FuncionarioService;
import br.com.ramazzini.service.entidade.ParametroService;
import br.com.ramazzini.service.util.Cliente;

@Named
@ConversationScoped
public class ListagemFuncionarioController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Inject
    private FuncionarioService funcionarioService; 
    
    @Inject
	ParametroService parametroService;
    
    private List<Funcionario> funcionarios;
    
    String PATH_DIRECTORY_LISTAGEM_FUNCIONARIOS = "FUNCIONARIO";
    String JASPER_FUNCIONARIOS = "Funcionarios.jasper";
    
    //Filtros
    private Funcao funcao;
    private List<String> situacoes;
	private Date dataSelecionada;

    

    public void export() throws Exception {
    	funcionarios = funcionarioService.recuperarPor(funcao, situacoes);
    	File relatorio = getCaminhoRelatorio(PATH_DIRECTORY_LISTAGEM_FUNCIONARIOS, JASPER_FUNCIONARIOS);
    	ExportarPdfController export = new ExportarPdfController(carregaParametros(), new JRBeanCollectionDataSource(funcionarios), "Listagem_Funcionarios", relatorio);
		export.download();
    }
    
    private HashMap<String, Object> carregaParametros() {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		Cliente cliente = new Cliente();
		parameters.put("NOME_CLIENTE", cliente.getNome());		
		parameters.put("ENDERECO_CLIENTE", cliente.getEnderecoFormatado());	
		StringBuilder sb = new StringBuilder();
		for(String situacao : situacoes){
			sb.append(situacao).append(" - ");
		}
		parameters.put("FILTRO_SITUACOES", sb.toString());
		parameters.put("FILTRO_FUNCAO", funcao);
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

	public Date getDataSelecionada() {
		return dataSelecionada;
	}

	public void setDataSelecionada(Date dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}

	public List<String> getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(List<String> situacoes) {
		this.situacoes = situacoes;
	}
}