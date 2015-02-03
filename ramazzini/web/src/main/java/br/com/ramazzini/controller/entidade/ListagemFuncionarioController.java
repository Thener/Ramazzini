package br.com.ramazzini.controller.entidade;

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
import br.com.ramazzini.model.funcionario.SituacaoFuncionario;
import br.com.ramazzini.model.parametro.ParametroSistema;
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
    
    String PATH_DIRECTORY_LISTAGEM_FUNCIONARIOS = "FUNCIONARIOS";
    String JASPER_FUNCIONARIOS = "Funcionarios.jasper";
    
    //Filtros
    private Funcao funcao;
    private List<SituacaoFuncionario> situacao;
	private Date dataSelecionada;

    

    public void export() throws Exception {
    	funcionarios = funcionarioService.recuperarPor(funcao, null, null);
    	String baseDir = parametroService.recuperarPorParametroSistema(ParametroSistema.DIR_BASE_RELATORIO).getValor();
    	ExportarPdfController export = new ExportarPdfController(carregaParametros(), new JRBeanCollectionDataSource(funcionarios), "Listagem_Funcionarios", baseDir);
		export.download(PATH_DIRECTORY_LISTAGEM_FUNCIONARIOS, JASPER_FUNCIONARIOS);
    }
    
    private HashMap<String, Object> carregaParametros() {
		HashMap<String, Object> parameters = new HashMap<String, Object>();

//		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
//		String imagemPath = request.getSession()
//				.getServletContext().getRealPath("img") + File.separator;
		parameters.put("IMAGE_PATH", "C:\\Projetos\\workspace\\Ramazzini\\ramazzini\\web\\src\\main\\webapp\\resources\\img\\");
		Cliente cliente = new Cliente();
		parameters.put("NOME_CLIENTE", cliente.getNome());		
		parameters.put("ENDERECO_CLIENTE", cliente.getEnderecoFormatado());		
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

	public List<SituacaoFuncionario> getSituacao() {
		return situacao;
	}

	public void setSituacao(List<SituacaoFuncionario> situacao) {
		this.situacao = situacao;
	}
}